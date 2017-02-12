package your_code;

import ADTs.StackADT;

import java.util.LinkedList;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int longestString = 0;
        int buffer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    buffer = i;
                }
                stack.add("(");

            }
            if (s.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                else {continue;}

                if (stack.isEmpty() && i != 0) {
                    longestString = i-buffer+1;
                    if (longestString > s.length()/2) {
                        return longestString;
                    }
                }
            }
        }
        return longestString;
    }

    private static boolean nextIsLess(Integer current, Integer next) {
        if (current.intValue() < next.intValue()) {
            return true;
        }
        else {return false;}
    }

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        StackADT<Integer> temp = new MyStack();
        Integer elem;

        while (!s.isEmpty()){

            if (temp.isEmpty()) {
                temp.push(s.pop());
                continue;
            }

            if ((s.peek().intValue() < temp.peek().intValue())) {

                elem = s.pop();
                while (elem < temp.peek()){
                    s.push(temp.pop());
                    if (temp.isEmpty())
                    {break;}
                }
                temp.push(elem);
            }

            else {temp.push(s.pop());}
        }

        return temp;

    }

}
