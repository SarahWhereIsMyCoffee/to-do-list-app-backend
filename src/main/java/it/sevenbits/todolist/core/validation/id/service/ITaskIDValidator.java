package it.sevenbits.todolist.core.validation.id.service;

import org.springframework.stereotype.Service;

/**
 * Service provides task id validation.
 */
@Service
public interface ITaskIDValidator {

    /**
     * Method that presents validation of passed task id.
     *
     * @param id Task id to validate.
     * @return Boolean value that represents validation result.
     */
    boolean isValidTaskID(String id);
}
