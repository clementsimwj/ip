package pepe.task.tasklist;

import pepe.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in Pepe.
 * <p>
 * Provides methods to add, delete, and access tasks, as well as check list size or emptiness.
 */
public class TaskList {

    private ArrayList<Task> taskList;

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
        taskList.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index the index of the task to delete (0-based)
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
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
        return this.taskList;
    }

}
