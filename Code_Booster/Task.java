package Code_Booster;

public class Task {
    private int taskId;
    private String description;
    private String priority; // priority can be 'high', 'medium', 'low' based on importance
    private String deadline; // deadline can be in any date format
    private User assignedTo;

    public Task(int taskId, String description, String priority, String deadline, User assignedTo) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.assignedTo = assignedTo;
    }

    // Getters and setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", deadline='" + deadline + '\'' +
                ", assignedTo=" + assignedTo.getUsername() +
                '}';
    }
}

