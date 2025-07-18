package arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("Enter a command: ");
            command = scanner.nextLine();
            if (command.equals("add")) {
                System.out.println("Enter a description: ");
                String description = scanner.nextLine();
                tasks.add(new Task(description));
            } 
            else if(command.equals("delete")){
                System.out.println("Enter the task number: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                tasks.remove(taskNumber - 1);
            }
            else if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i) + " ");
                }
            } 
            else if (command.equals("done")) {
                System.out.println("Enter the task number: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                tasks.get(taskNumber - 1).markAsDone();
            }
            else if(command.equals("edit")){
                System.out.println("Enter the task number: ");
                int taskNumber = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter the new description: ");
                String new_description=scanner.nextLine();
                tasks.get(taskNumber-1).setDescription(new_description);
                if(tasks.get(taskNumber-1).isDone()){
                    tasks.get(taskNumber-1).markAsDone();
                }
            }
        }
    }
}
