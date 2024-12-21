package ru.itis.repository;

import ru.itis.model.TransactionCategoryEntity;

public interface TransactionCategoryRepository {

    boolean save(TransactionCategoryEntity transactionCategory);

    boolean delete(long transactionId, long categoryId);

}
