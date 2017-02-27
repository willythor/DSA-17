import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: 0(1)
     */
    @Override
    public int[] sort(int[] array) {
        for(int i = 1; i<array.length; i++) {
            int j = i-1;
            while(j>=0 && array[i] < array[j]) {
                swap(array, i, j);
                i = j;
                j--;
            }

        }
        return array;
    }

    @Test
    public void testSort() {
        int[] arr = {4,1,2,3,5};
        int[] expected = {1,2,3,4,5};
        assertEquals("Result", expected, sort(arr));
    }
}
