package pepe.command;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        ui.uiList(tasks);
    }
}
