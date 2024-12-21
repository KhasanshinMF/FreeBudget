package ru.itis.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.CategoryEntity;
import ru.itis.repository.CategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM categories WHERE id = ?";

    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM categories WHERE user_id = ?";

    private static final String SQL_SELECT_BY_TRANSACTION_ID =
            "SELECT id, user_id, name FROM categories JOIN transaction_categories ON id = category_id WHERE transaction_id = ?";

    private static final String SQL_INSERT = "INSERT INTO categories(user_id, name) VALUES (?, ?)";

    private static final String SQL_UPDATE = "UPDATE categories SET name = ? WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM categories WHERE id = ?";


    @Override
    public Optional<CategoryEntity> findCategoryById(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    new Object[] {id}, categoryRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<CategoryEntity> findAllCategoryByUserId(long userId) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_ID, new Object[] {userId}, categoryRowMapper);
    }

    @Override
    public List<CategoryEntity> findCategoriesByTransactionId(long transactionId) {
        return jdbcTemplate.query(SQL_SELECT_BY_TRANSACTION_ID, new Object[] {transactionId}, categoryRowMapper);
    }

    @Override
    public Optional<CategoryEntity> saveNewCategory(CategoryEntity category) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
                ps.setLong(1, category.getUserId());
                ps.setString(2, category.getName());
                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();
            return findCategoryById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean updateCategoryById(CategoryEntity category, long id) {
        return jdbcTemplate.update(SQL_UPDATE, category.getName(), id) == 1;
    }

    @Override
    public boolean deleteCategoryById(long id) {
        return jdbcTemplate.update(SQL_DELETE, id) == 1;
    }

    private static final class CategoryRowMapper implements RowMapper<CategoryEntity> {

        @Override
        public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return CategoryEntity.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getLong("user_id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
