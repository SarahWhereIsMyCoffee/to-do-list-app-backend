package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyVararg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class DatabaseTasksRepositoryTest {
    private JdbcOperations mockJdbcOperations;
    private DatabaseTasksRepository databaseTasksRepository;

    @Before
    public void setup() {
        mockJdbcOperations = mock(JdbcOperations.class);
        databaseTasksRepository = new DatabaseTasksRepository(mockJdbcOperations);
    }

    @Test
    public void addTask() {
        String expectedTaskText = "";
        AddTaskRequest addTaskRequest = new AddTaskRequest(expectedTaskText);

        Task expectedTask = databaseTasksRepository.addTask(addTaskRequest);

        Assert.assertEquals(expectedTask.getText(), expectedTaskText);
    }

    @Test
    public void getAllTasks() {
        String taskStatus = "inbox";
        List<Task> mockListBooks = mock(List.class);

        when(mockJdbcOperations.query(anyString(), any(RowMapper.class), anyString())).thenReturn(mockListBooks);

        List<Task> expectedList = databaseTasksRepository.getAllTasks();
        verify(mockJdbcOperations, times(1)).query(
                eq("SELECT id, text, status, createdAt, updatedAt FROM task_V2 WHERE status = ?"),
                any(RowMapper.class),
                eq(taskStatus)
        );

        Assert.assertSame(expectedList, mockListBooks);
    }

    @Test
    public void getTaskByID() {
        String taskID = "";
        Task mockTask = mock(Task.class);

        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), anyVararg())).thenReturn(mockTask);

        Task expectedTask = databaseTasksRepository.getTaskByID(taskID);

        verify(mockJdbcOperations, times(1)).queryForObject(
                eq("SELECT id, text, status, createdAt, updatedAt FROM task_V2 WHERE id = ?"),
                any(RowMapper.class),
                eq(taskID)
        );
        Assert.assertSame(expectedTask, mockTask);
    }

    @Test
    public void deleteTaskByID() {
        String taskID = "";
        Task mockTask = mock(Task.class);

        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), anyVararg())).thenReturn(mockTask);
        Task expectedTask = databaseTasksRepository.deleteTask(taskID);

        verify(mockJdbcOperations, times(1)).queryForObject(
                eq("SELECT id, text, status, createdAt, updatedAt FROM task_V2 WHERE id = ?"),
                any(RowMapper.class),
                eq(taskID)
        );
        verify(mockJdbcOperations, times(1)).update(
                eq("DELETE FROM task_V2 WHERE id = ?"),
                eq(taskID)
        );

        Assert.assertSame(mockTask, expectedTask);
    }

    @Test
    public void updateTask() {
        String taskID = "";
        Task mockTask = mock(Task.class);
        Task expectedTask = databaseTasksRepository.updateTask(taskID, mockTask);

        verify(mockJdbcOperations, times(0)).update(
                eq("UPDATE task_V2 SET text = ?, status = ?, updatedAt = ? WHERE id = ?")
        );

        Assert.assertSame(expectedTask, mockTask);
    }
}
