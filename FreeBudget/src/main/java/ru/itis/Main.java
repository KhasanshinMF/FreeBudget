package ru.itis;

import ru.itis.model.TransactionEntity;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.impl.TransactionRepositoryImpl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();

//        System.out.println(transactionRepository.findTransactionById(1));
//        System.out.println(transactionRepository.findAllTransactionByUserId(2));

//        System.out.println(transactionRepository.findAllTransactionsByDate(dateRead("2024-12-15")));

        System.out.println(transactionRepository.findAllTransactionsByDateDifference(dateRead("2024-12-13"), dateRead("2024-12-14")));

        TransactionEntity transaction = new TransactionEntity(1234L, 3L, 1999, dateRead("2024-12-16"), false, "Свечи зажигания");
        System.out.println(transactionRepository.saveNewTransaction(transaction));
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
