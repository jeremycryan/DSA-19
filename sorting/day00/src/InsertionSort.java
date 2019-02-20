
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
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

        if (array.length <= 1) return array;

        for (int num_sorted = 1; num_sorted < array.length; num_sorted++) {

            int check_idx = num_sorted;
            int temp_val;

            while (array[check_idx] < array[check_idx - 1]) {

                temp_val = array[check_idx];
                array[check_idx] = array[check_idx - 1];
                array[check_idx - 1] = temp_val;
                check_idx --;

                if (check_idx == 0) break;

            }

        }

        return array;
    }
}
