package it.sevenbits.todolist.core.model;

import it.sevenbits.todolist.core.validation.id.constraint.TaskIDConstraint;
import it.sevenbits.todolist.core.validation.status.constraint.TaskStatusConstraint;
import it.sevenbits.todolist.core.validation.id.constraint.TaskIDConstraint;
import it.sevenbits.todolist.core.validation.status.constraint.TaskStatusConstraint;

import javax.validation.constraints.NotBlank;

/**
 * This class presents a model used for creating of JSON objects.
 *
 */
public class Task {
    /**
     *
     */
    @NotBlank
    @TaskIDConstraint
    private final String id;
    /**
     *
     */
    @NotBlank
    private final String text;

    /**
     *
     */
    @NotBlank
    @TaskStatusConstraint
    private final String status;


    /**
     *
     */
    @NotBlank
    private final String createdAt;

    /**
     * Model constructor.
     *
     * @param id Task id (must be valid UUID).
     * @param text Task text (can not be null, empty or whitespace string).
     * @param status Task status (must be "inbox" or "done").
     */
    public Task(final String id,
                final String text,
                final String status,
                final String createdAt) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * Task id getter.
     *
     * @return Task id.
     */
    public String getId() {
        return id;
    }

    /**
     * Task text getter.
     *
     * @return Task id.
     */
    public String getText() {
        return text;
    }

    /**
     * Task status getter.
     *
     * @return Task id.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Task createdAt field getter.
     *
     * @return Task createdAt field.
     */
    public String getCreatedAt() {
        return createdAt;
    }

}
