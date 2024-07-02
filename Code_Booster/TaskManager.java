package Code_Booster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<User> users; // Store users for authentication
    private List<Task> tasks;
    private User currentUser;
    private Scanner scanner;

    public TaskManager() {
        users = new ArrayList<>();
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in); // Initialize scanner


        users.add(new User(1, "Aradhna", "password123", "employee"));
        users.add(new User(2, "CodeBooster", "manager123", "manager"));
    }

    private void authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                currentUser = user;
                System.out.println("Login successful as " + currentUser.getUsername());
                return;
            }
        }

        System.out.println("Authentication failed. Invalid username or password.");
        currentUser = null;
    }

    // Method to add a new task (accessible to managers)
    public void addTask() {
        if (currentUser == null) {
            System.out.println("Please login to add a task.");
            return;
        }

        if (!currentUser.getRole().equals("manager")) {
            System.out.println("Unauthorized! Only managers can add tasks.");
            return;
        }

        // Task addition logic
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter task priority (high/medium/low): ");
        String priority = scanner.nextLine().trim();

        System.out.print("Enter task deadline (e.g., YYYY-MM-DD): ");
        String deadline = scanner.nextLine().trim();

        // Assuming the user ID of the task assignee is known or entered
        System.out.print("Enter user ID to assign the task to (or 0 for no assignment): ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        User assignedTo = null;
        if (userId != 0) {
            assignedTo = findUserById(userId);
            if (assignedTo == null) {
                System.out.println("User not found with ID: " + userId);
                return;
            }
        }

        int taskId = tasks.size() + 1; // Generate task ID (assuming simple increment)
        Task newTask = new Task(taskId, description, priority, deadline, assignedTo);
        tasks.add(newTask);

        System.out.println("Task added successfully!");
    }

    // Method to view all tasks
    public void viewTasks() {
        if (currentUser == null) {
            System.out.println("Please login to view tasks.");
            return;
        }

        System.out.println("List of Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Method to view tasks sorted by deadline
    public void viewTasksByDeadline() {
        if (currentUser == null) {
            System.out.println("Please login to view tasks.");
            return;
        }

        tasks.sort(Comparator.comparing(Task::getDeadline));
        System.out.println("List of Tasks sorted by Deadline:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Method to view tasks sorted by priority
    public void viewTasksByPriority() {
        if (currentUser == null) {
            System.out.println("Please login to view tasks.");
            return;
        }

        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
        System.out.println("List of Tasks sorted by Priority:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Helper method to find a user by ID
    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    // Method to start the task manager application
    public void start() {
        System.out.println("Welcome to Task Manager!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Login");
            System.out.println("2. Add a new task");
            System.out.println("3. View all tasks");
            System.out.println("4. View tasks by deadline");
            System.out.println("5. View tasks by priority");
            System.out.println("6. Logout");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    authenticateUser();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    viewTasksByDeadline();
                    break;
                case 5:
                    viewTasksByPriority();
                    break;
                case 6:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }

        System.out.println("Thank you for using Task Manager. Goodbye!");
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.start();
    }
}
