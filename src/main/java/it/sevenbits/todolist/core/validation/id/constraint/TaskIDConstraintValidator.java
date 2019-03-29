package it.sevenbits.todolist.core.validation.id.constraint;

import it.sevenbits.todolist.core.validation.id.service.ITaskIDValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation of ConstraintValidator interface that provides functionality for task id validation
 * using @TaskIDConstraint annotation and TaskIDValidator service instance.
 */
public class TaskIDConstraintValidator
        implements ConstraintValidator<TaskIDConstraint, String> {
    private final ITaskIDValidator taskIDValidator;

    /**
     * Class constructor.
     *
     * @param taskIDValidator ITaskIDValidator service instance.
     */
    @Autowired
    public TaskIDConstraintValidator(final ITaskIDValidator taskIDValidator) {
        this.taskIDValidator = taskIDValidator;
    }

    /**
     * Method that provides task id validation.
     *
     * @param id Task id to validate.
     * @param constraintValidatorContext ConstraintValidationContext instance.
     *
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValid(final String id,
                           final ConstraintValidatorContext constraintValidatorContext) {
        return taskIDValidator.isValidTaskID(id);
    }
}
