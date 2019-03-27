package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.exceptions.UnavailableMethodException;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

public class DatabaseTasksRepository implements ITasksRepository {
    private final JdbcOperations jdbcOperations;

    public DatabaseTasksRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public String addTask(final AddTaskRequest addTaskRequest) {
        Task task = new Task(UUID.randomUUID().toString(),
                addTaskRequest.getText(),
                addTaskRequest.getStatus());

        jdbcOperations.update(
                "INSERT INTO task (id, status, text) VALUES (?, ?, ?)",
                task.getId(),
                task.getText(),
                task.getStatus()
        );

        return task.getId();
    }

    @Override
    public List<Task> getAllTasks() {
        return jdbcOperations.query(
                "SELECT id, status, text FROM task",
                (resultSet, i) -> {
                    String id = resultSet.getString(1);
                    String status = resultSet.getString(2);
                    String text = resultSet.getString(3);
                    return new Task(id, status, text);
                });
    }

    @Override
    public Task getTask(final String id) {
        throw new UnavailableMethodException();
    }

    @Override
    public Task deleteTask(final String id) {
        throw new UnavailableMethodException();
    }

    @Override
    public Task replaceTask(final String id, final Task newTask) {
        throw new UnavailableMethodException();
    }
}
