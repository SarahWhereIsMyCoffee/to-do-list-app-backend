package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.repository.DatabaseTasksRepository;
import it.sevenbits.todolist.core.repository.ITasksRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Spring configuration file for ITaskRepository interface.
 */
@Configuration
public class DataBaseTasksRepositoryConfig {
    /**
     * This method presents a Bean of repository based on PostgreSQL.
     *
     * @param jdbcOperations JdbcTemplate instance.
     * @return ITaskRepository instance that represents tasks repository.
     */
    @Bean
    public ITasksRepository dataBaseTasksRepository(
            @Qualifier("tasksJdbcOperations") final JdbcTemplate jdbcOperations) {
        return new DatabaseTasksRepository(jdbcOperations);
    }
}