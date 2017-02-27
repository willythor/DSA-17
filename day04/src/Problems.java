import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map map = new MyLinearMap<>();
        for (int i = 0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                int count = (int) map.get(arr[i]);
                count++;
                map.put(arr[i],count);
            }
            else {map.put(arr[i],1);}
        }
        return map;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        LinkedList<Integer> output = new LinkedList<>();
        int current;
        for (int i = 0; i < num.length; i++) {
            current = num[i];
            while ((!output.isEmpty()) && current < output.getLast() && (k>0)) {
                output.removeLast();
                k--;}

            if (output.size() < num.length - k) {
                output.addLast(current);
            }
        }
        return output;
    }

    public static int sumList(Node<Integer> h) {
        Node current_node = h;
        int sum = 0;

        while (current_node!=null) {
            int data = (int) current_node.data;
            sum = sum*10 + data;
            current_node = current_node.next;
        }
        return sum;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {

        return sumList(h1) + sumList(h2);
//
    }

}
