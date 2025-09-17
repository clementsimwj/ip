package pepe.command;

import java.io.IOException;
import java.util.ArrayList;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;


/**
 * Command to delete a task from the task list at a specified index.
 * <p>
 * Extends the {@link Command} abstract class and implements the {@link #execute(TaskList, Ui, Storage)}
 * method to remove the task, update the UI, and save the updated task list.
 */
public class DeleteCommand extends Command {
    private final int[] indices;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param indices an array of indexes to delete
     */
    public DeleteCommand(int[] indices) {
        this.indices = indices;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deletes the tasks at the specified index from the task list, shows a confirmation message
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
            ArrayList<Task> deletedTasks = new ArrayList<>();
            for (int index : indices) {
                if (index < 0 || index >= tasks.size()) {
                    throw new PepeExceptions("There is no task at index: " + (index + 1) + "!\nAborting Deletion...");
                }
            }
            for (int index : indices) {
                Task deletedTask = tasks.deleteTask(index);
                deletedTasks.add(deletedTask);
            }
            super.setResponse(ui.showUiDelete(tasks, deletedTasks.toArray(new Task[0])));
            tasks.wipe();
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }

}
