import java.util.*;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        Queue<TreeNode<Integer>> pq = new LinkedList<>();
        List<Integer> orderedList = new LinkedList<>();
        pq.add(n);

        while (pq.size()!=0){
            TreeNode<Integer> current_node = pq.poll();
            if (current_node.hasLeftChild()){
                pq.add(current_node.leftChild);
            }
            if (current_node.hasRightChild()){
                pq.add(current_node.rightChild);
            }
            orderedList.add(current_node.key);
        }
        return orderedList;
    }
}
