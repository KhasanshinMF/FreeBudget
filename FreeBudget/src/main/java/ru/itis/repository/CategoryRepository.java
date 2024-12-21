package ru.itis.repository;

import ru.itis.model.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<CategoryEntity> findCategoryById(long id);

    List<CategoryEntity> findAllCategoryByUserId(long userId);

    List<CategoryEntity> findCategoriesByTransactionId(long transactionId);

    Optional<CategoryEntity> saveNewCategory(CategoryEntity category);

    boolean updateCategoryById(CategoryEntity category, long id);

    boolean deleteCategoryById(long id);

}
