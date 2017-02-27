public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        quickSort(array,0, array.length-1);
//        for(int i = 0; i <array.length;i++){
//            System.out.print(array[i]);
//        }
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {
        if(low<high)
        {
            int q=partition(a,low,high);
            quickSort(a,low,q);
            quickSort(a,q+1,high);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low-1 ;
        int j = high+1 ;

        while (true) {
            i++;
            while ( i< high && array[i] < pivot)
                i++;
            j--;
            while (j>low && array[j] > pivot)
                j--;

            if (i < j)
                swap(array, i, j);
            else
                return j;
        }
    }
}
