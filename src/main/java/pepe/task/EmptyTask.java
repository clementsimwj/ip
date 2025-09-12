package pepe.task;
/**
 * Represents a placeholder task in the task list that signifies a deleted or
 * removed task.
 * <p>
 * When a task is deleted using a soft-delete approach, it can be replaced with
 * an {@code EmptyTask} to preserve the original indexing of the list. This helps
 * avoid {@code NullPointerException}s and maintains consistent task positions
 * for other operations such as marking, unmarking, or listing tasks.
 * <p>
 * {@code EmptyTask} should not contain any meaningful task data and is intended
 * solely as a placeholder. All methods that access task details (like
 * {@code toString()}) should reflect that it is empty or deleted.
 */
public class EmptyTask extends Task {
    /**
     * Constructs a new Empty Task with no name.
     * The task is initially unmarked (not done).
     */
    public EmptyTask() {
        super("");
    }

    @Override
    public boolean isDueNextWeek() {
        return false;
    }

    @Override
    public String toFileFormat() {
        return "";
    }
}
