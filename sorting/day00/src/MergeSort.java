
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {

        // If array is short enough, it's sorted.
        if (array.length <= 1) return array;

        // Initialize some smaller arrays
        int halfway = array.length/2;
        int[] first_half = new int[halfway];
        int[] second_half = new int[array.length - halfway];

        // Add elements of array into two lists of similar length
        for (int i = 0; i < array.length; i++) {

            if (i < halfway) {
                first_half[i] = array[i];
            } else {
                second_half[i - halfway] = array[i];
            }

        }

        // Sort each half, and merge sorted lists
        return merge(sort(first_half), sort(second_half));

    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {

        int[] return_array = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (k < return_array.length) {


            // Catch case where indices pass array bounds on a or b
            if (i >= a.length) {

                return_array[k] = b[j];
                k++;
                j++;
                continue;

            } else if (j >= b.length) {

                return_array[k] = a[i];
                k++;
                i++;
                continue;

            }

            // If indices are valid, determine the lower value of first items in arrays
            if (a[i] < b[j]) {

                return_array[k] = a[i];
                k++;
                i++;

            } else {

                return_array[k] = b[j];
                k++;
                j++;

            }

        }

        // Return merged array
        return return_array;

    }

}
