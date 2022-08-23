package com.example.msbootcoin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@RedisHash("bootcoin_wallet")
@Data
@NoArgsConstructor
public class Wallet implements Serializable {
  @Id
  @Indexed
  private String id;
  private String documentType;
  private String documentNumber;
  @Indexed
  private String cellphoneNumber;
  private String email;
  private BigDecimal balance;
  private LocalDateTime createdAt;
}
