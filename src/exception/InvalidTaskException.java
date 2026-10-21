package exception;

// Unchecked: wrong data when creating a task
public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(String message) {
        super(message);
    }
}
