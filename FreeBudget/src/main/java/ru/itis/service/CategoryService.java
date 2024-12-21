package ru.itis.service;

import ru.itis.dto.CategoryDto;
import ru.itis.dto.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<CategoryDto> findCategoryById(long id);

    List<CategoryDto> findAllCategoryByUserId(long userId);

    List<CategoryDto> findCategoriesByTransactionId(long transactionId);

    CategoryResponse saveNewCategory(CategoryDto category);

    boolean updateCategoryById(CategoryDto category, long id);

    boolean deleteCategoryById(long id);

}
