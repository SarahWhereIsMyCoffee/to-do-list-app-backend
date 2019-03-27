package it.sevenbits.todolist.core.validation.text.service;
/**
 * Service provides task text validation.
 */
public class TaskTextValidator implements ITaskTextValidator {
    /**
     * Method that presents validation of passed text status.
     *
     * @param text Task text to validate.
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValidTaskText(final String text) {
        return text != null;
    }
}
