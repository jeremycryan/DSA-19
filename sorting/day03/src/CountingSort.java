import java.util.Arrays;

public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n + k), where k is max array value.
     * Space: O(k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {

        // Find maximum value of array
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) max = A[i];
        }

        // Initialize counting array to zeros
        int[] counts = new int[max + 1];
        Arrays.fill(counts, 0);

        // Iterate through A and populate counts
        for (int i = 0; i < A.length; i++) {
            counts[A[i]]++;
        }

        // Populate a sorted array from the counted elements
        int j = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                A[j] = i;
                counts[i]--;
                j++;
            }
        }

    }

}
