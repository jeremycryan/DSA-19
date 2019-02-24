public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child

    // Best, worse, and average case O(NlogN)
    public void sink(int i) {

        int child_to_swap;

        // If i has no children within range of index, end recursive call.
        if (leftChild(i) >= size) {
            return;
        }

        // If no right child exists, swap if left child is greater.
        if (rightChild(i) >= size) {

            if (heap[leftChild(i)] <= heap[i]) {

                return; // Left child can have no children if right child doesn't exist.

            } else {

                child_to_swap = heap[leftChild(i)];
                heap[leftChild(i)] = heap[i];
                heap[i] = child_to_swap;
                sink(leftChild(i));
                return;

            }

        }

        // If both children exist, sink to the greater one.
        if (heap[leftChild(i)] > heap[i] && heap[leftChild(i)] >= heap[rightChild(i)]) {

            child_to_swap = heap[leftChild(i)];
            heap[leftChild(i)] = heap[i];
            heap[i] = child_to_swap;
            sink(leftChild(i));
            return;

        }

        if (heap[rightChild(i)] > heap[i] && heap[rightChild(i)] > heap[leftChild(i)]) {

            child_to_swap = heap[rightChild(i)];
            heap[rightChild(i)] = heap[i];
            heap[i] = child_to_swap;
            sink(rightChild(i));
            return;

        }

    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            this.sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        int sorted_element;
        for (int i=size-1; i>0; i--) {

            // Iteratively pluck the highest element and sink whatever you replace it with.
            sorted_element = heap[0];
            heap[0] = heap[size - 1];
            heap[size - 1] = sorted_element;
            size --;
            sink(0);

        }

        return heap;
    }
}
