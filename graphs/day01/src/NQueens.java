import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkColumn(char[][] board, int c) {

        int x = c;
        for (int y = 0; y < board.length; y++) {

            if (board[y][x] == 'Q') return true;

        }
        return false;

    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {

        List<char[][]> answers = new ArrayList<>();

        char[][] board = new char[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                board[y][x] = '.';
            }
        }

        answers = nQueensRecurse(n, 0, board);

        return answers;


    }

    static List<char[][]> nQueensRecurse(int n, int r, char[][] board_so_far) {

        List<char[][]> solutions = new ArrayList<>();
        if (r == n) {   //  This means the array has been fully populated.
            solutions.add(board_so_far);
            return solutions;
        }

        char[][] board_copy;

        for (int c = 0; c < n; c++) {

            // Make a copy of the original board
            board_copy = copyOf(board_so_far);

            // If the current column is invalid for that row, don't do any further work
            if (checkColumn(board_copy, c)) continue;
            if (checkDiagonal(board_copy, r, c)) continue;

            // Otherwise, it's valid. Add a queen, and recurse.
            board_copy[r][c] = 'Q';
            List<char[][]> new_solutions = nQueensRecurse(n, r+1, board_copy);

            // Add all new solutions to the solutions list.
            for (char[][] item : new_solutions) {
                solutions.add(item);
                if (r == 0) print_array(item);
            }

        }

        return solutions;

    }

    static void print_array(char[][] a) {

        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[0].length; x++) {
                System.out.print(a[y][x]);
            }
            System.out.println();
        }
        System.out.println();

    }

}
