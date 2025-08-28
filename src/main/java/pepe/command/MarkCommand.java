package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;


/**
 * Command to mark a task as done.
 * <p>
 * Extends the {@link Command} abstract class. When executed, it marks the
 * task at the given index in the {@link TaskList} as completed and updates
 * the UI and storage.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Creates a new MarkCommand for the specified task index.
     *
     * @param index the 0-based index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
     * <p>
     * Marks the task at the specified index as done, updates the UI,
     * and saves the updated task list to storage.
     *
     * @param tasks   the current task list
     * @param ui      the UI object to show feedback
     * @param storage the storage object to save updated tasks
     * @throws PepeExceptions if the index is invalid or an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markTask();
                ui.uiMark(task);
            } else {
                throw new PepeExceptions("There is no task at index: " + (index + 1));
            }
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }
}
