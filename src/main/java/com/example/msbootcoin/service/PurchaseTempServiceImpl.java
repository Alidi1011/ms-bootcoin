package com.example.msbootcoin.service;

import com.example.msbootcoin.model.PurchaseTemp;
import com.example.msbootcoin.repository.PurchaseTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseTempServiceImpl implements PurchaseTempService {

  @Autowired
  PurchaseTempRepository repository;

  @Override
  public List<PurchaseTemp> findAll() {
    List<PurchaseTemp> purchases = new ArrayList<>();
    repository.findAll().forEach(purchases::add);
    return purchases;
  }

  @Override
  public Optional<PurchaseTemp> read(String id) {
    return repository.findById(id);
  }

  @Override
  public PurchaseTemp save(PurchaseTemp purchaseTemp) {
    return repository.save(purchaseTemp);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }

}
