package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Configuration;
import com.example.msbootcoin.model.Wallet;
import com.example.msbootcoin.repository.ConfigurationRepository;
import com.example.msbootcoin.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

  @Autowired
  ConfigurationRepository repository;


  @Override
  public List<Configuration> findAll() {
    List<Configuration> configurations = new ArrayList<>();
    repository.findAll().forEach(configurations::add);
    return configurations;
  }

  @Override
  public Configuration read(String id) {
    return repository.findById(id).get();
  }

  @Override
  public Configuration save(Configuration customerWallet) {
    customerWallet.setCreatedAt(LocalDateTime.now());
    return repository.save(customerWallet);
  }

  @Override
  public Configuration update(Configuration customerWallet) {
    Configuration configuration = repository.findById(customerWallet.getId()).get();
    customerWallet.setCreatedAt(configuration.getCreatedAt());
    return repository.save(customerWallet);
  }

  @Override
  public void delete(String id) {
    repository.deleteById(id);
  }


  @Override
  public Configuration findByName(String name) {
    return repository.findByName(name);
  }
}
