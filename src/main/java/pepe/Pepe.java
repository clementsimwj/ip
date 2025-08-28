package pepe;

import pepe.command.Command;
import pepe.exception.PepeExceptions;
import pepe.parser.Parser;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

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

}






/*    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<pepe.task.Task> list = pepe.storage.Storage.load();
        int counter = 0;

        System.out.println(BORDER);
        System.out.println("Hello, I am pepe.Pepe!\nHow may I help you today?");
        System.out.println(BORDER);

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                String pepe.command = input.split(" ", 2)[0].toLowerCase();

                switch (pepe.command) {
                case "bye":
                    System.out.println(BORDER);
                    System.out.println("Aww...so sad to see you leave! :(");
                    System.out.println(BORDER);
                    pepe.storage.Storage.save(list);
                    return;
                case "list":
                    System.out.println(BORDER);
                    if (list.isEmpty()) {
                        System.out.println("Your pepe.task.Task List is Empty...");
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
                            pepe.task.Task pepe.task = list.get(index);
                            pepe.task.markTask();
                            System.out.println(BORDER);
                            System.out.println("Nice! I've marked this pepe.task as done:");
                            System.out.println(pepe.task);
                            System.out.println(BORDER);
                            pepe.storage.Storage.save(list);
                        } else {
                            throw new pepe.exception.PepeExceptions("There is no pepe.task at index: " + (index + 1));
                        }
                    } else {
                        throw new pepe.exception.PepeExceptions("To mark a pepe.task: mark <pepe.task-index> (pepe.task-index is a valid number)");
                    }
                    break;
                case "unmark":
                    Matcher unmarkMatcher = UNMARK_PATTERN.matcher(input);
                    if (unmarkMatcher.matches()) {
                        int index = Integer.parseInt(unmarkMatcher.group(1)) - 1;
                        if (index >= 0 && index < list.size()) {
                            pepe.task.Task pepe.task = list.get(index);
                            pepe.task.unmarkTask();
                            System.out.println(BORDER);
                            System.out.println("OK, I've marked this pepe.task as not done yet:");
                            System.out.println(pepe.task);
                            System.out.println(BORDER);
                            pepe.storage.Storage.save(list);
                        } else {
                            throw new pepe.exception.PepeExceptions("There is no pepe.task at index: " + (index + 1));
                        }
                    } else {
                        throw new pepe.exception.PepeExceptions("To unmark a pepe.task: unmark <pepe.task-index> (pepe.task-index is a valid number)");
                    }
                    break;
                case "todo":
                    Matcher todoMatcher = TODO_PATTERN.matcher(input);
                    if (todoMatcher.matches()) {
                        String taskName = todoMatcher.group(1);
                        pepe.task.Task pepe.task = new pepe.task.ToDos(taskName);
                        list.add(pepe.task);
                        counter++;
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this pepe.task:");
                        System.out.println(pepe.task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        pepe.storage.Storage.save(list);
                    } else {
                        throw new pepe.exception.PepeExceptions("Add a ToDo pepe.task: todo <pepe.task-name>");
                    }
                    break;
                case "deadline":
                    Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
                    if (deadlineMatcher.matches()) {
                        String taskName = deadlineMatcher.group(1);
                        String deadline = deadlineMatcher.group(2);
                        pepe.task.Task pepe.task = new pepe.task.Deadlines(taskName, deadline);
                        list.add(pepe.task);
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this pepe.task:");
                        System.out.println(pepe.task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        pepe.storage.Storage.save(list);
                    } else {
                        throw new pepe.exception.PepeExceptions("Add a Deadline pepe.task.Task: deadline <pepe.task-name> " +
                                "/by <deadline> (In the format: yyyy-mm-dd)");
                    }
                    break;
                case "event":
                    Matcher eventMatcher = EVENT_PATTERN.matcher(input);
                    if (eventMatcher.matches()) {
                        String taskName = eventMatcher.group(1);
                        String startTime = eventMatcher.group(2);
                        String endTime = eventMatcher.group(3);
                        pepe.task.Task pepe.task = new pepe.task.Events(taskName, startTime, endTime);
                        list.add(pepe.task);
                        counter++;
                        System.out.println(BORDER);
                        System.out.println("Got it. I've added this pepe.task:");
                        System.out.println(pepe.task);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println(BORDER);
                        pepe.storage.Storage.save(list);
                    } else {
                        throw new pepe.exception.PepeExceptions("Add an Event pepe.task.Task: event <pepe.task-name> " +
                                "/from <start-time> " +
                                "/to <end-time> (In the format: yyyy-mm-dd)");
                    }
                    break;
                case "delete":
                    Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
                    if (deleteMatcher.matches()) {
                        int index = Integer.parseInt(deleteMatcher.group(1)) - 1;
                        if (index >= 0 && index < list.size()) {
                            pepe.task.Task pepe.task = list.remove(index);
                            counter--;
                            System.out.println(BORDER);
                            System.out.println("Noted. I've removed this pepe.task:");
                            System.out.println(pepe.task);
                            System.out.println("Now you have " + list.size() + " tasks in the list");
                            System.out.println(BORDER);
                            pepe.storage.Storage.save(list);
                        } else {
                            throw new pepe.exception.PepeExceptions("There is no pepe.task at index: " + (index + 1));
                        }
                    } else {
                        throw new pepe.exception.PepeExceptions("To delete a pepe.task: delete <pepe.task-index> (pepe.task-index is a valid number)");
                    }
                    break;
                default:
                    System.out.println(BORDER);
                    System.out.println("""
                            I am sorry! I do not recognise this pepe.command!\

                            To add Tasks: use todo, deadline or event\


                            To mark or unmark a pepe.task: do mark or unmark""");
                    System.out.println(BORDER);
                }
            } catch (pepe.exception.PepeExceptions p) {
                System.out.println(p.toString());
                continue;
            }

        }
    }*/
