/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private State initialState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO implement this
        while (state.prev != null) {
            state = state.prev;
        }
        return state;
    }

    // Comparator for sorting states by cost
    class sortByCost implements Comparator<State>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(State a, State b)
        {
            return (a.cost + a.moves) - (b.cost + b.moves);
        }
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here
        Queue<State> board_queue = new PriorityQueue<>(10, new sortByCost());
        initialState = new State(initial, 0, null);
        board_queue.add(initialState);

        if (!initial.solvable()) {
            solutionState = null;
            solved = true;
            return;
        }

        while (true) {

            // Pull the state with the best heuristic
            State cur_state = board_queue.poll();

            //cur_state.board.printBoard();

            // If board is solved, return the solution state
            if (cur_state.board.isGoal()) {
                solutionState = cur_state;
                solved = true;
                minMoves = cur_state.moves;
                break;
            }

            // Add all neighboring tiles to the queue
            for (Board neighbor : cur_state.board.neighbors()) {
                board_queue.add(new State(neighbor, cur_state.moves + 1, cur_state));
            }

        }

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return initialState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {

        // Return null if no valid solution exists
        if (solutionState == null) return null;

        State cur_state = solutionState;
        List<Board> sol = new ArrayList<>();

        // Iterate through previous boards
        while(cur_state != null) {
            sol.add(cur_state.board);
            cur_state = cur_state.prev;
        }

        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
