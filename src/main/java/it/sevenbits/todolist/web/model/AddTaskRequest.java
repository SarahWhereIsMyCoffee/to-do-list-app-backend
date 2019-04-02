package it.sevenbits.todolist.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


/**
 * This model separates query logic of adding new task.
 * from application business logic.
 */
public class AddTaskRequest {
    @NotBlank
    private String text;

    /**
     * Constructor of the model.
     * Creates a JSON object.
     *
     * @param text String Json property.
     */
    @JsonCreator
    public AddTaskRequest(@JsonProperty("text") final String text) {
        this.text = text;
    }

    /**
     * Getter for the text field.
     *
     * @return String text of the model.
     */
    public final String getText() {
        return text;
    }
}
