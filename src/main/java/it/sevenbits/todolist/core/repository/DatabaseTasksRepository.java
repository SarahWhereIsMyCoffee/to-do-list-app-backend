package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(
                "yyyy-MM-dd'T'H:mm:ss+00:00");

        String createdAt = formatForDateNow.format(dateNow);


        Task task = new Task(UUID.randomUUID().toString(),
                addTaskRequest.getText(),
                taskStatus,
                createdAt);

        jdbcOperations.update(
                "INSERT INTO task_V1 (id, text, status, createdAt) VALUES (?, ?, ?, ?)",
                task.getId(),
                task.getText(),
                task.getStatus(),
                task.getCreatedAt()
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
                "SELECT id, text, status, createdAt FROM task_V1",
                (resultSet, i) -> {
                    String id = resultSet.getString("id");
                    String status = resultSet.getString("status");
                    String text = resultSet.getString("text");
                    String createdAt = resultSet.getString("createdAt");
                    return new Task(id, status, text, createdAt);
                });
    }

    /**
     * This method returns a "Task" model from data base taken by ID.
     *
     * @param id String parameter.
     * @return "Task" model.
     */
    @Override
    public Task getTaskByID(final String id) {
        return jdbcOperations.queryForObject(
                "SELECT id, text, status, createdAt FROM task_V1 WHERE id = ?",
                (resultSet, i) -> {
                    String status = resultSet.getString("status");
                    String text = resultSet.getString("text");
                    String createdAt = resultSet.getString("createdAt");
                    return new Task(id, status, text, createdAt);
                },
                id);
    }

    /**
     * This method removes a "Task" model from data base by ID.
     *
     * @param id String parameter
     * @return deleted "Task" model
     */
    @Override
    public Task deleteTask(final String id) {
        final Task task = getTaskByID(id);
        jdbcOperations.update("DELETE FROM task_V1 WHERE id = ?", id);

        return task;
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
    public Task updateTask(final String id, final Task newTask) {
        jdbcOperations.update(
                "UPDATE task_V1 SET text = ?, status = ? WHERE id = ?",
                newTask.getText(),
                newTask.getStatus(),
                id);

        return newTask;
    }
}
