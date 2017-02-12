package your_code;

import ADTs.QueueADT;

import java.util.LinkedList;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {
    private LinkedList<Integer> queue = new LinkedList<>();


    @Override
    public void enqueue(Integer item) {
        queue.addLast(item);
    }

    @Override
    public Integer dequeue() {
        Integer elem = queue.removeFirst();
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public Integer front() {
        return queue.peek();
    }

}