import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MCCR {

    public static int MCCR(EdgeWeightedGraph G) {

        int start_vertex = 1;
        PriorityQueue<Edge> edges = new PriorityQueue(10, new EdgeWeightComparator());
        ArrayList<Edge> actual_edges = new ArrayList();
        HashSet<Integer> visited = new HashSet();
        visited.add(start_vertex);

        for (Edge edge : G.edges(start_vertex)) {
            edges.add(edge);
        }

        while (true) {

            if (edges.isEmpty()) break;
            Edge cheapest_edge = edges.poll();

            int v = cheapest_edge.either();
            int w = cheapest_edge.other(v);
            if (visited.contains(v) && !visited.contains(w)) {

                actual_edges.add(cheapest_edge);
                visited.add(w);
                for (Edge e : G.edges(w)) {
                    if (e.other(w) != v) {
                        edges.add(e);
                    }
                }

            } else if (visited.contains(w) && !visited.contains(v)) {

                actual_edges.add(cheapest_edge);
                visited.add(v);
                for (Edge e : G.edges(v)) {
                    if (e.other(v) != w) {
                        edges.add(e);
                    }
                }

            }

        }

        int total = 0;
        for (Edge e : actual_edges) {
            total += e.weight();
        }

        return total;

    }

}

class EdgeWeightComparator implements Comparator<Edge> {

    public int compare(Edge e1, Edge e2) {

        if (e1.weight() < e2.weight()) {
            return -1;
        } else if (e1.weight() > e2.weight()) {
            return 1;
        } else {
            return 0;
        }

    }

}