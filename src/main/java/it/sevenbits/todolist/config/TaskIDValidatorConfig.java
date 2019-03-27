package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.validation.id.service.ITaskIDValidator;
import it.sevenbits.todolist.core.validation.id.service.TaskIDValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file for TaskIDValidator service.
 */
@Configuration
public class TaskIDValidatorConfig {

    /**
     * This
     * @return ITaskIDValidator instance that presents task id validator service.
     */
    @Bean
    public ITaskIDValidator taskIDValidator() {
        return new TaskIDValidator();
    }
}