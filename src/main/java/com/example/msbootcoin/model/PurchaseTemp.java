package com.example.msbootcoin.model;

import com.example.msbootcoin.enums.PaymentModeEnum;
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
  private PaymentModeEnum paymentMode;
  private String paymentNumber;
  private String buyerPhone;
}
