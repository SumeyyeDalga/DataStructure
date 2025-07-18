package Main;

import MyTaskManager.TaskManager;
import java.util.Scanner; 

/**
 * The entry point for the task management system.
 * This class initializes the TaskManager and provides a command-line interface
 * for adding tasks and executing them based on priority.
 */
public class Main {

    /**
     * The main method that starts the task management system.
     * It reads a configuration file, initializes the TaskManager, and processes user commands.
     *
     * @param args Command-line arguments. Expects a single argument: the path to the configuration file.
     * 
     * Time Complexity: O(n + m log m), where:
     * - n is the number of lines in the configuration file (for initialization).
     * - m is the number of tasks added by the user (for task execution).
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <configFile>");
            return;
        } 
        String configFile = args[0];
        TaskManager taskManager = new TaskManager(configFile);

        Scanner scanner = new Scanner(System.in);
        boolean control = true;
        int task_id=0;
        while(control){
            String command = scanner.nextLine().trim();
        
           
            if (command.equals("execute")) {
                System.out.println("Executing task...");
                taskManager.executeTask();
                control = false;
                continue; 
            }
        
    
            try {
                int user = Integer.parseInt(command);
                taskManager.addTask(task_id, user);
                task_id++;
            } catch (NumberFormatException e) {
                System.err.println("Invalid command. Please enter a valid user ID or 'execute'.");
            }
            
        }
        scanner.close();
    }
}
