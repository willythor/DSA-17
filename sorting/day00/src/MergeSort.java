import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     *
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        //Split the array in half in two parts
        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];

        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        //merge the two recursively sorted arrays
        return merge(sort(first), sort(second));
    }
    public void print(int[] array) {
        for (int i = 0; i<array.length; i++) {
            System.out.print(array[i]);
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */

    private int[] appendElems(int[] out, int[] in, int index_out, int index_in) {
        int in_inc = 0;

        for (int i = index_out; i<out.length; i++) {
            out[i] = in[index_in+in_inc];
            in_inc++;
        }
        return out;

    }

    public int[] merge(int[] a, int[] b) {
        int[] output = new int[(a.length + b.length)];
        int i = 0;
        int j = 0;

        while ((j<b.length) && (i<a.length)) {
            while ((j<b.length) && (i<a.length) && (a[i] <= b[j])) {
                output[i + j] = a[i];
                i++;
            }
            while ((j<b.length) && (i<a.length) && (a[i] > b[j])) {
                output[j + i] = b[j];
                j++;
            }

        }
        if ((i == a.length) && (j == b.length)) {
            return output;
        }
        else if (j != b.length) {
            output = appendElems(output,b,(i+j), j);
        }
        else if (i != a.length) {
            output = appendElems(output,a,(i+j),i);
        }

        return output;
    }
}
