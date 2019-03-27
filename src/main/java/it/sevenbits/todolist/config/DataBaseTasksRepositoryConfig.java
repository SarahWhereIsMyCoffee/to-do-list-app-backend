package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.repository.DatabaseTasksRepository;
import it.sevenbits.todolist.core.repository.ITasksRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBaseTasksRepositoryConfig {
    @Bean
    public ITasksRepository dataBaseTasksRepository(
            @Qualifier("tasksJdbcOperations") final JdbcTemplate jdbcOperations) {
        return new DatabaseTasksRepository(jdbcOperations);
    }
}