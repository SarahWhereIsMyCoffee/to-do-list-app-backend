package it.sevenbits.todolist.core.validation.id.service;

import java.util.regex.Pattern;

/**
 * Service provides task id validation.
 */
public class TaskIDValidator implements ITaskIDValidator {
    /**
     * Method that presents validation of passed task id.
     *
     * @param id Task id to validate.
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValidTaskID(final String id) {
        String reg = "([a-fA-F0-9]{8}" +
                "-[a-fA-F0-9]{4}" +
                "-[a-fA-F0-9]{4}" +
                "-[a-fA-F0-9]{4}" +
                "-[a-fA-F0-9]{12})";
        return Pattern.matches(reg, id);
    }
}
