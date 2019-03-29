package it.sevenbits.todolist.core.validation.status.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for task status validation using ConstraintValidator interface implementation.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatusConstraintValidator.class)
public @interface TaskStatusConstraint {
    /**
     * Attribute that represents error message.
     *
     * @return Error message.
     */
    String message() default "{value.negative}";

    /**
     * Attribute that allows specification of validation groups.
     *
     * @return Array of specified validation groups.
     */
    Class<?>[] groups() default {};
    /**
     * Attribute that can be used by clients of Bean Validation API to assign custom payload objects to a constraint.
     *
     * @return Array of custom payload objects assigned by clients of Bean Validation API.
     */
    Class<? extends Payload>[] payload() default {};
}