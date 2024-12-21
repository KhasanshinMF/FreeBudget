package ru.itis.repository;

import ru.itis.model.TransactionEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Optional<TransactionEntity> findTransactionById(long id);

    List<TransactionEntity> findAllTransactionByUserId(long userId);

    List<TransactionEntity> findAllIncomesByUserId(long userId);

    List<TransactionEntity> findAllExpensesByUserId(long userId);

    List<TransactionEntity> findAllTransactionsByAmount(int startAmount, int endAmount);

    List<TransactionEntity> findAllTransactionsByDate(Date date);

    List<TransactionEntity> findAllTransactionsByDateDifference(Date startDate, Date endDate);

    List<TransactionEntity> findTransactionsByCategoryId(long categoryId);

    Optional<TransactionEntity> saveNewTransaction(TransactionEntity transaction);

    boolean updateTransactionById(TransactionEntity transaction, long id);

    boolean deleteTransactionById(long id);

}
