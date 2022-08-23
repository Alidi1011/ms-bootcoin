package com.example.msbootcoin.service;

import com.example.msbootcoin.model.Configuration;

import java.util.List;

public interface ConfigurationService {
  List<Configuration> findAll();

  Configuration read(String id);

  Configuration save(Configuration customerWallet);

  Configuration update(Configuration customerWallet);

  void delete(String id);

  Configuration findByName(String name);
}
