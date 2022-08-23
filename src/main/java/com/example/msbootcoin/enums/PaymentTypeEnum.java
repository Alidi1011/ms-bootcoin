package com.example.msbootcoin.enums;

public enum PaymentTypeEnum {
  YANKI("YANKI"),
  TRANSFER("TRANSFER");
  private final String value;

  PaymentTypeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
