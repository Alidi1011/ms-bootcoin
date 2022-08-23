package com.example.msbootcoin.kafka.consumer;

import com.example.msbootcoin.dto.TransactionDto;
import com.example.msbootcoin.model.PurchaseTemp;
import com.example.msbootcoin.model.Transaction;
import com.example.msbootcoin.model.Wallet;
import com.example.msbootcoin.service.PurchaseTempService;
import com.example.msbootcoin.service.TransactionService;
import com.example.msbootcoin.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PurchaseConsumer {

    private final TransactionService transactionService;
    private final WalletService walletService;

    private final PurchaseTempService purchaseService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @KafkaListener(topics = "${kafka.topic.name}")
    public void listener(@Payload TransactionDto transactionDto) {
        log.info("Message received in ms-bootcoin: {} ", transactionDto);

        Optional<PurchaseTemp> purchaseTemp = this.getPurchaseTemp(transactionDto.getPurchaseTempId());

        if(purchaseTemp.isPresent()) {
            Wallet walletOrigin = this.getWalletByPhoneNumber(purchaseTemp.get().getBuyerPhone());
            Wallet walletDestiny = this.getWalletByPhoneNumber(transactionDto.getSellerPhone());

            if(walletOrigin != null && walletDestiny != null){
                walletOrigin.setBalance(walletOrigin.getBalance().add(purchaseTemp.get().getAmountBootcoin()));
                walletDestiny.setBalance(walletDestiny.getBalance().subtract(purchaseTemp.get().getAmountBootcoin()));
                walletService.update(walletOrigin);
                walletService.update(walletDestiny);

                this.saveTransaction(purchaseTemp.get(), transactionDto);

                purchaseService.delete(purchaseTemp.get().getId());

            }else{
                log.error("PhoneNumbers sent don't exist in ms-bootcoin");
            }
        }else{
            log.error("PurchaseTempId received don't exist in ms-bootcoin");
        }
    }

    private Wallet getWalletByPhoneNumber(String phoneNumber){
        System.out.println("PhoneNumber:" + phoneNumber);
        Wallet wallet = walletService.findByCellphoneNumber(phoneNumber);
        if(wallet == null) {
            log.error("PhoneNumber: " + phoneNumber + " sent don't exist in ms-bootcoin");
        }
        return wallet;
    }

    private Optional<PurchaseTemp> getPurchaseTemp(String idPurchaseTemp){
        log.info("idPurchaseTemp:" + idPurchaseTemp);
        Optional<PurchaseTemp> purchaseTemp = purchaseService.read(idPurchaseTemp);
        if(!purchaseTemp.isPresent()) {
            log.error("idPurchaseTemp: " + idPurchaseTemp + " sent don't exist in ms-bootcoin");
        }
        return purchaseTemp;
    }

    private void saveTransaction(PurchaseTemp purchaseTemp, TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        LocalDateTime dateTime;

        if(transactionDto.getDateTime() == null){
            dateTime = LocalDateTime.now();
        }else{
            dateTime = LocalDate.parse(transactionDto.getDateTime(), FORMATTER).atStartOfDay();
        }
        transaction.setAmountBootcoin(purchaseTemp.getAmountBootcoin());
        transaction.setAmountSoles(purchaseTemp.getAmountSoles());
        transaction.setPaymentOriginMode(purchaseTemp.getPaymentMode().getValue());
        transaction.setPaymentOriginNumber(purchaseTemp.getPaymentNumber());
        transaction.setPaymentDestinyMode(transactionDto.getPaymentDestinyMode());
        transaction.setPaymentDestinyNumber(transactionDto.getPaymentDestinyNumber());
        transaction.setBuyerPhone(purchaseTemp.getBuyerPhone());
        transaction.setSellerPhone(transactionDto.getSellerPhone());
        transaction.setDateTime(dateTime);
        Transaction transactionSaved = transactionService.save(transaction);
        log.info("walletTransaction saved in ms-bootcoin: {} ", transactionSaved);
    }

}
