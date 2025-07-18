package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * A class that implements the quicksort algorithm with an optional fallback to a subsorter.
 * This class extends {@link GTUSorter} and provides a concrete implementation
 * of the {@code sort} method for sorting arrays using the quicksort technique.
 */
public class GTUQuickSort extends GTUSorter {
    private Random random;
    private GTUSorter subsorter;
    private int subSortLimit;

    /**
     * Constructs a GTUQuickSort instance with a specified subsorter and sub-sort limit.
     *
     * @param subsorter the sorter to use for small subarrays
     * @param subSortLimit the size limit below which the subsorter is used
     */
    public GTUQuickSort(GTUSorter subsorter, int subSortLimit) {
        this.random = new Random();
        this.subsorter = subsorter;
        this.subSortLimit = subSortLimit;
    }

    /**
     * Constructs a GTUQuickSort instance with no subsorter and no partition limit.
     * In this case, pure quicksort is applied.
     */
    public GTUQuickSort() {
        this.random = new Random();
        this.subsorter = null;
        this.subSortLimit = 0;
    }

    /**
     * Sorts the specified portion of the array using the quicksort algorithm.
     * If a subsorter is defined and the portion is small enough, the subsorter is used instead.
     *
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param start the starting index (inclusive)
     * @param end the ending index (exclusive)
     * @param comparator the comparator to determine order
     */
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        if (subsorter != null && (end - start) <= subSortLimit) {
            subsorter.sort(arr, start, end, comparator);
        } else {
            if (end - start <= 1) return; 
            int pivotIndex = partition(arr, start, end, comparator);
            sort(arr, start, pivotIndex, comparator);
            sort(arr, pivotIndex + 1, end, comparator);
        }
    }

    /**
     * Partitions the array between start and end (exclusive) around a randomly chosen pivot.
     *
     * @param <T> the type of elements
     * @param arr the array
     * @param start start index (inclusive)
     * @param end end index (exclusive)
     * @param comparator comparator to decide order
     * @return the final index of the pivot
     */
    protected <T> int partition(T[] arr, int start, int end, Comparator<T> comparator) {
        int pivotIndex = random.nextInt(end - start) + start;
        T pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, end - 1); 
        int storeIndex = start;
        for (int i = start; i < end - 1; i++) {
            if (comparator.compare(arr[i], pivotValue) < 0) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, end - 1);
        return storeIndex;
    }

    /**
     * Swaps two elements in the array.
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
