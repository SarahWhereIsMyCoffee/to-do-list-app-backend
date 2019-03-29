package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.exceptions.UnavailableMethodException;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

/**
 * This class presents a repository that uses PostgreSQL.
 */
public class DatabaseTasksRepository implements ITasksRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * Constructor of TasksRepository class.
     * @param jdbcOperations JdbcOperations instance that presents an interface contains Data source instance
     */
    public DatabaseTasksRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    /**
     * This method provides a new "Task" model for the adding a new object to a data base.
     *
     * @param addTaskRequest "Task" model
     * @return new "Task" model id.
     */
    @Override
    public String addTask(final AddTaskRequest addTaskRequest) {
        String taskStatus = "inbox";
        Task task = new Task(UUID.randomUUID().toString(),
                addTaskRequest.getText(),
                taskStatus);

        jdbcOperations.update(
                "INSERT INTO task (id, status, text) VALUES (?, ?, ?)",
                task.getId(),
                task.getText(),
                task.getStatus()
        );

        return task.getId();
    }
    /**
     * This method returns all the objects from data base.
     * @return "Task" list.
     */
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

    /**
     * This method returns a "Task" model from data base taken by ID.
     *
     * @param id String parameter.
     * @return "Task" model.
     */
    @Override
    public Task getTask(final String id) {
        throw new UnavailableMethodException();
    }

    /**
     * This method removes a "Task" model from data base by ID.
     *
     * @param id String parameter
     * @return deleted "Task" model
     */
    @Override
    public Task deleteTask(final String id) {
        throw new UnavailableMethodException();
    }

    /**
     /**
     * This method update a "Task" model in data base by ID.
     *
     * @param id String parameter for define a "Task" model we wanna to replace.
     * @param newTask new "Task" model
     * @return deleted "Task" model.
     */
    @Override
    public Task replaceTask(final String id, final Task newTask) {
        throw new UnavailableMethodException();
    }
}
