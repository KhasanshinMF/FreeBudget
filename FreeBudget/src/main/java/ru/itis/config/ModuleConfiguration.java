package ru.itis.config;

import lombok.experimental.UtilityClass;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.util.PropertyReader;

import javax.sql.DataSource;

@UtilityClass
public class ModuleConfiguration {

    private final JdbcTemplate jdbcTempate = new JdbcTemplate(dataSource());

    public JdbcTemplate jdbcTemplate() {
        return jdbcTempate;
    }

    private DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_USER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }
}
