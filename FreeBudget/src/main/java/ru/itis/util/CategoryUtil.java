package ru.itis.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import ru.itis.dto.CategoryDto;
import ru.itis.model.CategoryEntity;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.UserRepository;

import java.util.List;

@UtilityClass
public class CategoryUtil {

    private CategoryRepository categoryRepository;

    public boolean checkName(CategoryDto categoryDto) {
        List<CategoryEntity> userCategories = categoryRepository.findAllCategoryByUserId(categoryDto.getUserId());

        if (userCategories.isEmpty()) return true;
        for (CategoryEntity category : userCategories) {
            if (category.getName().equals(categoryDto.getName())) return false;
        }
        return true;
    }
}
