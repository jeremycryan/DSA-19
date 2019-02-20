import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }

        return out;
    }


    /**
     *
     * @param A an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] A) {

        // This actually runs a few ms slower than the "really slow" version,
        // but I believe in it anyway.
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        int med_idx = 0;
        int med_idx_2 = 0;
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            med_idx = i/2;
            med_idx_2 = (i + 1)/2;
            out[i] = mean(seen.get(med_idx), seen.get(med_idx_2));
        }

        return out;
    }


    public static double mean(int n, int m) {
        if (n == m) return n;
        double sum = 0;
        sum += n;
        sum += m;
        return sum / 2;
    }

}
