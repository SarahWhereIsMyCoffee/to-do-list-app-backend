package it.sevenbits.todolist.core.validation.text.constraint;

import it.sevenbits.todolist.core.validation.text.service.ITaskTextValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation of ConstraintValidator interface that provides functionality for task text validation
 * using @ITaskTextConstraint annotation and TaskTextValidator service instance.
 */
public class TaskTextConstraintValidator
        implements ConstraintValidator<ITaskTextConstraint, String> {
    private final ITaskTextValidator taskTextValidator;

    /**
     * Class constructor.
     *
     * @param taskTextValidator ITaskTextValidator service instance.
     */
    @Autowired
    public TaskTextConstraintValidator(final ITaskTextValidator taskTextValidator) {
        this.taskTextValidator = taskTextValidator;
    }

    /**
     * Method that provides task status validation.
     *
     * @param id Task status to validate.
     * @param constraintValidatorContext ConstraintValidationContext instance.
     *
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValid(final String id,
                           final ConstraintValidatorContext constraintValidatorContext) {
        return taskTextValidator.isValidTaskID(id);
    }
}
