import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal;

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        n = b.length;
        goal = new int[n][n];
        tiles = new int[n][n];

        // Create goal matrix based on size of input board
        int i = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                i++;
                goal[y][x] = i;
                tiles[y][x] = b[y][x];
            }
        }

        goal[n-1][n-1] = 0; //  The bottom right tile should be empty

    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /* Finds the Manhattan distance of a single tile from its target place on the board, given its current
        x and y position.
     */
    private int manhattanTile(int value, int cur_x, int cur_y) {

        // If it's the empty tile, don't count as out of place.
        if (value == 0) {
            return 0;
        }

        // Determine the position the tile should be in, with math
        int target_x = (value - 1) % n;
        int target_y = (int)((value - 1)/n);

        int xdiff = Math.abs(cur_x - target_x);
        int ydiff = Math.abs(cur_y - target_y);

        return xdiff + ydiff;

    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {

        // Sum the individual manhattan distances of each tile
        int total = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                total += manhattanTile(tiles[y][x], x, y);
            }
        }

        return total;

    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (tiles[y][x] != goal[y][x]) return false;
            }
        }

        return true;

    }

    // Returns the x and y coordinates of a tile at position i
    private int[] getTileCoords(int i) {
        int x = i%n;
        int y = (int)(i/n);
        int[] coords = {x, y};
        return coords;
    }

    /* Prints the board
     */
    public void printBoard() {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                System.out.print(tiles[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {

        Set<Integer> observed = new HashSet<>();
        int inversions = 0;
        int blank_parity = 0;
        int n_parity = n%2;

        for (int a = 0; a < n*n; a++) {
            for (int b = a+1; b < n*n; b++) {

                // Increment inversions every time a tile is above a tile with a higher number
                int[] a_pos = getTileCoords(a);
                int[] b_pos = getTileCoords(b);
                int a_tile = tiles[a_pos[1]][a_pos[0]];
                int b_tile = tiles[b_pos[1]][b_pos[0]];
                if (a_tile > 0 && b_tile > 0 && (a_tile > b_tile)) {
                    inversions++;
                }

                if (a_tile == 0) {
                    blank_parity = a_pos[1]%2;
                }

            }
        }

        if (n_parity == 1 && inversions%2 == 0) return true;
        if (n_parity == 0) {
            if (blank_parity == 1 && inversions%2 == 0) return true;
            if (blank_parity == 0 && inversions%2 == 1) return true;
        }
        return false;
    }

    private int[][] tilesCopy() {
        int[][] copy = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                copy[y][x] = tiles[y][x];
            }
        }

        return copy;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here

        List<Board> neighbors = new ArrayList<>();
        int blank_y = -1;
        int blank_x = -1;

        // Identify the location of the blank tile
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (tiles[y][x] == 0) {
                    blank_y = y;
                    blank_x = x;   //  End the loop
                    break;
                }
            }
            if (blank_x != -1 && blank_y != -1) break;
        }

        // Add left neighbor
        if (blank_x > 0) {
            int[][] tiles_copy = tilesCopy();
            tiles_copy[blank_y][blank_x] = tiles_copy[blank_y][blank_x - 1];
            tiles_copy[blank_y][blank_x - 1] = 0;
            neighbors.add(new Board(tiles_copy));
        }

        // Add right neighbor
        if (blank_x < n - 1) {
            int[][] tiles_copy = tilesCopy();
            tiles_copy[blank_y][blank_x] = tiles_copy[blank_y][blank_x + 1];
            tiles_copy[blank_y][blank_x + 1] = 0;
            neighbors.add(new Board(tiles_copy));
        }

        // Add upper neighbor
        if (blank_y > 0) {
            int[][] tiles_copy = tilesCopy();
            tiles_copy[blank_y][blank_x] = tiles_copy[blank_y - 1][blank_x];
            tiles_copy[blank_y - 1][blank_x] = 0;
            neighbors.add(new Board(tiles_copy));
        }

        // Add lower neighbor
        if (blank_y < n - 1) {
            int[][] tiles_copy = tilesCopy();
            tiles_copy[blank_y][blank_x] = tiles_copy[blank_y + 1][blank_x];
            tiles_copy[blank_y + 1][blank_x] = 0;
            neighbors.add(new Board(tiles_copy));
        }

        return neighbors;
    }


    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
