package it.sevenbits.todolist.core.validation.status.constraint;

import it.sevenbits.todolist.core.validation.status.service.ITaskStatusValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation of ConstraintValidator interface that provides functionality for task status validation
 * using @TaskStatusConstraint annotation and TaskStatusValidator service instance.
 */
public class TaskStatusConstraintValidator implements ConstraintValidator<TaskStatusConstraint, String> {
    private final ITaskStatusValidator taskStatusValidator;
    /**
     * Class constructor.
     *
     * @param taskStatusValidator ITaskStatusValidator service instance.
     */
    @Autowired
    public TaskStatusConstraintValidator(final ITaskStatusValidator taskStatusValidator) {
        this.taskStatusValidator = taskStatusValidator;
    }


    /**
     * Method that provides task status validation.
     *
     * @param status Task status to validate.
     * @param constraintValidatorContext ConstraintValidationContext instance.
     *
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValid(final String status, final ConstraintValidatorContext constraintValidatorContext) {
        return taskStatusValidator.isValidOrder(status);
    }
}