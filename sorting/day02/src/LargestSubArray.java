import java.util.HashMap;


// Runtime O(N)
public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {

        int[] parity = new int[nums.length + 1];
        int total = 0;

        // Generate parity array
        parity[0] = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) total++;
            if (nums[i] == 0) total--;
            parity[i+1] = total;

        }

        // Iteratively assembly hash map and compare to last time you saw that parity
        HashMap first_idx = new HashMap<Integer, Integer>();
        int max_max_distance = 0;
        int max_first_idx = 0;

        for (int i = 0; i < parity.length; i++) {

            if (first_idx.containsKey(parity[i])) {

                int new_max_distance = i - (int)first_idx.get(parity[i]) - 1;
                if (new_max_distance > max_max_distance) {

                    max_max_distance = new_max_distance;
                    max_first_idx = (int)first_idx.get(parity[i]);

                }

            } else {

                first_idx.put(parity[i], i);

            }

        }

        // Return array bounds
        return new int[]{max_first_idx, max_first_idx + max_max_distance};
    }
}
