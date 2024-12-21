package ru.itis.mapper;

import ru.itis.dto.TransactionDto;
import ru.itis.model.TransactionEntity;

public interface TransactionMapper {

    TransactionDto toDto(TransactionEntity entity);

    TransactionEntity toEntity(TransactionDto transactionDto);

}
