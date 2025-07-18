package MyPriorityQueue;
import java.util.ArrayList;

/**
 * A generic MinHeap implementation that maintains the minimum element at the root.
 * The heap is implemented using an ArrayList and supports generic types that implement Comparable.
 *
 * @param <T> The type of elements stored in the heap. Must implement Comparable.
 */
public class MinHeap < T extends Comparable <T>> implements MyPriorityQueue <T> {
    private ArrayList <T> heap;

    /**
     * Constructs an empty MinHeap.
     * 
     * Time Complexity: O(1)
     */
    public MinHeap() {
        heap = new ArrayList <T> ();
    }

    /**
     * Adds an element to the heap and maintains the heap property.
     *
     * @param t The element to be added to the heap.
     * 
     * Time Complexity: O(log n), where n is the number of elements in the heap.
     */
    public void add(T t) {
        heap.add(t);
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                index = parentIndex;
            } else {
                break;
            }
        }
    }
    
    /**
     * Removes and returns the minimum element from the heap.
     * If the heap is empty, returns null.
     *
     * @return The minimum element in the heap, or null if the heap is empty.
     * 
     * Time Complexity: O(log n), where n is the number of elements in the heap.
     */
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        int index = 0;
        while (index < heap.size()) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (leftChildIndex >= heap.size()) {
                break;
            }
            int minChildIndex = leftChildIndex;
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(leftChildIndex)) < 0) {
                minChildIndex = rightChildIndex;
            }
            if (heap.get(index).compareTo(heap.get(minChildIndex)) > 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(minChildIndex));
                heap.set(minChildIndex, temp);
                index = minChildIndex;
            } else {
                break;
            }
        }
        return min;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return True if the heap is empty, false otherwise.
     * 
     * Time Complexity: O(1)
     */
    public Boolean isEmpty() {
        return heap.isEmpty();
    }
}