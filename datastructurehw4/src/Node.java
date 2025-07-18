import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in a hierarchical structure, such as a planet or satellite.
 * Each node has a name, type, sensor data, and a list of child nodes.
 */
public class Node {
    String name;
    String type;
    SensorData sensor_data;
    List <Node> children;

    /**
     * Constructs a new Node with the specified name, type, and sensor data.
     * 
     * @param name        The name of the node.
     * @param type        The type of the node (e.g., "planet", "satellite").
     * @param sensor_data The sensor data associated with the node.
     */
    public Node(String name, String type, SensorData sensor_data) {
        this.name = name;
        this.type = type;
        this.sensor_data = sensor_data;
        this.children = new ArrayList<>();
    }
    
    /**
     * Adds a child node to this node's list of children.
     * 
     * @param child The child node to be added.
     */
    public void addChild(Node child) {
        children.add(child);
    }
}
