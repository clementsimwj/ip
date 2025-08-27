import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.unmarkTask();
                ui.uiUnmark(tasks.get(index));
            } else {
                throw new PepeExceptions("There is no task at index: " + (index + 1));
            }
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }

    }
}
