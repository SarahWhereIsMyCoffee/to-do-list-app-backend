package it.sevenbits.todolist.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * This model separates query logic of adding new task.
 * from application business logic.
 */
public class AddTaskRequest {
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
