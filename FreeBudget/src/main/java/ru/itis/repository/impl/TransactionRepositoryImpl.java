package ru.itis.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.TransactionEntity;
import ru.itis.repository.TransactionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM transactions WHERE id = ?";

    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM transactions WHERE user_id = ?";

    private static final String SQL_SELECT_ALL_INCOMES_BY_USER_ID =
            "SELECT * FROM transactions WHERE user_id = ? AND is_income = TRUE";
    private static final String SQL_SELECT_ALL_EXPENSES_BY_USER_ID =
            "SELECT * FROM transactions WHERE user_id = ? AND is_income = FALSE";
    private static final String SQL_SELECT_ALL_BY_AMOUNT =
            "SELECT * FROM transactions WHERE amount > ? AND amount < ?";

    private static final String SQL_SELECT_ALL_BY_DATE = "SELECT * FROM transactions WHERE date = ?";

    private static final String SQL_SELECT_ALL_BY_DATE_DIFFERENCE =
            "SELECT * FROM transactions WHERE date >= ? AND date <= ?";

    private static final String SQL_INSERT =
            "INSERT INTO transactions(user_id, amount, date, is_income, description) VALUES (?, ?, ?, ?, ?)";

    private final TransactionRowMapper transactionRowMapper = new TransactionRowMapper();

    @Override
    public Optional<TransactionEntity> findTransactionById(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    new Object[] {id}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllTransactionByUserId(long userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_BY_USER_ID,
                    new Object[] {userId}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllIncomesByUserId(long userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_ALL_INCOMES_BY_USER_ID,
                    new Object[] {userId}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllExpensesByUserId(long userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_ALL_EXPENSES_BY_USER_ID,
                    new Object[] {userId}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllTransactionsByAmount(int startAmount, int endAmount) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_ALL_BY_AMOUNT,
                    new Object[] {startAmount, endAmount}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllTransactionsByDate(Date date) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_ALL_BY_DATE,
                    new Object[] {date}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<TransactionEntity>> findAllTransactionsByDateDifference(Date startDate, Date endDate) {
        try {
            return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_ALL_BY_DATE_DIFFERENCE,
                    new Object[] {startDate, endDate}, transactionRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<TransactionEntity> saveNewTransaction(TransactionEntity transaction) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
                ps.setLong(1, transaction.getUserId());
                ps.setInt(2, transaction.getAmount());
                ps.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
                ps.setBoolean(4, transaction.isIncome());
                ps.setString(5, transaction.getDescription());
                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();
            return findTransactionById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static final class TransactionRowMapper implements RowMapper<TransactionEntity> {

        @Override
        public TransactionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return TransactionEntity.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getLong("user_id"))
                    .date(rs.getDate("date"))
                    .isIncome(rs.getBoolean("is_income"))
                    .description(rs.getString("description"))
                    .build();
        }
    }
}
