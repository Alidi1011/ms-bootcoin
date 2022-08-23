package com.example.msbootcoin.kafka.consumer;

import com.example.msbootcoin.dto.PurchaseDto;
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

@Component
@Slf4j
@RequiredArgsConstructor
public class PurchaseConsumer {

    private final TransactionService transactionService;
    private final WalletService walletService;

    private final PurchaseTempService purchaseService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @KafkaListener(topics = "${kafka.topic.name}")
    public void listener(@Payload PurchaseDto purchaseDto) {
        log.info("Message received in ms-bootcoin: {} ", purchaseDto);

        PurchaseTemp purchaseTemp = this.getPurchaseTemp(purchaseDto.getPurchaseTempId());

        Wallet walletOrigin = this.getWalletByPhoneNumber(purchaseDto.getPaymentOriginNumber());
        Wallet walletDestiny = this.getWalletByPhoneNumber(purchaseDto.getPaymentDestinyNumber());


        if(walletOrigin != null & walletDestiny != null){
            Transaction transaction = new Transaction();
            LocalDateTime dateTime;

            if(purchaseDto.getDateTime() == null){
                dateTime = LocalDateTime.now();
            }else{
                dateTime = LocalDate.parse(purchaseDto.getDateTime(), FORMATTER).atStartOfDay();
            }

            transaction.setAmountBootcoin(purchaseDto.getAmountBootcoin());
            transaction.setAmountSoles(purchaseDto.getAmountSoles());
            transaction.setPaymentOriginType(purchaseDto.getPaymentOriginType());
            transaction.setPaymentOriginNumber(purchaseDto.getPaymentOriginNumber());
            transaction.setPaymentDestinyType(purchaseDto.getPaymentDestinyType());
            transaction.setPaymentDestinyNumber(purchaseDto.getPaymentDestinyNumber());
            transaction.setDateTime(dateTime);

            walletOrigin.setBalance(walletOrigin.getBalance().add(purchaseDto.getAmountBootcoin()));
            walletDestiny.setBalance(walletDestiny.getBalance().subtract(purchaseDto.getAmountBootcoin()));

            walletService.update(walletOrigin);
            walletService.update(walletDestiny);
            purchaseService.delete(purchaseTemp.getId());
            Transaction transactionSaved = transactionService.save(transaction);
            log.info("walletTransaction saved in ms-bootcoin: {} ", transactionSaved);
        }else{
            log.error("PhoneNumbers sent don't exist in ms-bootcoin");
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

    private PurchaseTemp getPurchaseTemp(String idPurchaseTemp){
        System.out.println("idPurchaseTemp:" + idPurchaseTemp);
        PurchaseTemp purchaseTemp = purchaseService.read(idPurchaseTemp);
        if(purchaseTemp == null) {
            log.error("idPurchaseTemp: " + idPurchaseTemp + " sent don't exist in ms-bootcoin");
        }
        return purchaseTemp;
    }

}
