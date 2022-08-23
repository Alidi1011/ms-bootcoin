package com.example.msbootcoin.service;

import com.example.msbootcoin.model.PurchaseTemp;

import java.util.List;

public interface PurchaseTempService {
  List<PurchaseTemp> findAll();

  PurchaseTemp read(String id);

  PurchaseTemp save(PurchaseTemp purchaseTemp);

  void delete(String id);
}
