package ru.itis.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private int status;

    private String statusDesc;

    private TransactionDto transactionDto;

}
