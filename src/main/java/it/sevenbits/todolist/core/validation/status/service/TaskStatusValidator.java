package it.sevenbits.todolist.core.validation.status.service;

/**
 * Service provides task status validation.
 */
public class TaskStatusValidator implements ITaskStatusValidator {
    /**
     * Method that presents validation of passed task status.
     *
     * @param status Task status to validate.
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValidOrder(final String status) {
        String taskStatusDone = "done";
        String taskStatusInbox = "inbox";
        return status == null || status.equals(taskStatusDone) || status.equals(taskStatusInbox);
    }
}
