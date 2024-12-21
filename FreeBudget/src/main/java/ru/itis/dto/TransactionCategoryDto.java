package ru.itis.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryDto {

    private Long transactionId;

    private Long categoryId;

}
