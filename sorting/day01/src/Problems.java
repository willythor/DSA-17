import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }

        return out;
    }


    /**
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> below_median = maxPQ();
        PriorityQueue<Integer> above_median = minPQ();

        int i = 0;

        for (int k : inputStream) {
            if (above_median.size() == 0 || k > above_median.peek()) {
                above_median.add(k);
            } else {
                below_median.add(k);
            }

            if (above_median.size() > (below_median.size() + 1)) {
                below_median.add(above_median.poll());
            }
            if (below_median.size() > above_median.size()) {
                above_median.add(below_median.poll());
            }

            if (above_median.size() == below_median.size()) {
                runningMedian[i] = (below_median.peek() + above_median.peek()) / 2.0;
            } else if (above_median.size() == below_median.size() + 1) {
                runningMedian[i] = above_median.peek();
            } else {
                System.out.println("THIS IS BAD WHAT HAPPENED");
            }
            i++;
        }
//        for (int j = 0; j < runningMedian.length; j++) {
//            System.out.print(runningMedian[j]);
//            System.out.print(" ");
//        }
//        System.out.println(" ");
        return runningMedian;
    }

}
