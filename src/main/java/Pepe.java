import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pepe {
    //Constants
    private static final String BORDER = "____________________________________________________________";
    private static final String FILE_PATH = "./data/tasks.txt";
    //Fields
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Pepe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PepeExceptions e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.uiGreet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PepeExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Pepe("data/tasks.txt").run();
    }

/*    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = Storage.load();
        int counter = 0;

        System.out.println(BORDER);
        System.out.println("Hello, I am Pepe!\nHow may I help you today?");
        System.out.println(BORDER);

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ", 2)[0].toLowerCase();

                switch (command) {
                case "bye":
                    System.out.println(BORDER);
                    System.out.println("Aww...so sad to see you leave! :(");
                    System.out.println(BORDER);
                    Storage.save(list);
                    return;
                case "list":
                    System.out.println(BORDER);
                    if (list.isEmpty()) {
                        System.out.println("Your Task List is Empty...");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                    }
                    System.out.println(BORDER);
                    break;
                case "mark":
                    Matcher markMatcher = MARK_PATTERN.matcher(input);
                    if (markMatcher.matches()) {
                        int index = Integer.parseInt(markMatcher.group(1)) - 1;
                        if (index >= 0 && index < list.size()) {
                            Task task = list.get(index);
                            task.markTask();
                            System.out.println(BORDER);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                            System.out.println(BORDER);
                            Storage.save(list);
                        } else {
                            throw new PepeExceptions("There is no task at index: " + (index + 1));
                        }
                    } else {
                        throw new PepeExceptions("To mark a task: mark <task-index> (task-index is a valid number)");
                    }
                    break;
                case "unmark":
                    Matcher unmarkMatcher = UNMARK_PATTERN.matcher(input);
                    if (unmarkMatcher.matches()) {
                        int index = Integer.parseInt(unmarkMatcher.group(1)) - 1;
                        if (index >= 0 && index < list.size()) {
                            Task task = list.get(index);
                            task.unmarkTask();
                            System.out.println(BORDER);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(task);
                            System.out.println(BORDER);
                            Storage.save(list);
                        } else {
                            throw new PepeExceptions("There is no task at index: " + (index + 1));
                        }
                    } else {
                        throw new PepeExceptions("To unmark a task: unmark <task-index> (task-index is a valid number)");
                    }
                    break;
                case "todo":
                    Matcher todoMatcher = TODO_PATTERN.matcher(input);
                    if (todoMatcher.matches()) {
                        String taskName = todoMatcher.group(1);
                        Task task = new ToDos(taskName);
                        list.add(task);
                        counter++;
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        Storage.save(list);
                    } else {
                        throw new PepeExceptions("Add a ToDo task: todo <task-name>");
                    }
                    break;
                case "deadline":
                    Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
                    if (deadlineMatcher.matches()) {
                        String taskName = deadlineMatcher.group(1);
                        String deadline = deadlineMatcher.group(2);
                        Task task = new Deadlines(taskName, deadline);
                        list.add(task);
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        Storage.save(list);
                    } else {
                        throw new PepeExceptions("Add a Deadline Task: deadline <task-name> " +
                                "/by <deadline> (In the format: yyyy-mm-dd)");
                    }
                    break;
                case "event":
                    Matcher eventMatcher = EVENT_PATTERN.matcher(input);
                    if (eventMatcher.matches()) {
                        String taskName = eventMatcher.group(1);
                        String startTime = eventMatcher.group(2);
                        String endTime = eventMatcher.group(3);
                        Task task = new Events(taskName, startTime, endTime);
                        list.add(task);
                        counter++;
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        Storage.save(list);
                    } else {
                        throw new PepeExceptions("Add an Event Task: event <task-name> " +
                                "/from <start-time> " +
                                "/to <end-time> (In the format: yyyy-mm-dd)");
                    }
                    break;
                case "delete":
                    Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
                    if (deleteMatcher.matches()) {
                        int index = Integer.parseInt(deleteMatcher.group(1)) - 1;
                        if (index >= 0 && index < list.size()) {
                            Task task = list.remove(index);
                            counter--;
                            System.out.println(BORDER);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(task);
                            System.out.println("Now you have " + list.size() + " tasks in the list");
                            System.out.println(BORDER);
                            Storage.save(list);
                        } else {
                            throw new PepeExceptions("There is no task at index: " + (index + 1));
                        }
                    } else {
                        throw new PepeExceptions("To delete a task: delete <task-index> (task-index is a valid number)");
                    }
                    break;
                default:
                    System.out.println(BORDER);
                    System.out.println("""
                            I am sorry! I do not recognise this command!\
                            
                            To add Tasks: use todo, deadline or event\
                            
                            
                            To mark or unmark a task: do mark or unmark""");
                    System.out.println(BORDER);
                }
            } catch (PepeExceptions p) {
                System.out.println(p.toString());
                continue;
            }

        }
    }*/
}
