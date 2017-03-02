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

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int i) {

       int l = leftChild(i);
       int r = rightChild(i);
       int largest;
       if (l < size && heap[l] > heap[i]){
           largest = l;
       }
       else {largest = i;}
       if (r < size && heap[r] > heap[largest]){
           largest = r;
       }
       if (largest != i) {
           swap(heap,i,largest);
           heapify(largest);
       }

    }

    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i = size/2-1; i>=0; i--) {
            heapify(i);
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
        buildHeapFrom(array);
        for (int i = array.length-1; i >=1; i--){
            swap(heap,0,i);
            size = size-1;
            heapify(0);
        }
        return heap;
    }
}
