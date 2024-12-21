package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.dto.CategoryDto;
import ru.itis.dto.CategoryResponse;
import ru.itis.mapper.CategoryMapper;
import ru.itis.model.CategoryEntity;
import ru.itis.repository.CategoryRepository;
import ru.itis.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public Optional<CategoryDto> findCategoryById(long id) {
        return categoryRepository.findCategoryById(id).map(categoryMapper::toDto);
    }

    @Override
    public List<CategoryDto> findAllCategoryByUserId(long userId) {
        return categoryRepository.findAllCategoryByUserId(userId)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public List<CategoryDto> findCategoriesByTransactionId(long transactionId) {
        return categoryRepository.findCategoriesByTransactionId(transactionId)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponse saveNewCategory(CategoryDto category) {
        Optional<CategoryEntity> optionalCategory = categoryRepository
                .saveNewCategory(categoryMapper.toEntity(category));

        if (optionalCategory.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", categoryMapper.toDto(optionalCategory.get()));
    }

    @Override
    public boolean updateCategoryById(CategoryDto category, long id) {
        return categoryRepository.updateCategoryById(CategoryEntity.builder()
                .userId(category.getUserId())
                .name(category.getName())
                .build(), id);
    }

    @Override
    public boolean deleteCategoryById(long id) {
        return categoryRepository.deleteCategoryById(id);
    }

    private CategoryResponse response(int status, String statusDesc, CategoryDto categoryDto) {
        return CategoryResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .categoryDto(categoryDto)
                .build();
    }
}
