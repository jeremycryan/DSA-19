package divide_and_conquer;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {

        // if list has one element, return it
        if (nums.length == 1) return nums[0];

        // Recurse!
        return findOneDPeakRecursive(nums, 0, nums.length - 1);

    }

    public static int findOneDPeakRecursive(int[] nums, int start_idx, int end_idx) {


        if (start_idx == end_idx) return start_idx;

        int middle_idx = (end_idx - start_idx) / 2 + start_idx;
        int peak_value = peakOneD(middle_idx, nums);

        if (peak_value == 0) {
            return middle_idx;
        } else if (peak_value < 0) {
            return findOneDPeakRecursive(nums, start_idx, middle_idx - 1);
        } else {
            return findOneDPeakRecursive(nums, middle_idx + 1, end_idx);
        }

    }

    public static int[] findTwoDPeak(int[][] nums) {

        return findTwoDPeakRecursive(nums, 0, nums.length - 1, 0, nums[0].length - 1);

    }

    public static int[] findTwoDPeakRecursive(int[][] nums, int y_min, int y_max, int x_min, int x_max) {

        int ydiff = y_max - y_min;
        int xdiff = x_max - x_min;
        int return_vals[] = {0, 0};

        if (xdiff <= ydiff) {

            // Find local max in column if thing is taller than wide
            int x_mid = (x_max - x_min)/2 + x_min;
            int max = -999999;
            int max_idx = 0;
            for (int y = y_min; y <= y_max; y++) {
                if (nums[y][x_mid] > max) {
                    max = nums[y][x_mid];
                    max_idx = y;
                }
            }

            // If found value is peak, return it (base case)
            if (peakX(x_mid, max_idx, nums) == 0) {
                return_vals[0] = max_idx;
                return_vals[1] = x_mid;
                return return_vals;
            }

            // If increases in y, cut array in half lengthwise (divide)
            else if (peakX(x_mid, max_idx, nums) > 0) {
                // Recursive step (conquer)
                return findTwoDPeakRecursive(nums, y_min, y_max, x_mid + 1, x_max);
            }

            // Otherwise, cut but keep the other half (divide)
            else if (peakX(x_mid, max_idx, nums) < 0) {
                // Recursive step (conquer)
                return findTwoDPeakRecursive(nums, y_min, y_max, x_min, x_mid - 1);
            }

        }

        if (ydiff <= xdiff) {

            // Find local max in column if thing is taller than wide
            int y_mid = (y_max - y_min)/2 + y_min;
            int max = -999999;
            int max_idx = 0;
            for (int x = x_min; x <= x_max; x++) {
                if (nums[y_mid][x] > max) {
                    max = nums[y_mid][x];
                    max_idx = x;
                }
            }

            // If found value is peak, return it
            if (peakX(max_idx, y_mid, nums) == 0) {
                return_vals[0] = y_mid;
                return_vals[1] = max_idx;
                return return_vals;
            }

            // If increases in y, cut array in half lengthwise
            else if (peakX(max_idx, y_mid, nums) > 0) {
                return findTwoDPeakRecursive(nums, y_mid + 1, y_max, x_min, x_max);
            }

            // Otherwise, cut but keep the other half
            else if (peakX(max_idx, y_mid, nums) < 0) {
                return findTwoDPeakRecursive(nums, y_mid - 1, y_max, x_min, x_max);
            }

        }

        return return_vals;


    }


}
