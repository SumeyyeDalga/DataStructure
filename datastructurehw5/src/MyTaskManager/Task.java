package MyTaskManager;

/**
 * Represents a task in the task manager system.
 * Each task is associated with a user and has a unique ID.
 * Tasks are comparable based on the user's priority and, if priorities are equal, by their ID.
 */
public class Task implements Comparable<Task> {
    User user;
    Integer id;

    /**
     * Constructs a Task with the specified user and ID.
     *
     * @param user The user associated with the task.
     * @param id   The unique ID of the task.
     * 
     * Time Complexity: O(1)
     */
    public Task(User user, Integer id) {
        this.user = user;
        this.id = id;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task ID and the user ID.
     *
     * @return A string representation of the task.
     * 
     * Time Complexity: O(1)
     */
    public String toString() {
        return "Task " + this.id + " User " + this.user.getID();
    }

    /**
     * Compares this task with another task for ordering.
     * Tasks are compared first by the user's priority. If the priorities are equal,
     * tasks are compared by their ID (earlier tasks are considered smaller).
     *
     * @param other The other task to compare to.
     * @return A negative integer, zero, or a positive integer as this task is less than,
     *         equal to, or greater than the specified task.
     * 
     * Time Complexity: O(1)
     */
    @Override
    public int compareTo(Task other) {
        int priorityComparison = this.user.getPriority().compareTo(other.user.getPriority());
        if (priorityComparison != 0) {
            return priorityComparison; //compare by priority
        }
        // If priorities are equal, compare by ID
        return this.id.compareTo(other.id);
    }
    
}
