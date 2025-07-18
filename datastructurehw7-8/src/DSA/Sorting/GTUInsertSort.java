package DSA.Sorting;

import java.util.Comparator;

/**
 * A class that implements the insertion sort algorithm.
 * This class extends {@link GTUSorter} and provides a concrete implementation
 * of the {@code sort} method for sorting arrays using the insertion sort technique.
 */
public class GTUInsertSort extends GTUSorter{

    /**
     * Sorts the specified portion of the array using the insertion sort algorithm.
     * 
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param start the starting index of the portion to be sorted (inclusive)
     * @param end the ending index of the portion to be sorted (exclusive)
     * @param comparator the comparator to determine the order of the elements
     * 
     * Time Complexity:
     * - Best case: O(n), when the array is already sorted.
     * - Worst case: O(n^2), when the array is sorted in reverse order.
     * - Average case: O(n^2).
     */
    @Override 
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
       for(int i=start+1;i<end;i++){
            T next_value= arr[i];
            int j=i-1;
            while(j>=start && comparator.compare(arr[j],next_value)>0){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=next_value;
       }
    }
}
