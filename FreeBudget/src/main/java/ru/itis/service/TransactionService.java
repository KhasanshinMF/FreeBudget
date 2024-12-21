package ru.itis.service;

import ru.itis.dto.TransactionDto;
import ru.itis.dto.TransactionDtoWithCategories;
import ru.itis.dto.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    TransactionResponse saveNewTransaction(TransactionDto dto);

    List<TransactionDtoWithCategories> findAllTransactionsByUserId(Long userId);

    Optional<TransactionDto> findTransactionById(Long id);

    List<TransactionDtoWithCategories> findTransactionsByCategoryId(Long categoryId);

    boolean updateTransactionsById(TransactionDto transaction, Long id);

    boolean deleteTransactionById(Long id);

}
