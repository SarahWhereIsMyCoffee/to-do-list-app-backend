package it.sevenbits.todolist.core.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void taskModelTest() {
        String text = "Task 1 text";
        String status = "inbox";
        UUID id = UUID.randomUUID();
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(
                "yyyy-MM-dd'T'H:mm:ss+00:00");

        String createdAt = formatForDateNow.format(dateNow);
        String updatedAt = createdAt;

        Task task = new Task(id.toString(),
                text,
                status,
                createdAt,
                updatedAt);


        assertEquals(id.toString(), task.getId());
        assertEquals(text, task.getText());
        assertEquals(status, task.getStatus());
        assertEquals(createdAt, task.getCreatedAt());
        assertEquals(updatedAt, task.getUpdatedAt());
    }
}
