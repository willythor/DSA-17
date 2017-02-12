package your_code;
import ADTs.StackADT;
import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> stack = new LinkedList<>();
    private LinkedList<Integer> maxElems = new LinkedList<>();

    public MyStack(){
        maxElems.add(Integer.MIN_VALUE);
    }

    @Override
    public void push(Integer e) {
        if (e.intValue() >= maxElems.peekLast().intValue()) {maxElems.addLast(e);}
        stack.addLast(e);
    }

    @Override
    public Integer pop() {
        Integer elem = stack.removeLast();
        if (elem.intValue() == maxElems.peekLast().intValue()) {maxElems.removeLast();}
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Integer peek() {
        return stack.peekLast();
    }

    public Integer maxElement() {
        return maxElems.peekLast();
    }
}
