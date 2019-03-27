package it.sevenbits.todolist.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class TasksDatabaseConfig {
    @Bean
    @Qualifier("tasksJdbcOperations")
    public JdbcTemplate tasksJdbcOperations(
            @Qualifier("tasksDataSource")
                    final DataSource tasksDataSource
    ) {
        return new JdbcTemplate(tasksDataSource);
    }
}