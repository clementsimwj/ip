package pepe.ui;

import org.junit.jupiter.api.*;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testShowLine() {
        ui.showLine();
        String output = outContent.toString();
        assertTrue(output.contains("____________________________________________________________"));
    }

    @Test
    void testUiGreet() {
        ui.uiGreet();
        String output = outContent.toString();
        assertTrue(output.contains("Hello, I am pepe.Pepe!"));
        assertTrue(output.contains("How may I help you today?"));
    }

    @Test
    void testUiBye() {
        ui.uiBye();
        String output = outContent.toString();
        assertTrue(output.contains("Aww...so sad to see you leave! :("));
    }

    @Test
    void testUiListEmpty() {
        TaskList list = new TaskList();
        ui.uiList(list);
        String output = outContent.toString();
        assertTrue(output.contains("Your pepe.task.Task List is Empty..."));
    }

    @Test
    void testUiMark() {
        Task task = new ToDos("Test task");
        ui.uiMark(task);
        String output = outContent.toString();
        assertTrue(output.contains("Nice! I've marked this pepe.task as done:"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiUnmark() {
        Task task = new ToDos("Test task");
        ui.uiUnmark(task);
        String output = outContent.toString();
        assertTrue(output.contains("OK, I've marked this pepe.task as not done yet:"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiToDo() {
        Task task = new ToDos("Test task");
        TaskList list = new TaskList();
        list.addTask(task);
        ui.uiToDo(list, task);
        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this pepe.task:"));
        assertTrue(output.contains("Test task"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void testShowError() {
        ui.showError("Error message");
        String output = outContent.toString();
        assertTrue(output.contains("Error message"));
    }
}
