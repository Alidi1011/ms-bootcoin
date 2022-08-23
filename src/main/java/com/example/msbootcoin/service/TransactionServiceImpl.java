package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Transaction;
import com.example.msbootcoin.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  TransactionRepository repository;


  @Override
  public List<Transaction> findAll() {
    List<Transaction> transactions = new ArrayList<>();
    repository.findAll().forEach(transactions::add);
    return transactions;
  }

  @Override
  public Transaction read(String id) {
    return repository.findById(id).get();
  }

  @Override
  public Transaction save(Transaction transaction) {
    return repository.save(transaction);
  }

  @Override
  public Transaction update(Transaction transaction) {
    return repository.save(transaction);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }
}
