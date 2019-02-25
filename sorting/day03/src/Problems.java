import java.util.LinkedList;

import static java.lang.Math.pow;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        for (int i=0; i < A.length; i++) A[i] += 100;
        CountingSort.countingSort(A);
        for (int i=0; i < A.length; i++) A[i] -= 100;
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }

    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {

        // Determine maximum value of thing
        int max_val = 0;
        for (String s : A) {
            int nth = getNthCharacter(s, n);
            if (nth > max_val) max_val = nth;
        }

        LinkedList<String>[] L = new LinkedList[max_val + 1];
        for (int i = 0; i < max_val + 1; i++)
            L[i] = new LinkedList<>();
        for (String s : A) {
            int sort_val = getNthCharacter(s, n);
            L[sort_val].addLast(s);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (String item : list) {
                A[j] = item;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int  i = 0; i < stringLength; i++) {
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
