import java.util.List;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = A[0];
        for (int m: A) {
            if (m>max) {
                max = m;
            }
        }

        int[] keys = new int[max+1];

        for (int f: A) {
            keys[f]++;
        }

        int i = 0;
        for (int j = 0; j <= max; j++) {
            while (keys[j] > 0) {
                A[i] = j;
                keys[j]--;
                i++;
            }

        }

    }

}
