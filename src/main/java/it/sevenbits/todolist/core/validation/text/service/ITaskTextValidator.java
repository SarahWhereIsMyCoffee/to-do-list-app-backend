package it.sevenbits.todolist.core.validation.text.service;

import org.springframework.stereotype.Service;

/**
 * Service provides task text validation.
 */
@Service
public interface ITaskTextValidator {
    /**
     * Method that presents validation of passed task status.
     *
     * @param text Task text to validate.
     * @return Boolean value that represents validation result.
     */
    boolean isValidTaskText(String text);
}
