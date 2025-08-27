import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            ui.uiBye();
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
