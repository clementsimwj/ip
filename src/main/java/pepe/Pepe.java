package pepe;

import pepe.command.Command;
import pepe.exception.PepeExceptions;
import pepe.parser.Parser;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Main class for the Pepe application, a command-line task manager.
 * <p>
 * This class initializes the application, loads tasks from storage,
 * handles user input through the Ui, and executes commands using the Parser.
 */
public class Pepe {
    //Constants
    private static final String BORDER = "____________________________________________________________";
    private static final String FILE_PATH = "./data/tasks.txt";

    //Fields
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private String commandType;

    /**
     * Constructs a new Pepe application instance with the given storage file path.
     * <p>
     * Loads tasks from the storage file. If loading fails, initializes an empty TaskList.
     *
     * @param filePath the path to the storage file for tasks
     */
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

    /**
     * Runs the main loop of the Pepe application.
     * <p>
     * The loop repeatedly reads user commands, parses them, executes the
     * corresponding command, and updates the task list until the user exits.
     */
    public void run() {
        ui.uiGreetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PepeExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Entry point for the Pepe application.
     * <p>
     * Initializes a Pepe instance with the default task storage path and runs the main loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Pepe("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            System.out.println(c.getString());
            return c.getString();
        } catch (PepeExceptions e) {
            return "Yo bro! \n" + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

}

