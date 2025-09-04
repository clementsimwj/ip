package pepe.ui;

import java.util.Scanner;

import pepe.task.Task;
import pepe.task.tasklist.TaskList;


/**
 * Handles all user interactions for the Pepe application.
 * <p>
 * This class provides methods to read user input, display messages,
 * and print the state of tasks and task lists in a user-friendly format.
 */
public class Ui {
    private final String BORDER = "____________________________________________________________\n";
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
    public String uiGreetUser() {
        String message = "Hello, I am pepe.Pepe!\nHow may I help you today?\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays the farewell message when exiting the application.
     */
    public String uiSayBye() {
        String message = "Aww...so sad to see you leave! :(\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param taskList the TaskList containing tasks to display
     */
    public String uiListTask(TaskList taskList) {
        StringBuilder message = new StringBuilder();
        if (taskList.isEmpty()) {
            message = new StringBuilder("Your pepe.task.Task List is Empty...\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String task = (i + 1) + ". " + taskList.get(i) + "\n";
                message.append(task);
            }
        }
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the Task that was marked
     */
    public String uiMark(Task task) {
        String message = "Nice! I've marked this pepe.task as done:\n";
        String taskMessage = task.toString() + "\n";
        message = message + taskMessage;
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message indicating that a task has been unmarked (not done yet).
     *
     * @param task the Task that was unmarked
     */
    public String uiUnmark(Task task) {
        String message = "OK, I've marked this pepe.task as not done yet:\n";
        String taskMessage = task.toString() + "\n";
        message = message + taskMessage;
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message after adding a ToDo task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddToDo(TaskList list, Task task) {
        String message = "Got it. I've added this pepe.task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message after adding a Deadline task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddDeadline(TaskList list, Task task) {
        String message = "Got it. I've added this pepe.task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message after adding an Event task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddEvent(TaskList list, Task task) {
        String message = "Got it. I've added this pepe.task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays a message after deleting a task from the list.
     *
     * @param list the TaskList after deletion
     * @param task the Task that was removed
     */
    public String uiDelete(TaskList list, Task task) {
        String message = "Noted. I've removed this task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return (BORDER + message + BORDER);
    }

    /**
     * Displays an ArrayList of type Task from the list.
     *
     * @param tasks the TaskList showing matches
     */
    public String uiFind(TaskList tasks) {
        StringBuilder message = new StringBuilder("Here are the matching tasks according to your search:\n");
        if (tasks.isEmpty()) {
            message.append("Hmm...it looks the task you want isn't added yet!\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = (i + 1) + ". " + tasks.get(i) + "\n";
                message.append(taskMessage);
            }
        }
        return (BORDER + message + BORDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        return message;
    }


}
