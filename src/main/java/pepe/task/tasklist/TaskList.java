package pepe.task.tasklist;

import java.util.ArrayList;
import java.util.stream.Collectors;

import pepe.task.EmptyTask;
import pepe.task.Task;


/**
 * Represents a list of tasks in Pepe.
 * <p>
 * Provides methods to add, delete, and access tasks, as well as check list size or emptiness.
 */
@SuppressWarnings("checkstyle:Regexp")
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param taskList the initial list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList with an initial capacity of 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        taskList.add(task);
    }

    /**
     * Sets a task to null from the list at the specified index.
     *
     * @param index the index of the task to delete (0-based)
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds for deleteTask: " + index;
        Task task = taskList.get(index);
        taskList.set(index, new EmptyTask());
        return task;
    }

    /**
     * Permanently removes all tasks that have been marked as deleted (or set to {@code null})
     * from the task list.
     * <p>
     * This method should be called after performing deletions to compact the list and ensure
     * task indices remain consistent. Without calling {@code wipe()}, deleted tasks remain
     * as placeholders in the list.
     * <p>
     * Typical usage:
     * <pre>
     *     tasks.delete(1);
     *     tasks.delete(3);
     *     tasks.wipe(); //clears the deleted tasks from the list
     * </pre>
     */
    public void wipe() {
        this.taskList.removeIf(task -> task instanceof EmptyTask);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list has no tasks, false otherwise
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     */
    public Task get(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds for get: " + index;
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return the ArrayList containing all tasks
     */
    public ArrayList<Task> getTaskList() {
        assert taskList != null : "Underlying task list should never be null";
        return this.taskList;
    }

    /**
     * Searches for tasks whose names contain the given keyword.
     * <p>
     * This method does not modify the original task list. It returns a new
     * {@link TaskList} containing only the tasks that match the search criterion.
     *
     * @param taskName the keyword to search for in task names
     * @return a new {@link TaskList} containing tasks whose names contain {@code taskName}
     */
    public TaskList findTask(String taskName) {
        return new TaskList(this.taskList.stream()
                .filter(task -> task.getName().contains(taskName))
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

}
