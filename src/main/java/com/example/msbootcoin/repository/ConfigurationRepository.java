package com.example.msbootcoin.repository;

import com.example.msbootcoin.model.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
  Configuration findByName(String name);
}
