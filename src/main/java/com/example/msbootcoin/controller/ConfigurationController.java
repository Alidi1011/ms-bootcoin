package com.example.msbootcoin.controller;

import com.example.msbootcoin.model.Configuration;
import com.example.msbootcoin.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

  @Autowired
  private ConfigurationService configurationService;

  @GetMapping
  public List<Configuration> getWallets() {
    return configurationService.findAll();
  }

  @PostMapping
  public Configuration save(@RequestBody Configuration configuration) {
    return configurationService.save(configuration);
  }

  @GetMapping("/{id}")
  public Configuration read(@PathVariable String id) {
    return configurationService.read(id);
  }

  @PutMapping
  public Configuration update(@RequestBody Configuration configuration) {
    return configurationService.update(configuration);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    configurationService.delete(id);
  }


  @GetMapping("/name/{name}")
  public Configuration readByPhone(@PathVariable String name) {
    return configurationService.findByName(name);
  }
}
