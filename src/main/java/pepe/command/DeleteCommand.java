package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;


public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
