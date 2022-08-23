package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Wallet;

import java.util.List;

public interface WalletService {
  List<Wallet> findAll();

  Wallet read(String id);

  Wallet save(Wallet customerWallet) throws Exception;

  Wallet update(Wallet customerWallet);

  void delete(String id);

  Wallet findByCellphoneNumber(String phoneNumber);
}
