package it.sevenbits.todolist.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.core.repository.ITasksRepository;
import it.sevenbits.todolist.core.validation.order.IPageOrderValidator;
import it.sevenbits.todolist.web.exceptions.*;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import it.sevenbits.todolist.web.model.UpdateTaskRequest;
import it.sevenbits.todolist.core.validation.id.service.ITaskIDValidator;
import it.sevenbits.todolist.core.validation.status.service.ITaskStatusValidator;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * This class presents Spring REST @Controller controller functionality
 * using for handling different HTTP requests.
 */

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final ITasksRepository dataBaseTasksRepository;
    private final ITaskIDValidator taskIDValidator;
    private final ITaskStatusValidator taskStatusValidator;
    private final IPageOrderValidator pageOrderValidator;

    /**
     * Class constructor.
     *
     * @param dataBaseTasksRepository ITaskRepository instance.
     * @param taskIDValidator ITaskIDValidator service instance.
     * @param taskStatusValidator ITaskStatusValidator service instance.
     */
    public TasksController(final ITasksRepository dataBaseTasksRepository,
                           final ITaskIDValidator taskIDValidator,
                           final ITaskStatusValidator taskStatusValidator,
                           final IPageOrderValidator pageOrderValidator) {
        this.dataBaseTasksRepository = dataBaseTasksRepository;
        this.taskIDValidator = taskIDValidator;
        this.taskStatusValidator = taskStatusValidator;
        this.pageOrderValidator = pageOrderValidator;
    }

    /**
     * Method that returns list of all tasks from task repository.
     * That method handles GET request to "/".

     * @return Response that contains information about:
     *                                -Request body
     *                                -Content type
     *                                -HTTP status
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<JsonNode> getAllTasks(
            @RequestParam(value = "status", required = false) final String status,
            @RequestParam(value = "order", required = false) final String order,
            @RequestParam(value = "page", required = false) final Short page,
            @RequestParam(value = "size", required = false) final Short size) {

        final int pageMinTasksCount = 10;
        final int pageMaxTasksCount = 50;
        if (!taskStatusValidator.isValidOrder(status)) {
            throw new InvalidTaskStatusException();
        }
        if (!pageOrderValidator.isValidOrder(order)) {
            throw new InvalidPageOrderException();
        }
        if (size != null && (size < pageMinTasksCount || size > pageMaxTasksCount)) {
            throw new InvalidPageSizeException();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(dataBaseTasksRepository.getAllTasks(
                        status,
                        order,
                        page,
                        size)
                );
    }

    /**
     * Method that adds new task to repository.
     * That method handles POST request to "/".
     *
     * @param addTaskRequest "Add task" request model. It is required parameter.
     *                       If it is not valid, status code "400 - Bad Request" will be returned.
     * @return Response that contains all the information about the operation status,
     *      content type and location.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Task> createTask(@Valid @RequestBody final AddTaskRequest addTaskRequest) {
        URI location = UriComponentsBuilder
                .fromPath("/tasks/")
                .path(String.valueOf(dataBaseTasksRepository.addTask(addTaskRequest).getId()))
                .build()
                .toUri();

        return ResponseEntity
                .created(location)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .build();
    }

    /**
     * Method that returns task by passed task id.
     * That method handles GET request to "/tasks/{id}".
     *
     * @param id Id of task to return. It is required parameter.
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable("id") final String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = dataBaseTasksRepository.getTaskByID(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(currentTask);
    }
    /**
     *
     * Method that deletes task by passed task id.
     * That method handles DELETE request to "/tasks/{id}".
     *
     * @param id Id of task to remove. It is required parameter.
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Task> deleteTaskByID(@PathVariable("id") final String id) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        Task currentTask = dataBaseTasksRepository.getTaskByID(id);
        if (currentTask == null) {
            throw new TaskNotFoundException();
        }
        dataBaseTasksRepository.deleteTask(id);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * Method that updates task with passed new task text and/or task status by passed task id.
     * That method handles PATCH request to "/tasks/{id}".
     *
     * @param id Id of task to update. It is required parameter.
     * @param updateTaskRequest "Update task" request model.
     *                          If it is not valid, status code "400 - Bad Request" will be returned.
     *
     * @return Response that contains all the information
     *      about the operation status,content type and location.
     */
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> updateTask(@PathVariable("id") final String id,
                                             @Valid @RequestBody final UpdateTaskRequest updateTaskRequest) {
        if (!taskIDValidator.isValidTaskID(id)) {
            throw new InvalidTaskIDException();
        }

        if (updateTaskRequest.getStatus() != null
                && !taskStatusValidator.isValidOrder(updateTaskRequest.getStatus())) {
            throw new InvalidTaskStatusException();
        }

        if (updateTaskRequest.getText() != null
                && updateTaskRequest.getText().equals("")) {
            throw new InvalidTaskTextException();
        }

        if (dataBaseTasksRepository.getTaskByID(id) == null) {
            throw new TaskNotFoundException();
        }

        dataBaseTasksRepository.updateTask(id, new Task(
                        id,
                        Optional.ofNullable(updateTaskRequest.getText())
                                .orElse(dataBaseTasksRepository.getTaskByID(id).getText()),
                        Optional.ofNullable(updateTaskRequest.getStatus())
                                .orElse(dataBaseTasksRepository.getTaskByID(id).getStatus()),
                        dataBaseTasksRepository.getTaskByID(id).getCreatedAt(),
                        dataBaseTasksRepository.getTaskByID(id).getUpdatedAt()
                )
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}