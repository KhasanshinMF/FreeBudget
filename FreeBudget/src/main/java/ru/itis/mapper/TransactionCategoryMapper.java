package ru.itis.mapper;

import ru.itis.dto.TransactionCategoryDto;
import ru.itis.model.TransactionCategoryEntity;

public interface TransactionCategoryMapper {

    TransactionCategoryDto toDto(TransactionCategoryEntity entity);

    TransactionCategoryEntity toEntity(TransactionCategoryDto dto);

}
