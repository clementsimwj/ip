package pepe.storage;

import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles storage and retrieval of tasks from a file.
 * <p>
 * This class manages loading tasks from a text file into memory
 * and saving tasks from memory back into the file in a specific
 * file format.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage instance for a given file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the given file path.
     * <p>
     * The method will attempt to parse each line into a Task object
     * (ToDos, Deadlines, Events) and mark them as done if specified.
     * If the file does not exist, it will be created.
     *
     * @return an ArrayList of Tasks loaded from the file
     * @throws PepeExceptions if there is an error creating the file
     */
    public ArrayList<Task> load() throws PepeExceptions {
        ArrayList<Task> outputArray = new ArrayList<>(100);
        File file = new File(this.filePath);
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("File tasks.txt exists");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskName = parts[2].trim();

                switch (type) {
                case "T":
                    ToDos todo = new ToDos(taskName);
                    if (isDone) {
                        todo.markTask();
                    }
                    outputArray.add(todo);
                    break;
                case "D":
                    String by = parts[3].trim();
                    String dateline = rawDateToString(by);
                    try {
                        Deadlines deadline = new Deadlines(taskName, dateline);
                        if (isDone) {
                            deadline.markTask();
                        }
                        outputArray.add(deadline);
                    } catch (PepeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "E":
                    String time= parts[3].trim();
                    String[] fromAndTo = time.split("-");
                    String from = rawDateToString(fromAndTo[0].trim());
                    String to = rawDateToString(fromAndTo[1].trim());
                    try {
                        Events event = new Events(taskName, from, to);
                        if (isDone) {
                            event.markTask();
                        }
                        outputArray.add(event);
                    } catch (PepeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                default:
                    System.out.println("Unknown pepe.task type: " + line);
                }
            }
            return outputArray;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: Creating file now...");
            try {
                // Ensure parent directory exists (e.g., ./data/)
                file.getParentFile().mkdirs();
                // Create the file itself (empty duke.txt)
                file.createNewFile();
                System.out.println("Successfully created file: ./data/tasks.txt");
            } catch (IOException ioException) {
                throw new PepeExceptions("Error creating file: " + ioException.getMessage());
            }
        }
        return outputArray;
    }

    /**
     * Saves the given list of tasks to the file.
     * <p>
     * Each task is written in its file format on a new line.
     *
     * @param tasks the TaskList containing tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        try {
            for (Task task : tasks.getTaskList()) {
                fileWriter.write(task.toFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {
            fileWriter.close();
        }
    }

    /**
     * Converts a raw date string (e.g., "Dec 31 2025") to a standard
     * string format (yyyy-MM-dd) used internally.
     *
     * @param rawDate the raw date string from the file
     * @return the date string in yyyy-MM-dd format
     */
    public String rawDateToString(String rawDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(rawDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormatter);
    }
}
