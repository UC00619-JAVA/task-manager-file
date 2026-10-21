import entity.Priority;
import entity.Status;
import exception.InvalidTaskException;

public class Task {

    private final int id;
    private final String title;
    private final Priority priority;
    private Status status;

    public Task(int id, String title, Priority priority, Status status) {
        if (title.isBlank() || title.contains(";")) {
            throw new InvalidTaskException("The title cannot be empty or contain ;");
        }
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Example: 1;Study enums;HIGH;DONE
    public String toFileLine() {
        return id + ";" + title + ";" + priority + ";" + status;
    }

    // Creates a Task from a line of the file
    public static Task fromFileLine(String line) {
        String[] parts = line.split(";");
        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        Priority priority = Priority.valueOf(parts[2]);
        Status status = Status.valueOf(parts[3]);
        return new Task(id, title, priority, status);
    }

    public void show() {
        System.out.println(id + ". [" + priority + "] " + title + " - " + status);
    }
}
