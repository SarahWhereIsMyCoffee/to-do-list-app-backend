package it.sevenbits.todolist.core.model;

/**
 * This class presents a model used for creating of JSON objects.
 *
 */
public class Task {
    private final String id;
    private final String text;
    private final String status;

    /**
     * Model constructor.
     *
     * @param id Task id (must be valid UUID).
     * @param text Task text (can not be null, empty or whitespace string).
     * @param status Task status (must be "inbox" or "done").
     */
    public Task(final String id, final String text, final String status) {
        this.id = id;
        this.text = text;
        this.status = status;
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
}
