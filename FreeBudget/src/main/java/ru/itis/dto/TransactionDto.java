package ru.itis.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private long id;

    private long userId;

    private BigDecimal amount;

    private Date date;

    private boolean isIncome;

    private String description;

}
