import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO

        Arrays.sort(occupied);

        if (occupied.length == 0) return 0;
        if (M >= occupied.length) return occupied.length;

        ArrayList<Integer> gaps = new ArrayList<Integer>();

        int last_stall = occupied[0];
        for (int stall : occupied) {

            if (stall - last_stall > 1) {
                gaps.add(stall - last_stall - 1);
            }

            last_stall = stall;
        }

        int solution = occupied[occupied.length - 1] - occupied[0] + 1;
        Collections.sort(gaps);

        while (M > 1) {

            if (gaps.size() == 0) break;

            solution -= gaps.get(gaps.size() - 1);
            gaps.remove(gaps.size() - 1);
            M --;
        }

        return solution;
    }
}