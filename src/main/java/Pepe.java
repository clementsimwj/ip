import java.util.ArrayList;
import java.util.Scanner;

public class Pepe {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String border = "____________________________________________________________";
        String input;
        ArrayList<Task> list = new ArrayList<>(100);
        int counter = 0;

        System.out.println(border);
        System.out.println("Hello, I am Pepe!\n" + "How may I help you today?" );
        System.out.println(border);

        while (true) {
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("bye")) {
                System.out.println(border);
                System.out.println("Aww...so sad to see you leave! :(");
                System.out.println(border);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(border);
                if (list.get(0) == null) {
                    System.out.println("Your Task List is Empty!");
                } else {
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                }
                System.out.println(border);
            } else if (input.startsWith("mark")) {
                try {
                    if (input.substring(4).isBlank()) {
                        throw new PepeExceptions("To mark a task: mark <task-index>");
                    }
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (list.size() <= index) {
                        throw new PepeExceptions("There is no task at index: " + (index+1));
                    }
                    Task task = list.get(index);
                    task.markTask();
                    System.out.println(border);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    System.out.println(border);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("To mark a task: mark <task-index> (task-index must be a valid number)");
                    System.out.println(border);
                } catch (PepeExceptions e) {
                    System.out.println(border);
                    System.out.println(e);
                    System.out.println(border);
                }
            }
            else if (input.startsWith("unmark")) {
                try {
                    if (input.substring(6).isBlank()) {
                        throw new PepeExceptions("To unmark a task: unmark <task-index>");
                    }
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (list.size() <= index) {
                        throw new PepeExceptions("There is no task at index: " + (index+1));
                    }
                    Task task = list.get(index);
                    task.unmarkTask();
                    System.out.println(border);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    System.out.println(border);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("To unmark a task: unmark <task-index> (task-index must be a valid number)");
                    System.out.println(border);
                } catch (PepeExceptions e) {
                    System.out.println(border);
                    System.out.println(e);
                    System.out.println(border);
                }
            } else if (input.startsWith("todo")) {
                try {
                    String desc = input.substring(4);
                    if (desc.isBlank()) {
                        throw new Exception("String cannot be Empty!");
                    }
                    Task t = new ToDos(desc);
                    list.add(t);
                    counter++;
                    System.out.println(border);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + counter + " tasks in the list");
                    System.out.println(border);
                } catch (Exception e) {
                    System.out.println(border);
                    System.out.println("Add a ToDo task: todo <task-name>");
                    System.out.println(border);
                }

            } else if (input.startsWith("deadline")) {
                try {
                    String[] parts = input.substring(9).split(" /by ");
                    String taskName = parts[0];
                    String dateline = parts[1];
                    System.out.println(taskName + " " + dateline);
                    Task t = new Deadlines(taskName, dateline);
                    list.add(t);
                    counter++;
                    System.out.println(border);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + counter + " tasks in the list");
                    System.out.println(border);
                } catch (Exception e) {
                    System.out.println(border);
                    System.out.println("Add a Deadline Task: deadline <task-name> /by <dateline>");
                    System.out.println(border);
                }

            } else if (input.startsWith("event")) {
                try {
                    String[] parts1 = input.substring(6).split(" /from ");
                    String taskName = parts1[0];
                    String[] parts2 = parts1[1].split(" /to ");
                    String startTime = parts2[0];
                    String endTime = parts2[1];
                    Task t = new Events(taskName, startTime, endTime);
                    list.add(t);
                    counter++;
                    System.out.println(border);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + counter + " tasks in the list");
                    System.out.println(border);
                } catch (Exception e) {
                    System.out.println(border);
                    System.out.println("Add an Event Task: event <task-name> /from <start-time> /to <end-time>");
                    System.out.println(border);
                }
            } else if (input.startsWith("delete")) {
                try {
                    if (input.substring(6).isBlank()) {
                        throw new PepeExceptions("To delete a task: delete <task-index>");
                    }
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (list.size() <= index) {
                        throw new PepeExceptions("There is no task at index: " + (index+1));
                    }
                    Task task = list.remove(index);
                    System.out.println(border);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    counter--;
                    System.out.println("Now you have " + counter + " tasks in the list");
                    System.out.println(border);
                } catch (NumberFormatException e) {
                    System.out.println(border);
                    System.out.println("To delete a task: delete <task-index> (task-index must be a valid number)");
                    System.out.println(border);
                } catch (PepeExceptions e) {
                    System.out.println(border);
                    System.out.println(e);
                    System.out.println(border);
                }
            }
            else {
                System.out.println(border);
                System.out.println("""
                        I am sorry! I do not recognise this command!\
                        
                        To add Tasks: use todo, deadline or event\
                        
                        To mark or unmark a task: do mark or unmark""");
                System.out.println(border);
            }
        }

        scanner.close();
    }
}
