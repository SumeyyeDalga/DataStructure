package DSA.Sorting;

/**
 * A class that implements the selection sort algorithm.
 * This class extends {@link GTUSorter} and provides a concrete implementation
 * of the {@code sort} method for sorting arrays using the selection sort technique.
 */
public class GTUSelectSort extends GTUSorter {

    /**
     * Sorts the specified portion of the array using the selection sort algorithm.
     * 
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param start the starting index of the portion to be sorted (inclusive)
     * @param end the ending index of the portion to be sorted (exclusive)
     * @param comparator the comparator to determine the order of the elements
     * 
     * Time Complexity: O(n^2), where n is the size of the portion to be sorted.
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, java.util.Comparator<T> comparator) {
        for (int i = start; i < end - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Swaps two elements in the array.
     * 
     * @param <T> the type of elements in the array
     * @param arr the array in which the elements are to be swapped
     * @param i the index of the first element
     * @param j the index of the second element
     * 
     * Time Complexity: O(1).
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
