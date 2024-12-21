package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.TransactionCategoryEntity;
import ru.itis.repository.TransactionCategoryRepository;


public class TransactionCategoryRepositoryImpl implements TransactionCategoryRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private static final String SQL_INSERT = "INSERT INTO transaction_categories (transaction_id, category_id) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM transaction_categories WHERE transaction_id = ? AND category_id = ?";

    @Override
    public boolean save(TransactionCategoryEntity transactionCategory) {
        return jdbcTemplate.update(SQL_INSERT,
                transactionCategory.getTransactionId(), transactionCategory.getCategoryId()) == 1;
    }

    @Override
    public boolean delete(long transactionId, long categoryId) {
        return jdbcTemplate.update(SQL_DELETE, transactionId, categoryId) == 1;
    }
}
