package ru.itis;

import ru.itis.mapper.TransactionMapper;
import ru.itis.mapper.impl.TransactionMapperImpl;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.impl.CategoryRepositoryImpl;
import ru.itis.repository.impl.TransactionRepositoryImpl;
import ru.itis.service.TransactionService;
import ru.itis.service.impl.TransactionServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();

//        System.out.println(transactionRepository.findTransactionById(1));
//        System.out.println(transactionRepository.findAllTransactionByUserId(2));
//        System.out.println(transactionRepository.findAllTransactionsByDate(dateRead("2024-12-15")));
//        System.out.println(transactionRepository.findAllTransactionsByDateDifference(dateRead("2024-12-13"), dateRead("2024-12-14")));
//        TransactionEntity transaction = new TransactionEntity(1234L, 3L, 1999, dateRead("2024-12-16"), false, "Свечи зажигания");
//        System.out.println(transactionRepository.saveNewTransaction(transaction));

//        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
//        System.out.println(categoryRepository.findCategoryById(1));
//        System.out.println(categoryRepository.findAllCategoryByUserId(1));
//        CategoryEntity category = new CategoryEntity(123L, 2L, "Спорт пит");
//        System.out.println(categoryRepository.saveNewCategory(category));

//        TransactionMapper transactionMapper = new TransactionMapperImpl();
//        TransactionService transactionService = new TransactionServiceImpl(transactionMapper, transactionRepository, c);
//
//        List<TransactionRequest> transactions = transactionService.findAllTransactionsByUserId(2L);
//
//        for (TransactionRequest transaction : transactions) {
//            System.out.println(transaction);
//        }
//
//        System.out.println();
//
//        List<TransactionEntity> transactionEntities = transactionRepository.findAllTransactionByUserId(2L);
//
//        for (TransactionEntity transaction : transactionEntities) {
//            System.out.println(transaction);
//        }

//        System.out.println();

//        CategoryRepository categoryRepository= new CategoryRepositoryImpl();

//        System.out.println(categoryRepository.findCategoriesByTransactionId(5L));

//        System.out.println(transactionRepository.findTransactionsByCategoryId(1L));

//        System.out.println(transactionService.findTransactionsByCategoryId(2L));


    }

    private static Date dateRead(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        while (true) {
            try {
                parsedDate = formatter.parse(date);
                break;
            } catch (Exception e) {
                System.out.println("Некорректный ввод даты.");
            }
        }
        return parsedDate;
    }
}
