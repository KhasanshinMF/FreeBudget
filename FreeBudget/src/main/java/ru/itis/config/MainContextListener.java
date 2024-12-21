package ru.itis.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.mapper.CategoryMapper;
import ru.itis.mapper.TransactionMapper;
import ru.itis.mapper.UserMapper;
import ru.itis.mapper.impl.TransactionMapperImpl;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.UserRepository;
import ru.itis.repository.impl.TransactionRepositoryImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.TransactionService;
import ru.itis.service.UserService;
import ru.itis.service.impl.TransactionServiceImpl;
import ru.itis.service.impl.UserServiceImpl;
import ru.itis.util.PropertyReader;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static ru.itis.util.KeyNames.DATABASE_ERROR;
import static ru.itis.util.KeyNames.USER_SERVICE;

@Slf4j
@WebListener
public class MainContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            DataSource dataSource = dataSource();
            Connection connection = dataSource.getConnection();
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Connection to the database failed.");
            }

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            UserMapper userMapper = new UserMapperImpl();

            UserRepository userRepository = new UserRepositoryImpl(jdbcTemplate);

            UserService userService = initUserService(userRepository, userMapper, context);

            TransactionRepository transactionRepository = new TransactionRepositoryImpl();
            context.setAttribute("transactionRepository", transactionRepository);
            TransactionMapper transactionMapper = new TransactionMapperImpl();

            TransactionService transactionService = new TransactionServiceImpl(transactionMapper, transactionRepository);
            context.setAttribute("transactionService", transactionService);

        } catch (Exception e) {
            context.setAttribute(DATABASE_ERROR, "Database connection failed");
            log.error("Database connection failed", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_USER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }

    private UserService initUserService(UserRepository userRepository, UserMapper userMapper, ServletContext context) {
        UserService userService = new UserServiceImpl(userRepository, userMapper);
        context.setAttribute(USER_SERVICE, userService);
        return userService;
    }

    private void initCategoryService(JdbcTemplate jdbcTemplate, CategoryRepository categoryRepository, CategoryMapper categoryMapper, ServletContext context) {

    }
}
