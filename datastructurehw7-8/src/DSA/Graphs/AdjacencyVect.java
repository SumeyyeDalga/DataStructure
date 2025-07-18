package DSA.Graphs;

import java.util.Collection;
import java.util.Iterator;

/**
 * A class representing an adjacency vector for graph representation.
 * This class implements the {@link Collection} interface and provides
 * methods to manipulate and query the adjacency vector.
 */
public class AdjacencyVect implements  Collection<Integer> {

    private boolean[] adjacency_vector;
    private int size;

    /**
     * Constructs an AdjacencyVect with the specified size.
     * 
     * @param size the size of the adjacency vector
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    public AdjacencyVect(int size) {
        this.size = size;
        this.adjacency_vector = new boolean[size];
    }

    /**
     * Returns the number of true elements in the adjacency vector.
     * 
     * @return the number of true elements in the adjacency vector
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    public int size() {
        int count =0;
        for (int i = 0; i < size; i++) {
            if(adjacency_vector[i]){
                count++;
            }
        }
        return count;
    }

     /**
     * Checks whether the neighborhood vector has any neighbors.     * 
     * @return true if the adjacency vector is empty, false otherwise
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    public boolean isEmpty() {
        boolean control= true;
        for (int i = 0; i < size; i++) {
            if(adjacency_vector[i]){
                control= false;
                break;
            }
        }
        return control;
    }

    /**
     * Checks if the adjacency vector contains the specified element.
     * 
     * @param o the element to check
     * @return true if the element is present, false otherwise
     * @throws IndexOutOfBoundsException if the element is out of bounds
     * Time Complexity: O(1).
     */
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        Integer e = (Integer) o;
        if (e < 0 || e >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return adjacency_vector[e];
    }

    /**
     * Returns an iterator over the elements in the adjacency vector.
     * 
     * @return an iterator over the elements in the adjacency vector
     * Time Complexity: O(1) for creating the iterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new AdjacencyVectIterator();
    }

    /**
     * Returns an array containing all elements in the adjacency vector.
     * 
     * @return an array containing all elements in the adjacency vector
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    public Object[] toArray() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (adjacency_vector[i]) {
                count++;
            }
        }
    
        Object[] result = new Object[count];
        int index = 0;
    
        for (int i = 0; i < size; i++) {
            if (adjacency_vector[i]) {
                result[index++] = i;
            }
        }
    
        return result;
    }
    
    /**
     * Returns an array containing all elements in the adjacency vector.
     * 
     * @param a the array into which the elements will be stored
     * @return an array containing all elements in the adjacency vector
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int count = 0;
        for (boolean b : adjacency_vector) {
            if (b) count++;
        }

        int index = 0;
        for (int i = 0; i < size; i++) {
            if (adjacency_vector[i]) {
                a[index++] = (T) Integer.valueOf(i);
            }
        }
        if (a.length > count) {
            for (int i = count; i < a.length; i++) {
                a[i] = null;
            }
        }
        

    return a;
    }

    /**
     * Adds the specified element to the adjacency vector.
     * 
     * @param e the element to add
     * @return true if the element was added, false otherwise
     * @throws IndexOutOfBoundsException if the element is out of bounds
     * Time Complexity: O(1).
     */
    @Override
    public boolean add(Integer e) {
        if (e < 0 || e >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (!adjacency_vector[e]) {
            adjacency_vector[e] = true;
            return true;
        }
        return false;
    }

    /**
     * Removes the specified element from the adjacency vector.
     * 
     * @param o the element to remove
     * @return true if the element was removed, false otherwise
     * @throws IndexOutOfBoundsException if the element is out of bounds
     * Time Complexity: O(1).
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        Integer e = (Integer) o;
        if (e < 0 || e >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (adjacency_vector[e]) {
            adjacency_vector[e] = false;
            return true;
        }
        return false;
    }

    /**
     * Checks if the adjacency vector contains all elements in the specified collection.
     * 
     * @param c the collection to check
     * @return true if all elements are present, false otherwise
     * @throws IndexOutOfBoundsException if any element is out of bounds
     * Time Complexity: O(n), where n is the size of the collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean control= true;
        for (Object o : c) {
            if (!(o instanceof Integer)) {
                control= false;
                break;
            }
            Integer e = (Integer) o;
            if (e < 0 || e >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            if (!adjacency_vector[e]) {
                control= false;
                break;
            }
        }
        return control;
    }

    /**
     * Adds all elements in the specified collection to the adjacency vector.
     * 
     * @param c the collection of elements to add
     * @return true if any element was added, false otherwise
     * @throws IndexOutOfBoundsException if any element is out of bounds
     * Time Complexity: O(n), where n is the size of the collection.
     */
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean control=false;
        for (Integer e : c){
            if (e < 0 || e >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            if (!adjacency_vector[e]) {
                adjacency_vector[e] = true;
                control=true;
            }
        }
        return control;
    }

    /**
     * Removes all elements in the specified collection from the adjacency vector.
     * 
     * @param c the collection of elements to remove
     * @return true if any element was removed, false otherwise
     * @throws IndexOutOfBoundsException if any element is out of bounds
     * Time Complexity: O(n), where n is the size of the collection.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean control=false;
        for (Object o : c) {
            if (!(o instanceof Integer)) {
                control= false;
                break;
            }
            Integer e = (Integer) o;
            if (e < 0 || e >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            if (adjacency_vector[e]) {
                adjacency_vector[e] = false;
                control=true;
            }
        }
        return control;
    }

    /**
     * Retains only the elements in the adjacency vector that are contained in the specified collection.
     * 
     * @param c the collection of elements to retain
     * @return true if any element was removed, false otherwise
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean control=false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(i)) {
                if (adjacency_vector[i]) {
                    adjacency_vector[i] = false;
                    control=true;
                }
            }
        }
        return control;
        
    }

    /**
     * Clears the adjacency vector, removing all elements.
     * 
     * Time Complexity: O(n), where n is the size of the adjacency vector.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            adjacency_vector[i] = false;
        }
    }

    /**
     * An iterator for the AdjacencyVect class.
     * 
     * Time Complexity: O(1) for creating the iterator, O(n) for iteration.
     */
    private class AdjacencyVectIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < size && !adjacency_vector[currentIndex]) {
                currentIndex++;
            }
            return currentIndex < size;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            while (currentIndex < size && !adjacency_vector[currentIndex]) {
                currentIndex++;
            }
            return currentIndex++;
        }
    }
}
