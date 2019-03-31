package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.model.AddTaskRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Task ITasksRepository implementation.
 */
public class TasksRepository implements ITasksRepository {
    private Map<String, Task> taskMap;

    /**
     * Constructor of TasksRepository class.
     */
    public TasksRepository() {
        taskMap = new HashMap<>();
    }

    /**
     * This method provides a new "Task" model for the adding a new object to repository.
     *
     * @param addTaskRequest "Task" model
     * @return new "Task" model id.
     */
    @Override
    public String addTask(final AddTaskRequest addTaskRequest) {
        UUID taskID = UUID.randomUUID();
        String taskStatus = "inbox";
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(
                "yyyy-MM-dd'T'H:mm:ss+00:00");

        String createdAt = formatForDateNow.format(dateNow);

        taskMap.put(taskID.toString(),
                new Task(taskID.toString(),
                        addTaskRequest.getText(),
                        taskStatus,
                        createdAt));
        return taskID.toString();
    }

    /**
     * This method returns all the objects from "Task" repository.
     * @return "Task" list.
     */
    @Override
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(new ArrayList<>(taskMap.values()));
    }

    /**
     * This method returns a "Task" model from repository taken by ID.
     *
     * @param id String parameter.
     * @return "Task" model.
     */
    @Override
    public Task getTaskByID(final String id) {
        return taskMap.get(id);
    }

    /**
     * This method removes a "Task" model from repository by ID.
     *
     * @param id String parameter
     * @return deleted "Task" model
     */
    @Override
    public Task deleteTask(final String id) {
        return taskMap.remove(id);
    }

    /**
     /**
     * This method replaces a "Task" model from repository by ID.
     *
     * @param id String parameter for define a "Task" model we wanna to replace.
     * @param newTask new "Task" model
     * @return deleted "Task" model.
     */
    @Override
    public Task updateTask(final String id, final Task newTask) {
        return taskMap.replace(id, newTask);
    }
}
