package ru.itis.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoWithCategories {

    private long id;

    private long userId;

    private BigDecimal amount;

    private Date date;

    private boolean isIncome;

    private String description;

    private List<CategoryDto> categories;

}
