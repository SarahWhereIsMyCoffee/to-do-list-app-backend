package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.validation.status.service.ITaskStatusValidator;
import it.sevenbits.todolist.core.validation.status.service.TaskStatusValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file for TaskStatusValidator service.
 */
@Configuration
public class TaskStatusValidatorConfig {
    /**
     * This
     * @return ITaskStatusValidator instance that presents task status validator service.
     */
    @Bean
    public ITaskStatusValidator taskStatusValidator() {
        return new TaskStatusValidator();
    }
}