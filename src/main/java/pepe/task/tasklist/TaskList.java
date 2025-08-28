package pepe.task.tasklist;

import java.util.ArrayList;

import pepe.task.Task;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
