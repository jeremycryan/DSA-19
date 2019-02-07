package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {

        // Set head and tail nodes to null
        head = null;
        tail = null;

        // Set size to zero and value to null
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {

        // Add a chicken to the end of the linked list and change tail value
        Node new_node = new Node(c, tail, null);

        if (size > 0) {
            tail.next = new_node;
        }
        tail = new_node;
        size++;

        if (size == 1) {
            head = new_node;
        }

    }

    public void addFirst(Chicken c) {

        // Add a chicken to the beginning of the list.
        // If there aren't any other chickens, use empty initializer.
        Node new_node;
        if (size >= 1) {
            Node old_head = head;
            new_node = new Node(c, null, old_head);
            head.prev = new_node;
            head = new_node;
        } else {
            new_node = new Node(c);
            head = new_node;
            tail = new_node;
        }

        size++;
    }

    public Chicken get(int index) {

        if ((index < 0) || (index >= size)) {
            return null;
        }

        Node cur_node = head;
        for (int i = 0; i < index; i++) {
            cur_node = cur_node.next;
        }

        return cur_node.val;

    }

    public Chicken remove(int index) {

        if ((index < 0) || (index >= size)) {
            return null;
        }

        Node cur_node = head;
        Node prev_node = null;

        int i=0;
        while (i < index) {
            prev_node = cur_node;
            cur_node = cur_node.next;
            i++;
        }

        if (prev_node!=null) {
            prev_node.next = cur_node.next;
        }

        size--;
        return cur_node.val;
    }

    public Chicken removeFirst() {

        if (size == 0) {
            return null;
        }

        Node old_head = head;

        if (size >= 2) {
            head = head.next;
        }

        size--;
        return old_head.val;
    }

    public Chicken removeLast() {

        if (size == 0) {
            return null;
        }

        Node cur_node = head;
        Node last_node = null;

        while (cur_node.next != null) {
            last_node = cur_node;
            cur_node = cur_node.next;
        }

        // Remove the link from the second-to-last node
        cur_node.next = null;
        size--;

        if (last_node != null) {
            return last_node.val;
        }
        return null;

    }
}
