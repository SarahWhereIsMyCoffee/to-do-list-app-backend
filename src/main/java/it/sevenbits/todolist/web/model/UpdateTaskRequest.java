package it.sevenbits.todolist.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model separates query logic of updating new task from application business logic
 */
public class UpdateTaskRequest {
    private String text;
    private String status;

    /**
     * Constructor of the model.
     * Creates a JSON object.
     *
     *
     * @param text String Json property
     * @param status String Json property
     */
    @JsonCreator
    public UpdateTaskRequest(@JsonProperty("text") final String text,
                             @JsonProperty("status") final String status) {
        this.text = text;
        this.status = status;
    }

    /**
     * Getter for the text field.
     *
     * @return String text of the model
     */
    public String getText() {
        return text;
    }

    /**
     * Getter for the status field.
     *
     * @return String status of the model
     */
    public String getStatus() {
        return status;
    }
}
