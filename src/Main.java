import entity.Priority;
import entity.Status;
import exception.InvalidTaskException;
import exception.TaskNotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TaskManager manager = new TaskManager();
        manager.load("tasks.txt");

        int option;
        do {
            designMenu();
            option = sc.nextInt();
            sc.nextLine();   // clear the Enter

            try {
                switch (option) {
                    case 1:
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Priority (LOW, MEDIUM, HIGH): ");
                        Priority priority = Priority.valueOf(sc.nextLine().toUpperCase());
                        manager.add(title, priority);
                        System.out.println("Task added");
                        break;
                    case 2:
                        manager.list();
                        break;
                    case 3:
                        System.out.print("Task id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New status (TODO, IN_PROGRESS, DONE): ");
                        Status status = Status.valueOf(sc.nextLine().toUpperCase());
                        manager.changeStatus(id, status);
                        System.out.println("Status changed");
                        break;
                    case 4:
                        manager.listByPriority();
                        break;
                    case 5:
                        manager.countByStatus();
                        break;
                    case 0:
                        System.out.println("Bye!");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (InvalidTaskException e) {
                System.out.println("Invalid task: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("That priority/status does not exist");
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (option != 0);

        try {
            manager.save("tasks.txt");
            System.out.println("Tasks saved");
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    private static void designMenu() {
        System.out.println();
        System.out.println("1. Add task");
        System.out.println("2. List tasks");
        System.out.println("3. Change status");
        System.out.println("4. List by priority (extra)");
        System.out.println("5. Count by status (extra)");
        System.out.println("0. Save and exit");
        System.out.print("Option: ");
    }
}
