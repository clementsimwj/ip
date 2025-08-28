package pepe.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public String rawDateToString(String rawDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(rawDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormatter);
    }
}
