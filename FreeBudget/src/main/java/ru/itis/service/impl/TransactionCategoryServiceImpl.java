package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.dto.TransactionCategoryDto;
import ru.itis.mapper.TransactionCategoryMapper;
import ru.itis.repository.TransactionCategoryRepository;
import ru.itis.service.TransactionCategoryService;

@RequiredArgsConstructor
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionCategoryMapper transactionCategoryMapper;

    @Override
    public boolean save(TransactionCategoryDto dto) {
        return transactionCategoryRepository.save(transactionCategoryMapper.toEntity(dto));
    }

    @Override
    public boolean delete(long transactionId, long categoryId) {
        return transactionCategoryRepository.delete(transactionId, categoryId);
    }
}
