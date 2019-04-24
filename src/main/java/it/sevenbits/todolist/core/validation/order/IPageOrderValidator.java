package it.sevenbits.todolist.core.validation.order;

import org.springframework.stereotype.Service;

/**
 * Service provides task status validation.
 */
@Service
public interface IPageOrderValidator {
    /**
     * Method that presents validation of passed task status.
     *
     * @param order Task status to validate.
     * @return Boolean value that represents validation result.
     */
    boolean isValidOrder(String order);
}
