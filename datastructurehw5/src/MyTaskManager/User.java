package MyTaskManager;

/**
 * Represents a user in the task manager system.
 * Each user has a unique ID and a priority level.
 */
public class User {
    Integer id;
    Integer priority;

     /**
     * Constructs a User with the specified ID and priority.
     *
     * @param id       The unique ID of the user.
     * @param priority The priority level of the user.
     * 
     * Time Complexity: O(1)
     */
    public User(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Returns the unique ID of the user.
     *
     * @return The ID of the user.
     * 
     * Time Complexity: O(1)
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Returns the priority level of the user.
     *
     * @return The priority of the user.
     * 
     * Time Complexity: O(1)
     */
    public Integer getPriority() {
        return this.priority;
    }
}
