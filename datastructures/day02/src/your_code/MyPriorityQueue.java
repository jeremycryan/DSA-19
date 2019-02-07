package your_code;
import ADTs.QueueADT;

import java.util.ArrayList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private ArrayList<Integer> ll;
    private int size = 0;
    private int max;

    public MyPriorityQueue() {
        ll = new ArrayList<Integer>();
        size = 0;
    }

    public void enqueue(int item) {
        // TODO

        if (ll.isEmpty()) {
            max = item;
        } else {
            max = Math.max(item, max);
        }

        ll.add(item);
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        // TODO

        int cur_val;
        int to_remove = -1;
        for (int i = 0; i < size; i++) {

            cur_val = ll.get(i);

            if (cur_val == max) {
                size--;
                to_remove = i;
            }
        }

        int return_val;
        if (to_remove != -1) {
            return_val = ll.remove(to_remove);
        } else {
            return_val = -1;
        }

        max = -1;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, ll.get(i));
        }

        return return_val;

    }

}