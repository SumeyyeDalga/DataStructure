import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * The main method that runs the application.
 * It listens for user commands to manage a planetary system.
 * 
 * Supported commands:
 * - `create planetSystem`: Creates a new planetary system.
 * - `addPlanet`: Adds a planet to a parent node.
 * - `addSatellite`: Adds a satellite to a parent node.
 * - `findRadiationAnomalies`: Finds nodes with high radiation.
 * - `getPathTo`: Prints the path to a specific node.
 * - `printMissionReport`: Prints a mission report.
 * 
 * @param args Command-line arguments (not used).
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlanetSystem system = new PlanetSystem();
        
        while (true) {
            System.out.print("--Enter command:");
            String command = scanner.nextLine();
            
            try {
                String[] parts = command.split(" ");
                switch (parts[0]) {
                    case "create":
                        if (parts.length != 7) throw new IllegalArgumentException("Usage: create planetSystem name temp pressure humidity radiation");
                        if (parts[1].equals("planetSystem")) {
                            String starName = parts[2];
                            double temperature = Double.parseDouble(parts[3]);
                            double pressure = Double.parseDouble(parts[4]);
                            double humidity = Double.parseDouble(parts[5]);
                            double radiation = Double.parseDouble(parts[6]);
                            system.create_PlanetSystem(starName, temperature, pressure, humidity, radiation);
                        } else {
                            throw new IllegalArgumentException("Invalid create command.");
                        }
                        break;
                    case "addPlanet":
                        if (parts.length != 7) throw new IllegalArgumentException("Usage: addPlanet name parent temp pressure humidity radiation");
                        String planetName = parts[1];
                        String parentName = parts[2];
                        double planetTemperature = Double.parseDouble(parts[3]);
                        double planetPressure = Double.parseDouble(parts[4]);
                        double planetHumidity = Double.parseDouble(parts[5]);
                        double planetRadiation = Double.parseDouble(parts[6]);
                        system.addPlanet(planetName, parentName, planetTemperature, planetPressure, planetHumidity, planetRadiation);
                        break;
                    case "addSatellite":
                        if (parts.length != 7) throw new IllegalArgumentException("Usage: addSatellite name parent temp pressure humidity radiation");
                        String satelliteName = parts[1];
                        String parentSatName = parts[2];
                        double satelliteTemperature = Double.parseDouble(parts[3]);
                        double satellitePressure = Double.parseDouble(parts[4]);
                        double satelliteHumidity = Double.parseDouble(parts[5]);
                        double satelliteRadiation = Double.parseDouble(parts[6]);
                        system.addSatellite(satelliteName, parentSatName, satelliteTemperature, satellitePressure, satelliteHumidity, satelliteRadiation);
                        break;
                    case "findRadiationAnomalies":
                        if (parts.length != 2) throw new IllegalArgumentException("Usage: findRadiationAnomalies threshold");
                        double threshold = Double.parseDouble(parts[1]);
                        List<Node> anomalies = system.findRadiationAnomalies(threshold);
                        if (anomalies.isEmpty()) {
                            System.out.println("No anomalies found.");
                        } else {
                            System.out.print("Anomalies found: ");
                            for (int i = 0; i < anomalies.size(); i++) {
                                System.out.print(anomalies.get(i).name);
                                if (i < anomalies.size() - 1) {
                                    System.out.print(" - ");
                                }
                            }
                            System.out.println(); 
                        }
                        break;
                    case "getPathTo":
                        if (parts.length != 2) throw new IllegalArgumentException("Usage: getPathTo nodeName");
                        String planetNamePath = parts[1];
                        Stack<String> path = system.getPathTo(planetNamePath);
                        for (int i = 0; i < path.size(); i++) {
                            System.out.print(path.get(i));
                            if (i < path.size() - 1) {
                                System.out.print(" - ");
                            }
                        }
                        System.out.println();
                        break;
                    case "printMissionReport":
                        if (parts.length == 1) {
                            system.printMissionReport();
                        } else if (parts.length == 2) {
                            String nodeName = parts[1];
                            system.printMissionReport(nodeName);
                        } else {
                            throw new IllegalArgumentException("Usage: printMissionReport [nodeName]");
                        }
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        throw new IllegalArgumentException("Unknown command: " + parts[0]);
                }            
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }   
}

