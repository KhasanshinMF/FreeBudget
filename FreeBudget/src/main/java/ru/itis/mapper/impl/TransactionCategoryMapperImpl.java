package ru.itis.mapper.impl;

import ru.itis.dto.TransactionCategoryDto;
import ru.itis.mapper.TransactionCategoryMapper;
import ru.itis.model.TransactionCategoryEntity;

public class TransactionCategoryMapperImpl implements TransactionCategoryMapper {

    @Override
    public TransactionCategoryDto toDto(TransactionCategoryEntity entity) {
        return TransactionCategoryDto.builder()
                .transactionId(entity.getTransactionId())
                .categoryId(entity.getCategoryId())
                .build();
    }

    @Override
    public TransactionCategoryEntity toEntity(TransactionCategoryDto dto) {
        return TransactionCategoryEntity.builder()
                .transactionId(dto.getTransactionId())
                .categoryId(dto.getCategoryId())
                .build();
    }
}
