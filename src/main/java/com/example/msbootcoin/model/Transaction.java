package com.example.msbootcoin.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RedisHash("bootcoin_transaction")
public class Transaction {
  @Id
  private String id;
  private BigDecimal amountBootcoin;
  private BigDecimal amountSoles;
  private String paymentOriginType;
  private String paymentOriginNumber;
  private String paymentDestinyType;
  private String paymentDestinyNumber;
  private LocalDateTime dateTime;
}
