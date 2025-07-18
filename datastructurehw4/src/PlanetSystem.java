import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a planetary system with a hierarchical structure of nodes.
 * The system includes a root node (e.g., a star) and its child nodes (e.g., planets and satellites).
 */
public class PlanetSystem {
    
    Node root;

    /**
     * Creates the root of the planetary system with the specified star and its sensor data.
     * 
     * @param star_name   The name of the star.
     * @param temperature The temperature of the star.
     * @param pressure    The pressure of the star.
     * @param humidity    The humidity of the star (must not be zero).
     * @param radiation   The radiation level of the star.
     */
    public void create_PlanetSystem(String star_name,double temperature, double pressure, double humidity, double radiation){
        if (this.root != null) {
            throw new IllegalStateException("A planetary system has already been created.");
        }
        if(humidity != 0) {
            throw new IllegalArgumentException("Humidity must be zero.");
        }
        SensorData sensor_data = new SensorData(temperature, pressure, humidity, radiation);
        root = new Node(star_name, "Star", sensor_data);
        System.out.println("Planetary system created with star: " + star_name);
    }

    /**
     * Adds a planet to the planetary system under the specified parent node.
     * 
     * @param planet_name The name of the planet.
     * @param parent_name The name of the parent node (e.g., the star or another planet).
     * @param temperature The temperature of the planet.
     * @param pressure    The pressure of the planet.
     * @param humidity    The humidity of the planet.
     * @param radiation   The radiation level of the planet.
     */
    public void addPlanet(String planet_name, String parent_name,double temperature, double pressure, double humidity, double radiation){
        SensorData sensor_data = new SensorData(temperature, pressure, humidity, radiation);
        Node planet = new Node(planet_name, "Planet", sensor_data);
        Node parent = findNode(root, parent_name);
        if (findNode(root, planet_name) != null) {
            throw new IllegalArgumentException("Planet with name '" + planet_name + "' already exists.");
        }
        if (parent != null) {
            if(parent.type.equals("Star") && !parent.children.isEmpty()) {
                throw new IllegalArgumentException("Cannot add planet under a star with existing children.");
            } 
                
            if(parent.type.equals("Satellite")) {
                throw new IllegalArgumentException("Cannot add planet under a satellite.");
            }
            parent.addChild(planet);
        } else {
            throw new IllegalArgumentException("Parent node not found");
        }
        System.out.println("Planet " + planet_name + " added under " + parent_name);
    }

