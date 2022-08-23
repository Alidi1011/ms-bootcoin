package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Wallet;
import com.example.msbootcoin.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

  @Autowired
  WalletRepository repository;


  @Override
  public List<Wallet> findAll() {
    List<Wallet> wallets = new ArrayList<>();
    repository.findAll().forEach(wallets::add);
    return wallets;
  }

  @Override
  public Wallet read(String id) {
    return repository.findById(id).get();
  }

  @Override
  public Wallet save(Wallet customerWallet) throws Exception {
    String phoneNumber = customerWallet.getCellphoneNumber();
    Wallet walletExisted = repository.findByCellphoneNumber(phoneNumber);

    if(walletExisted != null) throw new Exception("There is already a wallet created with the cellphoneNumber sent");

    customerWallet.setCreatedAt(LocalDateTime.now());
    return repository.save(customerWallet);
  }

  @Override
  public Wallet update(Wallet customerWallet) {
    Wallet wallet = repository.findById(customerWallet.getId()).get();
    customerWallet.setCreatedAt(wallet.getCreatedAt());
    return repository.save(customerWallet);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }

  @Override
  public Wallet findByCellphoneNumber(String phoneNumber) {
    return repository.findByCellphoneNumber(phoneNumber);
  }
}
