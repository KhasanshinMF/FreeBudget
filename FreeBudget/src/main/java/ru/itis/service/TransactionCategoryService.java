package ru.itis.service;

import ru.itis.dto.TransactionCategoryDto;

public interface TransactionCategoryService {

    boolean save(TransactionCategoryDto dto);

    boolean delete(long transactionId, long categoryId);


}
