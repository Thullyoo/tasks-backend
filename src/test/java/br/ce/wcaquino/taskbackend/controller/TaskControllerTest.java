package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void shouldntSaveTaskWithoutDescription() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class,
                () -> taskController.save(todo)
        );

        Assertions.assertEquals("Fill the task description", exception.getMessage());
    }

    @Test
    public void shouldntSaveTaskWithoutDate() {
        Task todo = new Task();
        todo.setTask("description");

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class,
                () -> taskController.save(todo)
        );

        Assertions.assertEquals("Fill the due date", exception.getMessage());
    }

    @Test
    public void shouldntSaveTaskWithPastDate() {
        Task todo = new Task();
        todo.setTask("description");
        todo.setDueDate(LocalDate.of(2000, 1, 1));

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class,
                () -> taskController.save(todo)
        );

        Assertions.assertEquals("Due date must not be in past", exception.getMessage());
    }

    @Test
    public void shouldSaveTaskWithSuccess() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Description");
        todo.setDueDate(LocalDate.now());

        taskController.save(todo);

        Mockito.verify(taskRepo).save(todo);
    }
}
