package it.sevenbits.todolist.web.controllers;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.core.repository.DatabaseTasksRepository;
import it.sevenbits.todolist.core.validation.id.service.ITaskIDValidator;
import it.sevenbits.todolist.core.validation.id.service.TaskIDValidator;
import it.sevenbits.todolist.core.validation.status.service.ITaskStatusValidator;
import it.sevenbits.todolist.core.validation.status.service.TaskStatusValidator;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import it.sevenbits.todolist.web.model.UpdateTaskRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TaskControllerTest {
    private TasksController tasksController;
    private DatabaseTasksRepository mockDatabaseTasksRepository;
    private ITaskIDValidator taskIDValidator;
    private ITaskStatusValidator taskStatusValidator;

    @Before
    public void setup() {
        mockDatabaseTasksRepository = mock(DatabaseTasksRepository.class);
        taskIDValidator = mock(TaskIDValidator.class);
        taskStatusValidator = mock(TaskStatusValidator.class);
        tasksController = new TasksController(mockDatabaseTasksRepository,
                taskIDValidator,
                taskStatusValidator);

        when(taskIDValidator.isValidTaskID(anyString())).thenReturn(true);
        when(taskStatusValidator.isValidStatus(anyString())).thenReturn(true);
    }

    @Test
    public void createTask() {
        Task task = mock(Task.class);
        UUID taskID = UUID.randomUUID();
        when(task.getId()).thenReturn(taskID.toString());
        AddTaskRequest addTaskRequest = mock(AddTaskRequest.class);
        when(mockDatabaseTasksRepository.addTask(addTaskRequest)).thenReturn(task);


        ResponseEntity<Task> createTaskAnswer = tasksController.createTask(addTaskRequest);
        verify(mockDatabaseTasksRepository, times(1)).addTask(addTaskRequest);


        assertEquals(UriComponentsBuilder.fromPath("/tasks/").path(taskID.toString()).build().toUri(),
                createTaskAnswer.getHeaders().getLocation());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, createTaskAnswer.getHeaders().getContentType());
        assertEquals(HttpStatus.CREATED, createTaskAnswer.getStatusCode());
        assertNull(createTaskAnswer.getBody());
    }

    @Test
    public void getAllTasks() {
        List<Task> taskList = mock(List.class);
        when(mockDatabaseTasksRepository.getAllTasks()).thenReturn(taskList);


        ResponseEntity<List<Task>> getAllTasksAnswer = tasksController.getAllTasks();
        verify(mockDatabaseTasksRepository, times(1)).getAllTasks();


        assertEquals(HttpStatus.OK, getAllTasksAnswer.getStatusCode());
        assertSame(taskList, getAllTasksAnswer.getBody());
    }

    @Test
    public void getTaskByID() {
        Task task = mock(Task.class);
        when(mockDatabaseTasksRepository.getTaskByID(anyString())).thenReturn(task);


        ResponseEntity<Task> getTaskByIDAnswer = tasksController.getTaskByID(anyString());
        verify(mockDatabaseTasksRepository, times(1)).getTaskByID(anyString());


        assertEquals(MediaType.APPLICATION_JSON_UTF8, getTaskByIDAnswer.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, getTaskByIDAnswer.getStatusCode());
        assertSame(task, getTaskByIDAnswer.getBody());
    }

    @Test
    public void deleteTaskByID() {
        Task task = mock(Task.class);
        when(mockDatabaseTasksRepository.getTaskByID(anyString())).thenReturn(task);


        ResponseEntity<Task> deleteTaskByIDAnswer = tasksController.deleteTaskByID(anyString());
        verify(mockDatabaseTasksRepository, times(1)).deleteTask(anyString());


        assertNull(deleteTaskByIDAnswer.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, deleteTaskByIDAnswer.getStatusCode());
        assertSame(null, deleteTaskByIDAnswer.getBody());
    }

    @Test
    public void updateTask() {
        Task task = mock(Task.class);
        UpdateTaskRequest updateTaskRequest = mock(UpdateTaskRequest.class);
        when(mockDatabaseTasksRepository.getTaskByID(anyString())).thenReturn(task);


        ResponseEntity<Task> updateTaskAnswer = tasksController.updateTask(
                anyString(),
                updateTaskRequest
        );


        assertNull(updateTaskAnswer.getHeaders().getContentType());
        assertEquals(HttpStatus.NO_CONTENT, updateTaskAnswer.getStatusCode());
        assertSame(null, updateTaskAnswer.getBody());
    }
}
