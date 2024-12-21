package ru.itis.mapper.impl;

import ru.itis.dto.CategoryDto;
import ru.itis.mapper.CategoryMapper;
import ru.itis.model.CategoryEntity;

public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(CategoryEntity entity) {
        return CategoryDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .build();
    }

    @Override
    public CategoryEntity toEntity(CategoryDto categoryDto) {
        return CategoryEntity.builder()
                .id(categoryDto.getId())
                .userId(categoryDto.getUserId())
                .name(categoryDto.getName())
                .build();
    }
}
