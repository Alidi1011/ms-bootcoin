package com.example.msbootcoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private String paymentDestinyMode;
    private String paymentDestinyNumber;
    private String sellerPhone;
    private String dateTime;
    private String purchaseTempId;
}
