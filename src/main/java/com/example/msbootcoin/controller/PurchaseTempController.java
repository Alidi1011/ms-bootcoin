package com.example.msbootcoin.controller;

import com.example.msbootcoin.model.PurchaseTemp;
import com.example.msbootcoin.service.PurchaseTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseTempController {

  @Autowired
  private PurchaseTempService purchaseService;

  @GetMapping
  public List<PurchaseTemp> getPurchases() {
    return purchaseService.findAll();
  }

  @PostMapping("/register")
  public PurchaseTemp save(@RequestBody PurchaseTemp purchaseTemp) {
    return purchaseService.save(purchaseTemp);
  }

  @GetMapping("/{id}")
  public PurchaseTemp read(@PathVariable String id) {
    return purchaseService.read(id).get();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    purchaseService.delete(id);
  }
}
