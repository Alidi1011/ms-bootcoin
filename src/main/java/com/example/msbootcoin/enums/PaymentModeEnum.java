package com.example.msbootcoin.enums;

public enum PaymentModeEnum {
  YANKI("YANKI"),
  TRANSFER("TRANSFER");
  private final String value;

  PaymentModeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
