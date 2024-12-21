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
import ru.itis.mapper.impl.CategoryMapperImpl;
import ru.itis.mapper.impl.TransactionMapperImpl;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.UserRepository;
import ru.itis.repository.impl.CategoryRepositoryImpl;
import ru.itis.repository.impl.TransactionRepositoryImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.CategoryService;
import ru.itis.service.TransactionService;
import ru.itis.service.UserService;
import ru.itis.service.impl.CategoryServiceImpl;
import ru.itis.service.impl.TransactionServiceImpl;
import ru.itis.service.impl.UserServiceImpl;
import ru.itis.util.PropertyReader;

import javax.sql.DataSource;

@Slf4j
@WebListener
public class MainServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = dataSource();
        context.setAttribute("dataSource", dataSource);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        context.setAttribute("jdbcTemplate", jdbcTemplate);

        UserMapper userMapper = new UserMapperImpl();
        context.setAttribute("userMapper", userMapper);

        UserRepository userRepository = new UserRepositoryImpl();
        context.setAttribute("userRepository", userRepository);

        UserService userService = new UserServiceImpl(userRepository, userMapper);
        context.setAttribute("userService", userService);

//        List<String> PROTECTED_URIS = List.of(PropertyReader.getProperty("PROTECTED_URIS").split(","));
//        context.setAttribute("PROTECTED_URIS", PROTECTED_URIS);
//        List<String> NOTAUTH_URIS = List.of(PropertyReader.getProperty("NOTAUTH_URIS").split(","));
//        context.setAttribute("NOTAUTH_URIS", NOTAUTH_URIS);
//
//        String PROTECTED_REDIRECT = PropertyReader.getProperty("PROTECTED_REDIRECT");
//        context.setAttribute("PROTECTED_REDIRECT", PROTECTED_REDIRECT);
//        String NOTAUTH_REDIRECT = PropertyReader.getProperty("NOTAUTH_REDIRECT");
//        context.setAttribute("NOTAUTH_REDIRECT", NOTAUTH_REDIRECT);
//
//        String AUTHORIZATION = PropertyReader.getProperty("AUTHORIZATION");
//        context.setAttribute("AUTHORIZATION", AUTHORIZATION);

        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        context.setAttribute("transactionRepository", transactionRepository);
        TransactionMapper transactionMapper = new TransactionMapperImpl();

        TransactionService transactionService = new TransactionServiceImpl(transactionMapper, transactionRepository);
        context.setAttribute( "transactionService", transactionService);
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
}
