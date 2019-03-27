package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.validation.text.service.ITaskTextValidator;
import it.sevenbits.todolist.core.validation.text.service.TaskTextValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 /**
  * Spring configuration file for TaskTextValidator service.
  */
@Configuration
public class TaskTextValidatorConfig {
    /**
     * This
     * @return ITaskStatusValidator instance that presents task text validator service.
     */
    @Bean
    public ITaskTextValidator taskTextValidator() {
        return new TaskTextValidator();
    }
}