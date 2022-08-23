package com.example.msbootcoin.service;

import com.example.msbootcoin.model.PurchaseTemp;

import java.util.List;
import java.util.Optional;

public interface PurchaseTempService {
    List<PurchaseTemp> findAll();

    Optional<PurchaseTemp> read(String id);

    PurchaseTemp save(PurchaseTemp purchaseTemp);

    void delete(String id);
}
