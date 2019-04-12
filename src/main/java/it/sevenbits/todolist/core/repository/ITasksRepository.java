package it.sevenbits.todolist.core.repository;

import it.sevenbits.todolist.core.model.Task;
import it.sevenbits.todolist.web.model.AddTaskRequest;

import java.util.List;

/**
 * Interface that describes functionality for "task" model repository.
 */
public interface ITasksRepository {
    /**
     * This method provides a new "Task" model for the adding a new object to repository.
     *
     * @param addTaskRequest "Task" model
     * @return new "Task" model id.
     */
    Task addTask(final AddTaskRequest addTaskRequest);

    /**
     * This method returns all the objects from "Task" repository.
     * @return "Task" list.
     */
    List<Task> getAllTasks(final String status,
                           final String order,
                           final short page,
                           final short size);

    /**
     * This method returns a "Task" model from repository taken by ID.
     *
     * @param id String parameter.
     * @return "Task" model.
     */
    Task getTaskByID(final String id);

    /**
     * This method removes a "Task" model from repository taken by ID.
     *
     * @param id String parameter
     * @return deleted "Task" model
     */
    Task deleteTask(final String id);

    /**
     /**
     * This method removes a "Task" model from repository taken by ID.
     *
     * @param id String parameter for define a "Task" model we wanna to replace.
     * @param newTask new "Task" model
     * @return deleted "Task" model.
     */
    Task updateTask(final String id, final Task newTask);
}
