package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;


/**
 * Command to delete a task from the task list at a specified index.
 * <p>
 * Extends the {@link Command} abstract class and implements the {@link #execute(TaskList, Ui, Storage)}
 * method to remove the task, update the UI, and save the updated task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index the 0-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deletes the task at the specified index from the task list, shows a confirmation message
     * via {@link Ui}, and saves the updated task list to {@link Storage}.
     *
     * @param tasks   the task list to delete the task from
     * @param ui      the UI object to display messages
     * @param storage the storage object to save the updated task list
     * @throws PepeExceptions if the index is invalid or there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            if (index >= 0 && index < tasks.size()) {
                ui.uiDelete(tasks, tasks.deleteTask(index));
            } else {
                throw new PepeExceptions("There is no task at index: " + (index + 1));
            }
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }
}
