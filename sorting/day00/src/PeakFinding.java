import java.util.Arrays;

/**
 * Created by sidd on 2/20/17.
 */


public class PeakFinding {

    public static int findPeakHelper(int[] nums, int n,int low, int high) {

        int mid = low + (high - low)/2;

        if ((mid == 0 || nums[mid-1] <= nums[mid]) && (mid == n-1 ||
                nums[mid+1] <= nums[mid]))
            return mid;


        else if (mid > 0 && nums[mid-1] > nums[mid])
            return findPeakHelper(nums, low, (mid -1), n);

        else return findPeakHelper(nums, (mid + 1), high, n);
    }

    public static int findOneDPeak(int[] nums){

        return findPeakHelper(nums,nums.length,0,nums.length);

    }

    public static int[] findTwoDPeak(int[][] nums){
    	// TODO
        int[] answer = {-1,-1};
        return answer;
    }

}
