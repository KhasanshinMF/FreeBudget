package ru.itis.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryEntity {

    private Long transactionId;

    private Long categoryId;

}
