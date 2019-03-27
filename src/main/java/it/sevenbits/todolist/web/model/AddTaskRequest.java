package it.sevenbits.todolist.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.todolist.core.validation.status.constraint.ITaskStatusConstraint;
import it.sevenbits.todolist.core.validation.text.constraint.ITaskTextConstraint;

import javax.validation.constraints.NotBlank;

/**
 * This model separates query logic of adding new task.
 * from application business logic.
 */
public class AddTaskRequest {
    /**
     *
     */
    @NotBlank
    @ITaskTextConstraint
    private String text;

    /**
     *
     */
    @ITaskStatusConstraint
    @NotBlank
    private String status;

    /**
     * Constructor of the model.
     * Creates a JSON object.
     *
     * @param text String Json property.
     * @param status String Json property.
     */
    @JsonCreator
    public AddTaskRequest(@JsonProperty("text") final String text,
                          @JsonProperty("status") final String status) {
        this.text = text;
        this.status = status;
    }

    /**
     * Getter for the text field.
     *
     * @return String text of the model.
     */
    public final String getText() {
        return text;
    }

    /**
     * Getter for the status field.
     *
     * @return String status of the model.
     */
    public final String getStatus() {
        return status;
    }
}