    /**
     * Finds a node in the planetary system by its name.
     * 
     * @param node The current node being searched.
     * @param name The name of the node to find.
     * @return The node with the specified name, or null if not found.
     */
    private Node findNode(Node node, String name){
        if(node == null) {
            return null;
        }
        if(node.name.equals(name)) {
            return node;
        }
        for(Node child : node.children) {
            Node found = findNode(child, name);
            if(found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Adds a satellite to the planetary system under the specified parent node.
     * 
     * @param satellite_name The name of the satellite.
     * @param parent_name    The name of the parent node (e.g., a planet).
     * @param temperature    The temperature of the satellite.
     * @param pressure       The pressure of the satellite.
     * @param humidity       The humidity of the satellite.
     * @param radiation      The radiation level of the satellite.
     */
    public void addSatellite(String satelitte_name, String parent_name, double temperature, double pressure, double humidity, double radiation){
        SensorData sensor_data= new SensorData(temperature, pressure, humidity, radiation);
        Node satelitte = new Node(satelitte_name, "Satellite", sensor_data);
        Node parent = findNode(root, parent_name);
        if(findNode(root, satelitte_name) != null) {
            throw new IllegalArgumentException("Satellite with name '" + satelitte_name + "' already exists.");
        }
        if (parent != null ) {
            if(parent.type.equals("Planet")) {
                parent.addChild(satelitte);
            } else {
                throw new IllegalArgumentException("Cannot add satellite under a non-planet node.");
            }
        } else {
            throw new IllegalArgumentException("Parent node not found");
        }
        System.out.println("Satellite " + satelitte_name + " added under " + parent_name);
    }

    /**
     * Finds nodes in the planetary system with radiation levels above the specified threshold.
     * 
     * @param threshold The radiation level threshold.
     * @return A list of nodes with radiation levels above the threshold.
     */
    public List<Node> findRadiationAnomalies(double threshold) {
        List<Node> anomalies = new ArrayList<>();
        findRadiationAnomaliesRec(root, threshold, anomalies);
        return anomalies;
    }

    /**
     * Recursively searches for nodes with radiation levels above the threshold.
     * 
     * @param node      The current node being checked.
     * @param threshold The radiation level threshold.
     * @param anomalies The list of nodes with radiation anomalies.
     */
    private void findRadiationAnomaliesRec(Node node,double threshold, List<Node> anomalies){
        if(node == null) {
            return;
        }
        if(node.sensor_data != null && node.sensor_data.radiation > threshold) {
            anomalies.add(node);
        }
        for(Node child : node.children) {
            findRadiationAnomaliesRec(child, threshold, anomalies);
        }
    }

    /**
     * Gets the path from the root to the specified node in the planetary system.
     * 
     * @param planet_name The name of the target node.
     * @return A stack representing the path from the root to the target node.
     */
    public Stack<String> getPathTo(String planet_name){
        Stack<String> path = new Stack<>();
        boolean found = findPath(this.root, planet_name, path);
        if (!found) {
            throw new IllegalArgumentException("Node '" + planet_name + "' not found in the system.");
        }
        return path;
    }
    

    /**
     * Recursively finds the path to the specified node.
     * 
     * @param node        The current node being checked.
     * @param planet_name The name of the target node.
     * @param path        The stack representing the path.
     * @return True if the path to the target node is found, false otherwise.
     */
    public boolean findPath(Node node,String planet_name,Stack<String> path){
        if(node==null){
            return false;
        }
        path.push(node.name);
        
        if(node.name.equals(planet_name)){
            return true;
        }
        for (Node child: node.children){
            if(findPath(child,planet_name,path)){
                return true;
            }
        }
        path.pop();
        return false;
    }

    /**
     * Prints a mission report for the entire planetary system.
     */
    public void printMissionReport(){
        printNode(this.root,0);
    }

    

    /**
     * Prints a detailed report for a specific node in the planetary system.
     * 
     * @param node  The node to print the report for.
     * @param level The current level in the hierarchy (for indentation).
     */
    public void printNode(Node node, int level) {
        if (node == null) {
            throw new IllegalArgumentException("Node not found in the system.");
        }
    
        String indent = new String(new char[level]).replace("\0", "    ");  
        System.out.println(indent + "Report for " + node.name);
        System.out.println(indent + "    Node Type: " + node.type);
            if (node.sensor_data != null) {
            System.out.println(indent + "    Temperature: " + node.sensor_data.temperature + " Kelvin");
            System.out.println(indent + "    Pressure: " + node.sensor_data.pressure + " Pascal");
            System.out.println(indent + "    Humidity: " + node.sensor_data.humidity);
            System.out.println(indent + "    Radiation: " + node.sensor_data.radiation);
        }
    
        for (Node child : node.children) {
            printNode(child, level + 1);
        }
    }

    /**
     * Prints a mission report for a specific node in the planetary system.
     * 
     * @param planet_name The name of the node to print the report for.
     */
    public void printMissionReport(String node_name){
        Node node = findNode(root, node_name);
        if (node != null) {
            System.out.println("Report for " + node.name);
            System.out.println("    Node Type: " + node.type);
            if (node.sensor_data != null) {
                System.out.println("    Temperature: " + node.sensor_data.temperature + " Kelvin");
                System.out.println("    Pressure: " + node.sensor_data.pressure + " Pascal");
                System.out.println("    Humidity: " + node.sensor_data.humidity);
                System.out.println("    Radiation: " + node.sensor_data.radiation);
            }
        } else {
            throw new IllegalArgumentException("Node '" + node_name + "' not found in the system.");
        }
    }
    
}
