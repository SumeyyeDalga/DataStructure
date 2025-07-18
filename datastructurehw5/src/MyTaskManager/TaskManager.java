package MyTaskManager;
import MyPriorityQueue.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages tasks and users in a priority-based task management system.
 * Tasks are stored in a priority queue, and users are stored in a list.
 */
public class TaskManager {
    MyPriorityQueue<Task> tasks;

    //store users
    ArrayList<User> users;
    
    /**
     * Constructs a TaskManager and initializes it with a configuration file.
     * The configuration file specifies user priorities.
     *
     * @param configFile The path to the configuration file.
     * 
     * Time Complexity: O(n), where n is the number of lines in the configuration file.
     */
    public TaskManager(String configFile) {
        users = new ArrayList<User>();
        loadconfig(configFile);
        tasks = new MinHeap<Task>();
    }

    /**
     * Loads user priorities from a configuration file.
     * Each line in the file represents the priority of a user.
     *
     * @param configFile The path to the configuration file.
     * 
     * Time Complexity: O(n), where n is the number of lines in the configuration file.
     */
    public void loadconfig(String configFile) {
        try {
            File file = new File(configFile);
            Scanner scanner = new Scanner(file);
            int lineCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                Integer priority = Integer.parseInt(parts[0].trim());
                Integer id = lineCount;
                users.add(new User(id, priority));
                lineCount++;

            }

        }
        catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + configFile);
        }
    }

     /**
     * Adds a task to the priority queue for a specific user.
     *
     * @param task_id The unique ID of the task.
     * @param user_id The ID of the user associated with the task.
     * 
     * Time Complexity: O(log m), where m is the number of tasks in the priority queue.
     */
    public void addTask(int task_id, int user_id) {
        if (user_id >= users.size()) {
            System.err.println("User ID " + user_id + " does not exist.");
            return;
        }
        int priority = users.get(user_id).getPriority();
        User user = new User(user_id, priority);
        Task task = new Task(user, task_id);
        tasks.add(task);
    }
    
    /**
     * Executes all tasks in the priority queue in order of priority.
     * Tasks with the highest priority (lowest value) are executed first.
     * 
     * Time Complexity: O(m log m), where m is the number of tasks in the priority queue.
     */
    public void executeTask() {
        if (tasks.isEmpty()) {
            System.err.println("No tasks to execute.");
            return;
        }
        while (tasks.isEmpty() == false) {
            Task task = tasks.poll();
            System.out.println(task.toString());
        }
    }

}
