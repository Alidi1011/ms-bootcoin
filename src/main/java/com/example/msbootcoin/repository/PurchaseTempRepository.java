package com.example.msbootcoin.repository;

import com.example.msbootcoin.model.PurchaseTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTempRepository extends CrudRepository<PurchaseTemp, String> {
}
