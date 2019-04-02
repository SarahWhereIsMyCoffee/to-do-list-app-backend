package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.repository.ITasksRepository;
import it.sevenbits.todolist.core.repository.HashMapTasksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Spring configuration file for ITaskRepository interface.
 */
@Configuration
public class HashMapTasksRepositoryConfig {
    /**
     * This method returns ITaskRepository instance.
     *
     * @return ITaskRepository instance that represents tasks repository.
     */
    @Bean
    public ITasksRepository tasksRepository() {
        return new HashMapTasksRepository();
    }
}