package pepe.command;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions;

    public boolean isExit() {
        return false;
    }

}
