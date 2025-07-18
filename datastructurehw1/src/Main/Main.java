package Main;
import HWSystem.HWSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String configFile = "config.txt";
        HWSystem system = new HWSystem(configFile);
        
        Scanner scanner = new Scanner(System.in);
        boolean control = true;
        
        System.out.println("HW System initialized. Enter commands:");
        
        while (control) {
            System.out.print("Command: ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            
            
            String command = parts[0];
            
            try {
                switch (command) {
                    case "turnON":
                        if (parts.length != 2) {
                            System.out.println("Usage: turnON <portID>");
                            break;
                        }
                        int portID = Integer.parseInt(parts[1]);
                        system.turnDeviceOn(portID);
                        break;
                        
                    case "turnOFF":
                        if (parts.length != 2) {
                            System.out.println("Usage: turnOFF <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.turnDeviceOff(portID);
                        break;
                        
                    case "addDev":
                        if (parts.length != 4) {
                            System.out.println("Usage: addDev <devName> <portID> <devID>");
                            break;
                        }
                        String devName = parts[1];
                        portID = Integer.parseInt(parts[2]);
                        int devID = Integer.parseInt(parts[3]);
                        system.addDevice(devName, portID, devID);
                        break;
                        
                    case "rmDev":
                        if (parts.length != 2) {
                            System.out.println("Usage: rmDev <portID>");
                            break;
                        }
                        portID = Integer.parseInt(parts[1]);
                        system.removeDevice(portID);
                        break;
                        
                    case "list":
                        if (parts.length != 2) {
                            System.out.println("Usage: list ports|Sensor|Display|WirelessIO|MotorDriver");
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
                            System.out.println("Usage: readSensor <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readSensor(devID);
                        break;
                        
                    case "printDisplay":
                        if (parts.length < 3) {
                            System.out.println("Usage: printDisplay <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        String data="";
                        for(int i=2;i<parts.length;i++){
                            data+=parts[i];
                        }
                        system.printDisplay(devID, data);
                        break;
                        
                    case "readWireless":
                        if (parts.length != 2) {
                            System.out.println("Usage: readWireless <devID>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        system.readWireless(devID);
                        break;
                        
                    case "writeWireless":
                        if (parts.length < 3) {
                            System.out.println("Usage: writeWireless <devID> <String>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        String wirelessData = "";
                        for(int i=2;i<parts.length;i++){
                            wirelessData+=parts[i];
                        }
                        system.writeWireless(devID, wirelessData);
                        break;
                        
                    case "setMotorSpeed":
                        if (parts.length != 3) {
                            System.out.println("Usage: setMotorSpeed <devID> <speed>");
                            break;
                        }
                        devID = Integer.parseInt(parts[1]);
                        int speed = Integer.parseInt(parts[2]);
                        system.setMotorSpeed(devID, speed);
                        break;
                        
                    case "exit":
                        control = false;
                        System.out.println("Exiting...");
                        break;
                        
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}