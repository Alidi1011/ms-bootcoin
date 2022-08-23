package com.example.msbootcoin.repository;

import com.example.msbootcoin.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, String> {
  Wallet findByCellphoneNumber(String number);
}
