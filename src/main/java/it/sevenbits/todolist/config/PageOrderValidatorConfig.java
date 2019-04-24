package it.sevenbits.todolist.config;

import it.sevenbits.todolist.core.validation.order.IPageOrderValidator;
import it.sevenbits.todolist.core.validation.order.PageOrderValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageOrderValidatorConfig {
    @Bean
    public IPageOrderValidator pageOrderValidator() {
        return new PageOrderValidator();
    }
}
