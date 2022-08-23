package com.example.msbootcoin.model;

import com.example.msbootcoin.enums.PaymentTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Getter
@Setter
@RedisHash("puchase_temp")
public class PurchaseTemp {
  @Id
  private String id;
  private BigDecimal amountBootcoin;
  private BigDecimal amountSoles;
  private PaymentTypeEnum paymentType;
  private String paymentNumber;
}
