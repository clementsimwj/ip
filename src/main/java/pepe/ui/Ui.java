package pepe.ui;

import java.util.Scanner;

import pepe.Pepe;
import pepe.exception.PepeExceptions;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;


/**
 * Handles all user interactions for the Pepe application.
 * <p>
 * This class provides methods to read user input, display messages,
 * and print the state of tasks and task lists in a user-friendly format.
 */
public class Ui {
    private final String BORDER = "____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);



    /**
     * Reads the next line of user input from the console.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal border line for UI separation.
     */
    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Displays the greeting message when the application starts.
     */
    public void uiGreet() {
        System.out.println(BORDER);
        System.out.println("Hello, I am pepe.Pepe!\nHow may I help you today?");
        System.out.println(BORDER);
    }

    /**
     * Displays the farewell message when exiting the application.
     */
    public void uiBye() {
        System.out.println(BORDER);
        System.out.println("Aww...so sad to see you leave! :(");
        System.out.println(BORDER);
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param taskList the TaskList containing tasks to display
     */
    public void uiList(TaskList taskList) {
        System.out.println(BORDER);
        if (taskList.isEmpty()) {
            System.out.println("Your pepe.task.Task List is Empty...");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println(BORDER);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the Task that was marked
     */
    public void uiMark(Task task) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this pepe.task as done:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    /**
     * Displays a message indicating that a task has been unmarked (not done yet).
     *
     * @param task the Task that was unmarked
     */
    public void uiUnmark(Task task) {
        System.out.println(BORDER);
        System.out.println("OK, I've marked this pepe.task as not done yet:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    /**
     * Displays a message after adding a ToDo task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public void uiToDo(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    /**
     * Displays a message after adding a Deadline task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public void uiDeadline(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    /**
     * Displays a message after adding an Event task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public void uiEvent(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    /**
     * Displays a message after deleting a task from the list.
     *
     * @param list the TaskList after deletion
     * @param task the Task that was removed
     */
    public void uiDelete(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Noted. I've removed this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    /**
     * Displays an ArrayList of type Task from the list.
     *
     * @param tasks the TaskList showing matches
     */
    public void uiFind(TaskList tasks) {
        System.out.println(BORDER);
        System.out.println("Here are the matching tasks according to your search:");
        if (tasks.isEmpty()) {
            System.out.println("Hmm...it looks the task you want isn't added yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(BORDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }


}
