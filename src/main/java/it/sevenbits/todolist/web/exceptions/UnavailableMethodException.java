package it.sevenbits.todolist.web.exceptions;

/**
 * This Exception throws when user calls for a blank API method
 */
public class UnavailableMethodException extends RuntimeException {

    /**
     * Constructor of the class.
     */
    public UnavailableMethodException() {
        super();
    }
}
