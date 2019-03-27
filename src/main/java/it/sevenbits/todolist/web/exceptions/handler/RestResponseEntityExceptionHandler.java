package it.sevenbits.todolist.web.exceptions.handler;

import it.sevenbits.todolist.web.exceptions.InvalidTaskIDException;
import it.sevenbits.todolist.web.exceptions.InvalidTaskStatusException;
import it.sevenbits.todolist.web.exceptions.InvalidTaskTextException;
import it.sevenbits.todolist.web.exceptions.TaskNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class that provides exception handling throughout web part of project.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

    /**
     * Method that handles InvalidTaskStatusException, InvalidTaskTextException exceptions.
     *
     * @param ex Instance of exception to handle.
     * @param request WebRequest instance.
     * @return Response sent to request source. It contains:
     *      - header "Content-Type": "application/json;charset=UTF-8";
     *      - status code: "400 - Bad Request".
     */
    @ExceptionHandler
            (value = {
            InvalidTaskStatusException.class,
            InvalidTaskTextException.class})
    protected ResponseEntity<Object> invalidStatus(
            final RuntimeException ex, final WebRequest request) {
        String bodyOfResponse = "Invalid task parameters";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * Method that handles InvalidTaskIDException exceptions.
     *
     * @param ex Instance of exception to handle.
     * @param request WebRequest instance.
     * @return Response sent to request source. It contains:
     *      - header "Content-Type": "application/json;charset=UTF-8";
     *      - status code: "400 - Bad Request".
     */
    @ExceptionHandler
            (value = {InvalidTaskIDException.class})
    protected ResponseEntity<Object> invalidID(
            final RuntimeException ex, final WebRequest request) {
        String bodyOfResponse = "Invalid task ID";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    /**
     * Method that handles TaskNotFoundException exceptions.
     *
     * @param ex Instance of exception to handle.
     * @param request WebRequest instance.
     * @return Response sent to request source. It contains:
     *      - header "Content-Type": "application/json;charset=UTF-8";
     *      - status code: "400 - Bad Request".
     */
    @ExceptionHandler
            (value = {TaskNotFoundException.class})
    protected ResponseEntity<Object> invalidTask(
            final RuntimeException ex, final WebRequest request) {
        String bodyOfResponse = "Task not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
