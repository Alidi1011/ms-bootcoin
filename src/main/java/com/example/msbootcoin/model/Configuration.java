package com.example.msbootcoin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@RedisHash("bootcoin_configuration")
@Data
@NoArgsConstructor
public class Configuration implements Serializable {
  @Id
  @Indexed
  private String id;
  @Indexed
  private String name;
  private BigDecimal value;
  private LocalDateTime createdAt;
}
