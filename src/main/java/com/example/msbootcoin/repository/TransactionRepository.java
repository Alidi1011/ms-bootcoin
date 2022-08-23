package com.example.msbootcoin.repository;

import com.example.msbootcoin.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    List<Transaction> findByBuyerPhone(String phone);

}
