package pepe.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

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
    void testUiGreet() {
        String output = ui.showUiGreetUser();
        assertTrue(output.contains("Hello, I am pepe.Pepe!\nHow may I help you today?\n"));
    }

    @Test
    void testUiBye() {
        String output = ui.showUiSayBye();
        assertTrue(output.contains("Aww...so sad to see you leave! :("));
    }

    @Test
    void testUiListEmpty() {
        TaskList list = new TaskList();
        String output = ui.showUiListTask(list);
        assertTrue(output.contains("Your Task List is Empty..."));
    }

    @Test
    void testUiMark() {
        Task task = new ToDos("Test task");
        String output = ui.showUiMark(task);
        assertTrue(output.contains("Nice! I've marked these task(s) as done:"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiUnmark() {
        Task task = new ToDos("Test task");
        String output = ui.showUiUnmark(task);
        assertTrue(output.contains("OK, I've unmarked these task(s) as not done yet:"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiToDo() {
        Task task = new ToDos("Test task");
        TaskList list = new TaskList();
        list.addTask(task);
        String output = ui.showUiAddToDo(list, task);
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Test task"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }
}
