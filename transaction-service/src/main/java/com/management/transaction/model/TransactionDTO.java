package com.management.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private LocalDateTime transactionDate;
    private String studentName;
    private Long studentId;
    private Long reference;
    private String cardNumber;
    private String cardType;
}
