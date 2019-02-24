public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][keys.length];

        int sorted = 0;

        for (int i = 0; i < keys.length; i++) {

            for (int j = 0; j < locks.length; j++) {

                if (keys[i] == locks[j]) {

                    result[0][sorted] = locks[j];
                    result[1][sorted] = keys[i];

                    sorted++;
                    break;
                }

            }

        }

        for (int i = 0; i < keys.length; i++) System.out.print(result[0][i]);
        System.out.println();
        for (int i = 0; i < locks.length; i++) System.out.print(result[1][i]);
        System.out.println();

        return result;
    }
}




