package Main;

import DSA.Graphs.AdjacencyVect;
import DSA.Graphs.MatrixGraph;
import DSA.Sorting.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * A class for testing sorting algorithms and the MatrixGraph implementation.
 * This class contains various test methods to verify the correctness of the implemented algorithms.
 */
public class MyTest {
    private static final Integer[] input = {5, 3, 1, 4, 2};
    private static final Integer[] expected = {1, 2, 3, 4, 5};

    /**
     * The main method that executes all test cases.
     * Prints the results of each test case to the console.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("MyTest main method executed.");

        if (testInsertSort()) {
            System.out.println("testInsertSort passed.");
        } else {
            System.out.println("testInsertSort failed.");
        }

        if (testQuickSort()) {
            System.out.println("testQuickSort passed.");
        } else {
            System.out.println("testQuickSort failed.");
        }

        if (testSelectSort()) {
            System.out.println("testSelectSort passed.");
        } else {
            System.out.println("testSelectSort failed.");
        }
        
        if (testQuick_InsertSort()) {
            System.out.println("testQuick_InsertSort passed.");
        } else {
            System.out.println("testQuick_InsertSort failed.");
        }

        if (testQuick_InsertSort2()) {
            System.out.println("testQuick_InsertSort2 passed.");
        } else {
            System.out.println("testQuick_InsertSort2 failed.");
        }

        if (testQuick_SelectSort()) {
            System.out.println("testQuick_SelectSort passed.");
        } else {
            System.out.println("testQuick_SelectSort failed.");
        }

        if (testAdjacencyVect()) {
            System.out.println("testAdjacencyVect passed.");
        } else {
            System.out.println("testAdjacencyVect failed.");
        }

        if (testMatrixGraph()) {
            System.out.println("testMatrixGraph passed.");
        } else {
            System.out.println("testMatrixGraph failed.");
        }
    }

    /**
     * Tests the insertion sort algorithm.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testInsertSort() {
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUInsertSort();
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

    /**
     * Tests the quicksort algorithm.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testQuickSort() {
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUQuickSort();
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

    /**
     * Tests the selection sort algorithm.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testSelectSort(){
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUSelectSort();
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

     /**
     * Tests the quicksort algorithm with insertion sort as a fallback for small subarrays.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testQuick_InsertSort() {
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUQuickSort(new GTUInsertSort(),4);
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

     /**
     * Tests the quicksort algorithm with insertion sort as a fallback for small subarrays.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testQuick_InsertSort2() {
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUQuickSort(new GTUInsertSort(),3);
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

    /**
     * Tests the quicksort algorithm with selection sort as a fallback for small subarrays.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testQuick_SelectSort() {
        Integer[] arr = Arrays.copyOf(input, input.length);

        GTUSorter sorter = new GTUQuickSort(new GTUSelectSort(),4);
        sorter.sort(arr, Comparator.naturalOrder());

        return Arrays.equals(arr, expected);
    }

    /**
     * Tests the AdjacencyVect implementation.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testAdjacencyVect() {
        AdjacencyVect vect = new AdjacencyVect(5);

        vect.add(1);
        vect.add(3);
        if (!vect.contains(1) || !vect.contains(3)) return false;

        vect.remove(1);
        if (vect.contains(1)) return false;

        List<Integer> iterated = new ArrayList<>();
        for (int val : vect) {
            iterated.add(val);
        }
        if (!iterated.equals(List.of(3))) return false;

        Object[] arr = vect.toArray();
        if (!(arr.length == 1 && (int) arr[0] == 3)) return false;

        return true;
    }

    /**
     * Tests the MatrixGraph implementation.
     * 
     * @return true if the test passes, false otherwise
     */
    public static boolean testMatrixGraph() {
        MatrixGraph graph = new MatrixGraph(5);

        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(3, 4);

        if (!graph.getEdge(0, 1) || !graph.getEdge(1, 0)) return false;
        if (!graph.getEdge(0, 2) || !graph.getEdge(2, 0)) return false;
        if (!graph.getEdge(3, 4) || !graph.getEdge(4, 3)) return false;
        if (graph.getEdge(0, 3)) return false;

        Collection<Integer> neighbors = graph.getNeighbors(0);
        List<Integer> neighborList = new ArrayList<>(neighbors);
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);
        expectedList.add(2);
        
        if (!neighborList.containsAll(expectedList) || neighborList.size() != expectedList.size()) {
            return false;
        }

        graph.reset(3);
        if (graph.size() != 3) return false;
        if (graph.getEdge(0, 1)) return false;

        return true;
    }

}
