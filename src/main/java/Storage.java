import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> outputArray = new ArrayList<>(100);
        File file = new File(FILE_PATH);
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
                    Deadlines deadline = new Deadlines(taskName, by);
                    if (isDone) {
                        deadline.markTask();
                    }
                    outputArray.add(deadline);
                    break;
                case "E":
                    String time= parts[3].trim();
                    String[] fromAndTo = time.split("-");
                    String from = fromAndTo[0].trim();
                    String to = fromAndTo[1].trim();
                    Events event = new Events(taskName, from, to);
                    if (isDone) {
                        event.markTask();
                    }
                    outputArray.add(event);
                    break;
                default:
                    System.out.println("Unknown task type: " + line);
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
                System.out.println("Error creating file: " + ioException.getMessage());
            }
        }
        return outputArray;
    }

    public static void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        try {
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {
            fileWriter.close();
        }
    }
}
