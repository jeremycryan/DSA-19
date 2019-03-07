import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {

        // O(NlogN)
        Collections.sort(values);

        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        generateTreeFromSorted(values, tree);

        return tree;

    }

    public static void generateTreeFromSorted(List<Integer> values, BinarySearchTree<Integer> tree) {


        if (values.size() == 0) {;
            return;
        }

        int median = values.get(values.size()/2);


        List<Integer> left_half = new ArrayList<Integer>();
        List<Integer> right_half = new ArrayList<Integer>();

        for (int i = 0; i < values.size(); i++) {

            if (i == values.size()/2) continue;
            else if (i < values.size()/2) left_half.add(values.get(i));
            else if (i > values.size()/2) right_half.add(values.get(i));

        }

        tree.add(median);
        generateTreeFromSorted(left_half, tree);
        generateTreeFromSorted(right_half, tree);

    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
