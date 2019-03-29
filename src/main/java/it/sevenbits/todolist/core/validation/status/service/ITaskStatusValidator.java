package it.sevenbits.todolist.core.validation.status.service;

import org.springframework.stereotype.Service;

/**
 * Service provides task status validation.
 */
@Service
public interface ITaskStatusValidator {
    /**
     * Method that presents validation of passed task status.
     *
     * @param status Task status to validate.
     * @return Boolean value that represents validation result.
     */
    boolean isValidStatus(String status);
}
