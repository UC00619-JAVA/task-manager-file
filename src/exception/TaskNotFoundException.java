package exception;

// Checked: the id does not exist
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(int id) {
        super("Task " + id + " not found");
    }
}
