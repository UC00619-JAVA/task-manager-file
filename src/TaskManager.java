import entity.Priority;
import entity.Status;
import exception.TaskNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {

    private final Task[] tasks = new Task[50];
    private int count = 0;

    public void add(String title, Priority priority) {
        int id = count + 1;
        tasks[count] = new Task(id, title, priority, Status.TODO);
        count++;
    }

    public Task findById(int id) throws TaskNotFoundException {
        for (int i = 0; i < count; i++) {
            if (tasks[i].getId() == id) {
                return tasks[i];
            }
        }
        throw new TaskNotFoundException(id);
    }

    public void changeStatus(int id, Status status) throws TaskNotFoundException {
        Task task = findById(id);
        task.setStatus(status);
    }

    public void list() {
        if (count == 0) {
            System.out.println("No tasks yet");
        }
        for (int i = 0; i < count; i++) {
            tasks[i].show();
        }
    }

    // Extra: first HIGH, then MEDIUM, then LOW
    public void listByPriority() {
        Priority[] order = {Priority.HIGH, Priority.MEDIUM, Priority.LOW};
        for (Priority p : order) {
            for (int i = 0; i < count; i++) {
                if (tasks[i].getPriority() == p) {
                    tasks[i].show();
                }
            }
        }
    }

    // Extra: how many tasks there are in each status
    public void countByStatus() {
        for (Status s : Status.values()) {
            int total = 0;
            for (int i = 0; i < count; i++) {
                if (tasks[i].getStatus() == s) {
                    total++;
                }
            }
            System.out.println(s + ": " + total);
        }
    }

    // One line per task
    public void save(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < count; i++) {
                writer.write(tasks[i].toFileLine() + "\n");
            }
        }
    }

    public void load(String fileName) {
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                try {
                    tasks[count] = Task.fromFileLine(line);
                    count++;
                } catch (RuntimeException e) {   // any problem in this line
                    System.out.println("Invalid line ignored: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks yet");
        }
    }
}
