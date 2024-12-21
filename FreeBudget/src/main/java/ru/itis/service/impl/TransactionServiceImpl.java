package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.dto.CategoryDto;
import ru.itis.dto.TransactionDto;
import ru.itis.dto.TransactionDtoWithCategories;
import ru.itis.dto.TransactionResponse;
import ru.itis.mapper.CategoryMapper;
import ru.itis.mapper.TransactionMapper;
import ru.itis.mapper.impl.CategoryMapperImpl;
import ru.itis.model.TransactionEntity;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.impl.CategoryRepositoryImpl;
import ru.itis.service.CategoryService;
import ru.itis.service.TransactionService;
import ru.itis.util.TransactionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    private final CategoryMapper categoryMapper = new CategoryMapperImpl();

    private final CategoryService categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);

    @Override
    public TransactionResponse saveNewTransaction(TransactionDto dto) {
        if (!TransactionUtil.checkAmount(dto.getAmount()))
            return response(1, "Invalid amount", null);

        if (!TransactionUtil.checkDate(dto.getDate()))
            return response(2, "Invalid date", null);

        if (dto.getDescription() == null || dto.getDescription().isBlank())
            return response(3, "Empty description", null);

        Optional<TransactionEntity> optionalTransaction = transactionRepository
                .saveNewTransaction(transactionMapper.toEntity(dto));

        if (optionalTransaction.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", transactionMapper.toDto(optionalTransaction.get()));
    }

    @Override
    public List<TransactionDtoWithCategories> findAllTransactionsByUserId(Long userId) {
        List<TransactionDto> transactions =  transactionRepository.findAllTransactionByUserId(userId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();

        List<TransactionDtoWithCategories> result = new ArrayList<>();

        for (TransactionDto transaction : transactions) {
            TransactionDtoWithCategories transactionWithCategories = copyTransactionDto(transaction);
            List<CategoryDto> categories = categoryService.findCategoriesByTransactionId(transaction.getId());
            transactionWithCategories.setCategories(categories);
            result.add(transactionWithCategories);
        }

        return result;
    }

    private TransactionDtoWithCategories copyTransactionDto(TransactionDto source) {
        TransactionDtoWithCategories target = TransactionDtoWithCategories.builder()
                .id(source.getId())
                .userId(source.getUserId())
                .amount(source.getAmount())
                .date(source.getDate())
                .isIncome(source.isIncome())
                .description(source.getDescription())
                .build();

        return target;
    }

    @Override
    public Optional<TransactionDto> findTransactionById(Long id) {
        return transactionRepository.findTransactionById(id).map(transactionMapper::toDto);
    }

    @Override
    public List<TransactionDtoWithCategories> findTransactionsByCategoryId(Long categoryId) {
        List<TransactionDto> transactions =  transactionRepository.findTransactionsByCategoryId(categoryId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();

        List<TransactionDtoWithCategories> result = new ArrayList<>();

        for (TransactionDto transaction : transactions) {
            TransactionDtoWithCategories transactionWithCategories = copyTransactionDto(transaction);
            List<CategoryDto> categories = categoryService.findCategoriesByTransactionId(transaction.getId());
            transactionWithCategories.setCategories(categories);
            result.add(transactionWithCategories);
        }

        return result;
    }

    @Override
    public boolean updateTransactionsById(TransactionDto transaction, Long id) {
        return transactionRepository.updateTransactionById(TransactionEntity.builder()
                .userId(transaction.getUserId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .isIncome(transaction.isIncome())
                .build(), id);
    }

    @Override
    public boolean deleteTransactionById(Long id) {
        return transactionRepository.deleteTransactionById(id);
    }

    private TransactionResponse response(int status, String statusDesc, TransactionDto transactionDto) {
        return TransactionResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .transactionDto(transactionDto)
                .build();
    }
}
