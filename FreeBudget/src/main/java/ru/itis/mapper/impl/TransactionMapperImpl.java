package ru.itis.mapper.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.dto.TransactionDto;
import ru.itis.mapper.TransactionMapper;
import ru.itis.model.TransactionEntity;

@RequiredArgsConstructor
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toDto(TransactionEntity entity) {
        return TransactionDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .isIncome(entity.isIncome())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public TransactionEntity toEntity(TransactionDto transactionDto) {
        return TransactionEntity.builder()
                .userId(transactionDto.getUserId())
                .amount(transactionDto.getAmount())
                .date(transactionDto.getDate())
                .isIncome(transactionDto.isIncome())
                .description(transactionDto.getDescription())
                .build();
    }

}
