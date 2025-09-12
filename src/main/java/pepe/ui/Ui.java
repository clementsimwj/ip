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
     * Displays the greeting message when the application starts.
     */
    public String uiGreetUser() {
        String message = "Hello, I am pepe.Pepe!\nHow may I help you today?\n";
        return message;
    }

    /**
     * Displays the farewell message when exiting the application.
     */
    public String uiSayBye() {
        String message = "Aww...so sad to see you leave! :(\n";
        return message;
    }

    /**
     * Displays all tasks in the provided TaskList.
     *
     * @param taskList the TaskList containing tasks to display
     */
    public String uiListTask(TaskList taskList) {
        assert taskList != null : "TaskList should not be null when listing tasks";
        String message = "";
        if (taskList.isEmpty()) {
            message = "Your Task List is Empty...\n";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                assert task != null : "Task at index " + i + " should not be null";
                String taskMessage = (i + 1) + ". " + task + "\n";
                message = message + taskMessage;
            }
        }
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks an array of tasks to be marked
     */
    public String uiMark(Task ... tasks) {
        assert tasks != null : "Tasks to mark should not be null";
        String message = "Nice! I've marked these task(s) as done:\n";
        for (Task task : tasks) {
            message += task.toString() + "\n";
        }
        return message;
    }
    /**
     * Displays a message indicating that a task has been unmarked (not done yet).
     *
     * @param tasks the Task that was unmarked
     */
    public String uiUnmark(Task ... tasks) {
        assert tasks != null : "Task to unmark should not be null";
        String message = "OK, I've unmarked these task(s) as not done yet:\n";
        for (Task task : tasks) {
            message += task.toString() + "\n";
        }
        return message;
    }

    /**
     * Displays a message after adding a ToDo task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddToDo(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding ToDo";
        assert task != null : "Task to add should not be null";
        String message = "Got it. I've added this task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after adding a Deadline task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddDeadline(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding Deadline";
        assert task != null : "Task to add should not be null";
        String message = "Got it. I've added this task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after adding an Event task to the list.
     *
     * @param list the TaskList containing the added task
     * @param task the Task that was added
     */
    public String uiAddEvent(TaskList list, Task task) {
        assert list != null : "TaskList should not be null when adding Event";
        assert task != null : "Task to add should not be null";
        String message = "Got it. I've added this task:\n";
        message = message + (task.toString() + "\n") + "Now you have " + list.size() + " tasks in the list\n";
        return message;
    }

    /**
     * Displays a message after deleting a task from the list.
     *
     * @param list the TaskList after deletion
     * @param tasks the Tasks that were removed
     */
    public String uiDelete(TaskList list, Task ... tasks) {
        assert list != null : "TaskList should not be null when deleting";
        String message = "Noted. I've removed these task(s):\n";
        for (Task task : tasks) {
            message = message + (task.toString() + "\n");
        }
        int remaining = list.size() - tasks.length;
        message += "Now you have " + remaining + " tasks in the list\n";
        return message;
    }

    /**
     * Displays an ArrayList of type Task from the list.
     *
     * @param tasks the TaskList showing matches
     */
    public String uiFind(TaskList tasks) {
        assert tasks != null : "TaskList should not be null when finding tasks";
        String message = "Here are the matching tasks according to your search:\n";
        if (tasks.isEmpty()) {
            message = message + "Hmm...it looks the task you want isn't added yet!\n";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = (i + 1) + ". " + tasks.get(i) + "\n";
                message = message + taskMessage;
            }
        }
        return message;
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
