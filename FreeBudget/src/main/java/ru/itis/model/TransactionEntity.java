package ru.itis.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    private Long id;

    private Long userId;

    private BigDecimal amount;

    private Date date;

    private boolean isIncome;

    private String description;

}
