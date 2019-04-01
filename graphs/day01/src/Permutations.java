import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {

        List<List<Integer>> permutations = new LinkedList<>();

        // If the input list contains no elements, return an empty list.
        if (A.size() == 0) return permutations;

        // If the input list contains a single element, add it to permutations and return.
        if (A.size() == 1) {
            permutations.add(A);
            return permutations;
        }

        // Otherwise, permutate all but the last element, then add it back on.
        int idx = 0;
        for (Integer item: A) {

            // Create a new array with one item missing
            List<Integer> sub_list = new LinkedList<>();
            for (Integer each_int : A) sub_list.add(each_int);
            sub_list.remove(idx);

            // Recursively find each permutation
            List<List<Integer>> new_perms = permutations(sub_list);

            for (List<Integer> perm : new_perms) {

                perm.add(item);
                permutations.add(perm);

            }

            idx++;
        }

        return permutations;
    }

}
