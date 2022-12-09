package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static br.ce.wcaquino.taskbackend.controller.TaskController.*;

public class TaskControllerTest {

    private static final String ASSERT_FAIL_MESSAGE = "shouldn't get to this point";

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
            Assert.fail(ASSERT_FAIL_MESSAGE);
        } catch (ValidationException e ) {
            Assert.assertEquals(VALIDATION_EXCEPTION_TO_DESCRIPTION_EMPTY, e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        Task todo = new Task();
        todo.setTask("description");
        try {
            controller.save(todo);
            Assert.fail(ASSERT_FAIL_MESSAGE);
        } catch (ValidationException e ) {
            Assert.assertEquals(VALIDATION_EXCEPTION_TO_DUE_DATE_FILL, e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        Task todo = new Task();
        todo.setTask("description");
        todo.setDueDate(LocalDate.of(2010, 1, 1));
        try {
            controller.save(todo);
            Assert.fail(ASSERT_FAIL_MESSAGE);
        } catch (ValidationException e ) {
            Assert.assertEquals(VALIDATION_EXCEPTION_TO_DUE_DATE_NOT_BE_IN_PAST, e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComSucesso() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Description");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);

        Mockito.verify(taskRepo).save(todo);
    }

}
