package pepe.task.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.task.Task;
import pepe.task.ToDos;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() throws PepeExceptions {
        Task task = new ToDos("Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    void testDeleteTask() throws PepeExceptions {
        Task task1 = new ToDos("Task 1");
        Task task2 = new ToDos("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        Task removed = taskList.deleteTask(0);
        assertEquals(task1, removed);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    void testIsEmpty() throws PepeExceptions {
        assertTrue(taskList.isEmpty());
        taskList.addTask(new ToDos("Task"));
        assertFalse(taskList.isEmpty());
    }

    @Test
    void testGetSize() throws PepeExceptions {
        assertEquals(0, taskList.size());
        taskList.addTask(new ToDos("Task 1"));
        taskList.addTask(new ToDos("Task 2"));
        assertEquals(2, taskList.size());
    }

    @Test
    void testGetTaskListReturnsArrayList() throws PepeExceptions {
        Task task = new ToDos("Task");
        taskList.addTask(task);
        ArrayList<Task> internalList = taskList.getTaskList();
        assertEquals(1, internalList.size());
        assertEquals(task, internalList.get(0));
    }
}
