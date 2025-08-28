package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;


public class DeadlineCommand extends Command {
    private Task task;

    public DeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            tasks.addTask(this.task);
            ui.uiDeadline(tasks, this.task);
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Add a Deadline Task: deadline <pepe.task-name> " +
                    "/by <deadline> (In the format: yyyy-mm-dd)");
        }
    }
}
