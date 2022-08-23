package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Transaction;

import java.util.List;

public interface TransactionService {
  List<Transaction> findAll();

  Transaction read(String id);

  Transaction save(Transaction transaction);

  Transaction update(Transaction transaction);

  void delete(String id);

  List<Transaction> findByBuyerPhone(String phone);

}
