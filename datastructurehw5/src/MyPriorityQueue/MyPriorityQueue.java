package MyPriorityQueue;

/**
 * A generic interface for a priority queue.
 * Provides methods to add, remove, and check if the queue is empty.
 *
 * @param <T> The type of elements in the priority queue.
 */
public interface MyPriorityQueue <T extends Comparable<T>> {

    /**
     * Adds an element to the priority queue.
     *
     * @param t The element to be added to the priority queue.
     * Time Complexity: O(log n), where n is the number of elements in the priority queue.
     */
    void add(T t);

    /**
     * Removes and returns the minimum element from the priority queue.
     * If the priority queue is empty, returns null.
     *
     * @return The minimum element in the priority queue, or null if the priority queue is empty.
     * Time Complexity: O(log n), where n is the number of elements in the priority queue.
     */
    T poll();

    /**
     * Returns the minimum element from the priority queue without removing it.
     * If the priority queue is empty, returns null.
     *
     * @return The minimum element in the priority queue, or null if the priority queue is empty.
     * Time Complexity: O(1), where n is the number of elements in the priority queue.
     */
    Boolean isEmpty();
}