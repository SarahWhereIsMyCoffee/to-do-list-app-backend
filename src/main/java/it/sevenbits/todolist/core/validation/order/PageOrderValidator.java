package it.sevenbits.todolist.core.validation.order;

import it.sevenbits.todolist.core.validation.status.service.ITaskStatusValidator;

/**
 * Service provides page order validation.
 */
public class PageOrderValidator implements IPageOrderValidator {
    /**
     * Method that presents validation of passed page order.
     *
     * @param order Page order to validate.
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValidOrder(final String order) {
        String desc = "desc";
        String asc = "asc";
        return order == null || order.equals(desc) || order.equals(asc);
    }
}
