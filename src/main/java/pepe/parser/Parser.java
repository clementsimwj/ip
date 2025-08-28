package pepe.parser;

import pepe.command.*;
import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    //Regex Patterns
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern MARK_PATTERN = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN = Pattern.compile("^unmark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

    public static Command parse(String input) throws PepeExceptions{
        String command = input.split(" ", 2)[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            Matcher markMatcher = MARK_PATTERN.matcher(input);
            if (markMatcher.matches()) {
                int index = Integer.parseInt(markMatcher.group(1)) - 1;
                return new MarkCommand(index);
            } else {
                throw new PepeExceptions("To mark a task: mark <task-index> (task-index is a valid number)");
            }
        case "unmark":
            Matcher unmarkMatcher = UNMARK_PATTERN.matcher(input);
            if (unmarkMatcher.matches()) {
                int index = Integer.parseInt(unmarkMatcher.group(1)) - 1;
                return new UnmarkCommand(index);
            } else {
                throw new PepeExceptions("To unmark a task: unmark <task-index> (task-index is a valid number)");
            }
        case "delete":
            Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
            if (deleteMatcher.matches()) {
                int index = Integer.parseInt(deleteMatcher.group(1)) - 1;
                return new DeleteCommand(index);
            } else {
                throw new PepeExceptions("To delete a task: delete <task-index> (task-index is a valid number)");
            }
        case "todo":
            Matcher todoMatcher = TODO_PATTERN.matcher(input);
            if (todoMatcher.matches()) {
                String taskName = todoMatcher.group(1);
                Task task = new ToDos(taskName);
                return new TodoCommand(task);
            } else {
                throw new PepeExceptions("Add a ToDo task: todo <task-name>");
            }
        case "event":
            Matcher eventMatcher = EVENT_PATTERN.matcher(input);
            if (eventMatcher.matches()) {
                String taskName = eventMatcher.group(1);
                String startTime = eventMatcher.group(2);
                String endTime = eventMatcher.group(3);
                Task task = new Events(taskName, startTime, endTime);
                return new EventCommand(task);
            } else {
                throw new PepeExceptions("Add an Event Task: event <task-name> " +
                        "/from <start-time> " +
                        "/to <end-time> (In the format: yyyy-mm-dd)");
            }
        case "deadline":
            Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
            if (deadlineMatcher.matches()) {
                String taskName = deadlineMatcher.group(1);
                String deadline = deadlineMatcher.group(2);
                Task task = new Deadlines(taskName, deadline);
                return new DeadlineCommand(task);
            } else {
                throw new PepeExceptions("Add a Deadline Task: deadline <task-name> " +
                        "/by <deadline> (In the format: yyyy-mm-dd)");
            }
        default:
            throw new PepeExceptions("Invalid Command!");
        }
    }
}
