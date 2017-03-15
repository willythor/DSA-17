import java.util.*;
import java.util.Arrays;

public class Problems {

    private static TreeNode<Integer> minHeightHelper(List<Integer> values, int start, int end){

        if (start > end) {return null;}

        int mid = (start + end) / 2;

        TreeNode node = new TreeNode(values.get(mid));

        node.leftChild = minHeightHelper(values, start, mid - 1);

        node.rightChild = minHeightHelper(values, mid + 1, end);

        return node;
    }

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);
        TreeNode root = minHeightHelper(values,0,values.size()-1);
        BinarySearchTree min = new BinarySearchTree();
        min.root = root;
        return min;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {

        if (n1 == null && n2 == null)
            return true;

        if (n1 == null || n2 == null)
            return false;

        if (n1.key != n2.key)
            return false;

        return (isIsomorphic(n1.leftChild,n2.leftChild) && isIsomorphic(n1.rightChild,n2.rightChild))||
                (isIsomorphic(n1.leftChild,n2.rightChild) && isIsomorphic(n1.rightChild,n2.leftChild));
        }

}
