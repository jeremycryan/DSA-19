import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {

        // Initialize empty state array
        char[] initial_state = new char[hoursInDay];
        for (int i = 0; i < hoursInDay; i++) {
            initial_state[i] = '.';
        }

        List<char[]> result = coinsOnAClockRecurse(pennies, nickels, dimes, hoursInDay, initial_state, 0);
        return result;
    }

    static List<char[]> coinsOnAClockRecurse(int pennies, int nickels, int dimes, int hoursInDay, char[] state, int cur_idx) {

        print_array(state);
        List<char[]> solution = new ArrayList<>();

        // If the clock is completely full, return the current state because it was successful
        int num_empty = 0;
        for (char coin : state) {
            if (coin == '.') num_empty++;
        }
        if (num_empty == 0) {
            solution.add(state);
            return solution;
        }

        // Otherwise, if the current spot is occupied, return an empty list for the collision
        if (state[cur_idx] != '.') return solution;

        // Otherwise, place each coin and recurse
        char[] temp_state = new char[state.length];
        int new_idx;

        if (pennies > 0) {
            temp_state = array_copy(state);
            temp_state[cur_idx] = 'p';
            new_idx = (cur_idx + 1)%hoursInDay;
            for(char[] each_solution : coinsOnAClockRecurse(pennies - 1, nickels, dimes, hoursInDay, temp_state, new_idx)) {
                solution.add(each_solution);
            };
        }

        if (nickels > 0) {
            temp_state = array_copy(state);
            temp_state[cur_idx] = 'n';
            new_idx = (cur_idx + 5)%hoursInDay;
            for(char[] each_solution : coinsOnAClockRecurse(pennies, nickels - 1, dimes, hoursInDay, temp_state, new_idx)) {
                solution.add(each_solution);
            };
        }

        if (dimes > 0) {
            temp_state = array_copy(state);
            temp_state[cur_idx] = 'd';
            new_idx = (cur_idx + 10)%hoursInDay;
            for(char[] each_solution : coinsOnAClockRecurse(pennies, nickels, dimes - 1, hoursInDay, temp_state, new_idx)) {
                solution.add(each_solution);
            };
        }

        return solution;

    }

    static char[] array_copy(char[] a) {

        char[] b = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        return b;

    }

    static void print_array(char[] a) {

        System.out.print('[');
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println(" ]");

    }

}
