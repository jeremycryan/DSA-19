public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {

        // Number of boomerangs
        int boomerangs = 0;

        for (int i = 0; i < points.length; i++) {

            // Create map mapping distances to count
            int[] cur_point = points[i];
            java.util.HashMap<Double, Integer> distances = new java.util.HashMap<Double, Integer>();

            // Fill dictionary by interating through points, assuming current point is center of boomerang
            for (int j = 0; j < points.length; j++) {

                if (j==i) continue;

                double dist = distance(points[i], points[j]);

                if (distances.containsKey(dist)) {
                    distances.put(dist, distances.get(dist) + 1);
                } else {
                    distances.put(dist, 1);
                }

            }

            // Iterate over map keys to add to boomerang count
            java.util.Iterator map_iterator = distances.entrySet().iterator();
            while (map_iterator.hasNext()) {

                java.util.Map.Entry<Double, Integer> kv = (java.util.Map.Entry)map_iterator.next();
                boomerangs += (kv.getValue() * (kv.getValue() - 1));

            }

        }

        return boomerangs;

    }


    /* Returns the distance between two Euclidean (x, y) points.
     */
    static double distance(int[] p1, int[] p2) {

        double dx = 1.0 * Math.abs(p1[0] - p2[0]);
        double dy = 1.0 * Math.abs(p1[1] - p2[1]);

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

    }

}

