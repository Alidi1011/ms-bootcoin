package com.example.msbootcoin.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

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
  private String paymentOriginMode;
  private String paymentOriginNumber;
  private String paymentDestinyMode;
  private String paymentDestinyNumber;
  @Indexed
  private String buyerPhone;
  private String sellerPhone;
  private LocalDateTime dateTime;
}
