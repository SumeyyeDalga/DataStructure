package Main;
import HWSystem.HWSystem;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Main class for the HWSystem application.
 * This class initializes the system and processes user commands.
 */
//I got help from artificial intelligence for javadoc 
public class Main {
    /**
     * The main method of the application.
     * It initializes the system, reads commands from the user, and executes them.
     *
     * @param args Command-line arguments. The first argument should be the configuration file path.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <configFile> <logFilePath>");
            return;
        }

        String configFile = args[0];
        String logFilePath = args[1];
        File logDir = new File(logFilePath);
        if (!logDir.exists()) {
            logDir.mkdirs(); // Create folder if it doesn't exist
        }
        HWSystem system = new HWSystem(configFile,logFilePath);

        Scanner scanner = new Scanner(System.in);
        Queue<String> commands = new LinkedList<>();
        boolean control = true;

        System.out.println("HW System initialized. Enter commands:");

        // Store commands in the queue
        while (control) {
            System.out.print("Command: ");
            String line = scanner.nextLine().trim();
            commands.add(line);

            if (line.equals("exit")) {
                control = false;
            }
        }

        scanner.close();

        // Run commands in order
        while (!commands.isEmpty()) {
            String commandLine = commands.poll();
            String[] parts = commandLine.split(" ");
            String command = parts[0];

            try {
                switch (command) {
                    case "turnON":
                        if (parts.length != 2) {
                            System.err.println("Usage: turnON <portID>");
                            break;
                        }
                        int portID = Integer.parseInt(parts[1]);
                        system.turnDeviceOn(portID);
                        break;

                    case "turnOFF":
                        if (parts.length != 2) {
                            System.err.println("Usage: turnOFF <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.turnDeviceOff(portID);
                        break;

                    case "addDev":
                        if (parts.length != 4) {
                            System.err.println("Usage: addDev <devName> <portID> <devID>");
                            break;
                        }
                        String devName = parts[1];
                        portID = Integer.parseInt(parts[2]);
                        int devID = Integer.parseInt(parts[3]);
                        system.addDevice(devName, portID, devID);
                        break;

                    case "rmDev":
                        if (parts.length != 2) {
                            System.err.println("Usage: rmDev <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.removeDevice(portID);
                        break;

                    case "list":
                        if (parts.length != 2) {
                            System.err.println("Usage: list ports|Sensor|Display|WirelessIO|MotorDriver");
                            break;
                        }
                        String listType = parts[1];
                        if (listType.equals("ports")) {
                            system.listPorts();
                        } else {
                            system.listDeviceType(listType);
                        }
                        break;

                    case "readSensor":
                        if (parts.length != 2) {
                            System.err.println("Usage: readSensor <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readSensor(devID);
                        break;

                    case "printDisplay":
                        if (parts.length < 3) {
                            System.err.println("Usage: printDisplay <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        String data = "";
                        for (int j = 2; j < parts.length; j++) {
                            data += parts[j] + " ";
                        }
                        system.printDisplay(devID, data.trim());
                        break;

                    case "readWireless":
                        if (parts.length != 2) {
                            System.err.println("Usage: readWireless <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readWireless(devID);
                        break;

                    case "writeWireless":
                        if (parts.length < 3) {
                            System.err.println("Usage: writeWireless <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        String wirelessData = "";
                        for (int j = 2; j < parts.length; j++) {
                            wirelessData += parts[j] + " ";
                        }
                        system.writeWireless(devID, wirelessData.trim());
                        break;

                    case "setMotorSpeed":
                        if (parts.length != 3) {
                            System.err.println("Usage: setMotorSpeed <devID> <speed>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        int speed = Integer.parseInt(parts[2]);
                        system.setMotorSpeed(devID, speed);
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        system.writeAllLogs();
                        break;

                    default:
                        System.err.println("Unknown command: " + command);
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}