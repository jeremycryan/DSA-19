package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxes;

    public MyStack() {
        ll = new LinkedList<>();
        maxes = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);

        if (maxes.isEmpty()) {
            maxes.addFirst(e);
        } else {
            maxes.addFirst(Math.max(e, maxes.get(0)));
        }

    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        maxes.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return maxes.get(0);
    }
}
