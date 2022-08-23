package com.example.msbootcoin.controller;

import com.example.msbootcoin.model.Transaction;
import com.example.msbootcoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @GetMapping
  public List<Transaction> getTransactions() {
    return transactionService.findAll();
  }

  @PostMapping
  public Transaction save(@RequestBody Transaction transaction) {
    return transactionService.save(transaction);
  }

  @GetMapping("/{id}")
  public Transaction read(@PathVariable String id) {
    return transactionService.read(id);
  }

  @PutMapping
  public Transaction update(@RequestBody Transaction transaction) {
    return transactionService.update(transaction);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    transactionService.delete(id);
  }

  @GetMapping("/byBuyerPhone/{phone}")
  public List<Transaction> getTransactions(@PathVariable String phone) {
    return transactionService.findByBuyerPhone(phone);
  }
}
