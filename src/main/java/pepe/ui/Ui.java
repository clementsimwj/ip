package pepe.ui;

import pepe.task.Task;
import pepe.task.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private final String BORDER = "____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);

    //Read pepe.command.Command
    public String readCommand() {
        return scanner.nextLine();
    }

    //Show Border
    public void showLine() {
        System.out.println(BORDER);
    }

    //Greet
    public void uiGreet() {
        System.out.println(BORDER);
        System.out.println("Hello, I am pepe.Pepe!\nHow may I help you today?");
        System.out.println(BORDER);
    }

    //Bye
    public void uiBye() {
        System.out.println(BORDER);
        System.out.println("Aww...so sad to see you leave! :(");
        System.out.println(BORDER);
    }

    //List
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

    //Mark
    public void uiMark(Task task) {
        System.out.println(BORDER);
        System.out.println("Nice! I've marked this pepe.task as done:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    //UnMark
    public void uiUnmark(Task task) {
        System.out.println(BORDER);
        System.out.println("OK, I've marked this pepe.task as not done yet:");
        System.out.println(task);
        System.out.println(BORDER);
    }

    //Todo
    public void uiToDo(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    //Deadline
    public void uiDeadline(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    //Event
    public void uiEvent(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Got it. I've added this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    //Delete
    public void uiDelete(TaskList list, Task task) {
        System.out.println(BORDER);
        System.out.println("Noted. I've removed this pepe.task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println(BORDER);
    }

    //Error
    public void showError(String message) {
        System.out.println(message);
    }


}
