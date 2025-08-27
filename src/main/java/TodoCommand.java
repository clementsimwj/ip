import java.io.IOException;

public class TodoCommand extends Command{
    private Task task;

    public TodoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            tasks.addTask(this.task);
            ui.uiToDo(tasks, this.task);
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }

    }
}
