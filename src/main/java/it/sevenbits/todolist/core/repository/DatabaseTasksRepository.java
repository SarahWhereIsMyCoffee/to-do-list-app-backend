package it.sevenbits.todolist.core.repository;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.exceptions.InvalidPageOrderException;
import it.sevenbits.todolist.web.exceptions.InvalidTaskStatusException;
import it.sevenbits.todolist.web.model.AddTaskRequest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class presents a repository that uses PostgreSQL.
 */
public class DatabaseTasksRepository implements ITasksRepository {
    private final JdbcOperations jdbcOperations;
    private final List<String> statusList;
    private final List<String> orderList;
    private final JsonNodeFactory jsonNodeFactory;

    /**
     * Constructor of HashMapTasksRepository class.
     *
     * @param jdbcOperations JdbcOperations instance that presents an interface contains Data source instance
     */
    public DatabaseTasksRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;

        jsonNodeFactory = JsonNodeFactory.instance;

        statusList = new ArrayList<>();
        orderList = new ArrayList<>();
        Collections.addAll(statusList, "inbox", "done");
        Collections.addAll(orderList, "desc", "asc");
    }

    /**
     * This method provides a new "Task" model for the adding a new object to a data base.
     *
     * @param addTaskRequest "Task" model
     * @return new "Task" model id.
     */
    @Override
    public Task addTask(final AddTaskRequest addTaskRequest) {
        String taskStatus = "inbox";
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(
                "yyyy-MM-dd'T'H:mm:ss+00:00");

        String createdAt = formatForDateNow.format(dateNow);
        String updatedAt = createdAt;

        Task task = new Task(UUID.randomUUID().toString(),
                addTaskRequest.getText(),
                taskStatus,
                createdAt,
                updatedAt);

        jdbcOperations.update(
                "INSERT INTO task (id, text, status, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)",
                task.getId(),
                task.getText(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );

        return task;
    }

    /**
     * This method returns all the objects from data base.
     *
     * @return "Task" list.
     */
    @Override
    public List<Task> getAllTasks(final String status,
                                  final String order,
                                  final short page,
                                  final short size) {

        if (!statusList.contains(status)) {
            throw new InvalidTaskStatusException();
        }
        if (!orderList.contains(order)) {
            throw new InvalidPageOrderException();
        }

        int skippedTasksCount = (page - 1) * size;
        int totalTaskCount = getTotalTaskCount(status);
        int

        ObjectNode rootNode = jsonNodeFactory.objectNode();
        ObjectNode metaNode = rootNode.putObject("_meta");
        ObjectNode tasksNode = rootNode.putObject("tasks");

        final UriComponents uriPartBeforePageNumber = UriComponentsBuilder.fromPath("/tasks")
                .queryParam("status", status)
                .queryParam("order", order)
                .build();
        final UriComponents uriPartAfterPageNumber = UriComponentsBuilder.fromPath("/tasks")
                .queryParam("size", size)
                .build();

        String nextPageLink = UriComponentsBuilder.newInstance()
                .uriComponents(uriPartBeforePageNumber)
                .queryParam("page", page + 1)
                .build()
                .toString();
        String nextPageLink = UriComponentsBuilder.newInstance()
                .uriComponents(uriPartBeforePageNumber)
                .queryParam("page", page + 1)
                .build()
                .toString();
        String nextPageLink = UriComponentsBuilder.newInstance()
                .uriComponents(uriPartBeforePageNumber)
                .queryParam("page", page + 1)
                .build()
                .toString();
        String nextPageLink = UriComponentsBuilder.newInstance()
                .uriComponents(uriPartBeforePageNumber)
                .queryParam("page", page + 1)
                .build()
                .toString();

        metaNode.put("total", totalTaskCount)
                .put("page", page)
                .put("size", size)
                .put("next", nextPageLink);


        return jdbcOperations.query(
                "SELECT id, text, status, createdAt, updatedAt FROM task WHERE status = ?" +
                        "ORDER BY createdAt " + order + " OFFSET ? LIMIT ?",
                (resultSet, i) -> {
                    String id = resultSet.getString("id");
                    String text = resultSet.getString("text");
                    String createdAt = resultSet.getString("createdAt");
                    String updatedAt = resultSet.getString("updatedAt");
                    return new Task(
                            id,
                            text,
                            status,
                            createdAt,
                            updatedAt);
                },
                status,
                skippedTasksCount,
                size);
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
                "SELECT id, text, status, createdAt, updatedAt FROM task WHERE id = ?",
                (resultSet, i) -> {
                    String status = resultSet.getString("status");
                    String text = resultSet.getString("text");
                    String createdAt = resultSet.getString("createdAt");
                    String updatedAt = resultSet.getString("updatedAt");
                    return new Task(id,
                            status,
                            text,
                            createdAt,
                            updatedAt);
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
        jdbcOperations.update("DELETE FROM task WHERE id = ?", id);

        return task;
    }

    /**
     * /**
     * This method update a "Task" model in data base by ID.
     *
     * @param id      String parameter for define a "Task" model we wanna to replace.
     * @param newTask new "Task" model
     * @return deleted "Task" model.
     */
    @Override
    public Task updateTask(final String id, final Task newTask) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(
                "yyyy-MM-dd'T'H:mm:ss+00:00");

        String updatedAt = formatForDateNow.format(dateNow);

        jdbcOperations.update(
                "UPDATE task SET text = ?, status = ?, updatedAt = ? WHERE id = ?",
                newTask.getText(),
                newTask.getStatus(),
                updatedAt,
                id);

        return newTask;
    }

    private int getTotalTaskCount(final String status) {
        return Optional.ofNullable(jdbcOperations.queryForObject(
                "SELECT COUNT(*) FROM task WHERE status = ?",
                Integer.class,
                status
        )).orElse(0);
    }
}
