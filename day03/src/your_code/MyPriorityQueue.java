package your_code;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> queue = new LinkedList<>();

    public void enqueue(Integer item) {
        queue.addLast(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        ListIterator<Integer> queueIter = queue.listIterator();
        Integer maxVal = queueIter.next();
        Integer currentVal;

        while (queueIter.hasNext()) {
            currentVal = queueIter.next();
            if (currentVal.intValue() > maxVal.intValue()) {
                maxVal = currentVal;
            }
        }
        queue.removeFirstOccurrence(maxVal);
        return maxVal;
    }

}