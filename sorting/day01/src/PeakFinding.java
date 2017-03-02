public class PeakFinding {

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums){
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (hi+lo)/2;
            int direction = peak(mid, nums);
            if (direction == 0) return mid;
            else if (direction == -1) hi = mid;
            else if (direction == 1) lo = mid+1;
        }
        return -1;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int x_lo = 0;
        int x_hi = nums[0].length;
        int y_lo = 0;
        int y_hi = nums.length;
        int i = 0;

        while ((x_lo < x_hi) && (y_lo < y_hi)) {
            if (i % 2 == 0) {
//                checking a column
                int mid_x = (x_hi + x_lo) / 2;
                int y_index = maxYIndex(mid_x, y_lo, y_hi, nums);
                int direction = peakX(mid_x,y_index,nums);
                if (direction == 0) return new int[]{y_index, mid_x};
                else if (direction == 1) {
                    x_lo = mid_x+1;
                }
                else if (direction == -1) {
                    x_hi = mid_x;
                }

            } else {
//               checking a row
                int mid_y = (y_hi + y_lo) / 2;
                int x_index = maxXIndex(mid_y, x_lo, x_hi, nums);
                int direction = peakY(x_index,mid_y,nums);
                if (direction == 0) return new int[]{mid_y, x_index};
                else if (direction == 1) {
                    y_lo = mid_y + 1;
                }
                else if (direction == -1) {
                    y_hi = mid_y;
                }

            }
            i++;
        }
        return new int[]{-1, -1};
    }

}
