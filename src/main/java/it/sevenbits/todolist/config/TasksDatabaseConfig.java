package it.sevenbits.todolist.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * This class presents wraps Data source into the JdbcTemplate interface.
 */
@Configuration
public class TasksDatabaseConfig {
    /**
     * This method returns JdbcTemplate instance we use to interaction with data base.
     * @param tasksDataSource DataSource instance we wrap.
     * @return JdbcTemplate instance.
     */
    @Bean
    @Qualifier("tasksJdbcOperations")
    public JdbcTemplate tasksJdbcOperations(
            @Qualifier("tasksDataSource")
                    final DataSource tasksDataSource
    ) {
        return new JdbcTemplate(tasksDataSource);
    }
}