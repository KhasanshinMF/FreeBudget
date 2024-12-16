package ru.itis.repository;

import ru.itis.model.TransactionEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Optional<TransactionEntity> findTransactionById(long id);

    Optional<List<TransactionEntity>> findAllTransactionByUserId(long userId);

    Optional<List<TransactionEntity>> findAllIncomesByUserId(long userId);

    Optional<List<TransactionEntity>> findAllExpensesByUserId(long userId);

    Optional<List<TransactionEntity>> findAllTransactionsByAmount(int startAmount, int endAmount);

    Optional<List<TransactionEntity>> findAllTransactionsByDate(Date date);

    Optional<List<TransactionEntity>> findAllTransactionsByDateDifference(Date startDate, Date endDate);

    Optional<TransactionEntity> saveNewTransaction(TransactionEntity transaction);

}
