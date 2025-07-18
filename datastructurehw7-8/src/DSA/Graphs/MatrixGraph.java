package DSA.Graphs;

import java.util.Collection;

/**
 * A class representing a graph using an adjacency matrix.
 * This class implements the {@link GTUGraph} interface and provides
 * methods to manipulate and query the graph.
 * 
 * Time complexities for each method are provided in their respective descriptions.
 */
public class MatrixGraph implements GTUGraph {
    private AdjacencyVect[] adjacencyVector;
    private int size;

    /**
     * Constructs a MatrixGraph with the specified size.
     * 
     * @param size the number of vertices in the graph
     * Time Complexity: O(n^2), where n is the size of the graph.
     */
    public MatrixGraph(int size) {
        this.size = size;
        adjacencyVector = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            adjacencyVector[i] = new AdjacencyVect(size);
        }
    }

    /**
     * Constructs a MatrixGraph with a default size of 10.
     * 
     * Time Complexity: O(n^2), where n is the default size (10).
     */
    public MatrixGraph() {
        this.size = 10;
        adjacencyVector = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            adjacencyVector[i] = new AdjacencyVect(size);
        }
    }

    /**
     * Sets an edge between two vertices in the graph.
     * 
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return true if the edge was successfully set
     * @throws IndexOutOfBoundsException if either vertex index is invalid
     * Time Complexity: O(1).
     */
    @Override
    public Boolean setEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size) {
            throw new IndexOutOfBoundsException("Invalid vertex index");
        }
        adjacencyVector[v1].add(v2);
        adjacencyVector[v2].add(v1); 
        return true;
    }

    /**
     * Checks if there is an edge between two vertices in the graph.
     * 
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return true if there is an edge between the vertices, false otherwise
     * @throws IndexOutOfBoundsException if either vertex index is invalid
     * Time Complexity: O(1).
     */
    @Override
    public Boolean getEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size) {
            throw new IndexOutOfBoundsException("Invalid vertex index");
        }
        return adjacencyVector[v1].contains(v2);
    }

    /**
     * Retrieves the neighbors of a given vertex.
     * 
     * @param v the vertex whose neighbors are to be retrieved
     * @return a collection of neighbors of the vertex
     * @throws IndexOutOfBoundsException if the vertex index is invalid
     * Time Complexity: O(1).
     */
    @Override
    public Collection<Integer> getNeighbors(int v) {
        if (v < 0 || v >= size) {
            throw new IndexOutOfBoundsException("Invalid vertex index");
        }
        return adjacencyVector[v];
    }

    /**
     * Returns the number of vertices in the graph.
     * 
     * @return the number of vertices in the graph
     * Time Complexity: O(1).
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resets the graph to a new size, clearing all existing edges.
     * 
     * @param size the new size of the graph
     * Time Complexity: O(n^2), where n is the new size of the graph.
     */
    @Override
    public void reset(int size) {
        this.size = size;
        adjacencyVector = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            adjacencyVector[i] = new AdjacencyVect(size);
        }
    }
    
    
}
