package it.sevenbits.todolist.web.exceptions.handler;

import it.sevenbits.todolist.web.exceptions.*;
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
     * @return Response sent to request source. It contains information about content type and HTTP status
     */
    @ExceptionHandler
            (value = {
                    InvalidTaskStatusException.class,
                    InvalidTaskTextException.class,
                    InvalidPageOrderException.class,
                    InvalidPageSizeException.class
            })
    protected ResponseEntity<Object> badRequest(
            final RuntimeException ex, final WebRequest request) {

        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
    /**
     * Method that handles InvalidTaskIDException exceptions.
     *
     * @param ex Instance of exception to handle.
     * @param request WebRequest instance.
     * @return Response sent to request source. It contains information about content type and HTTP status.
     */
    @ExceptionHandler
            (value = {
                    InvalidTaskIDException.class,
                    TaskNotFoundException.class
            })
    protected ResponseEntity<Object> not_found(
            final RuntimeException ex, final WebRequest request) {

        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    /**
     * This method is used when user call for a forbidden method.
     *
     * @param ex Instance of exception to handle.
     * @param request WebRequest instance.
     * @return Response sent to request source. It contains information about content type and HTTP status.
     */
    @ExceptionHandler
            (value = {
                    UnavailableMethodException.class
            })
    protected ResponseEntity<Object> forbidden(
            final RuntimeException ex, final WebRequest request) {

        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN,
                request
        );
    }
}
