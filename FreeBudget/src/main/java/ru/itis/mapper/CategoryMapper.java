package ru.itis.mapper;

import ru.itis.dto.CategoryDto;
import ru.itis.model.CategoryEntity;

public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity entity);

    CategoryEntity toEntity(CategoryDto categoryDto);

}
