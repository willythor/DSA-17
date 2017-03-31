import java.util.Arrays;

public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int p1 = 0;
        int p2 = 1;
        int sum;
        int minLength = A.length+1;
        boolean found = false;

        while (p1<A.length && p2<=A.length){
            sum = sum(Arrays.copyOfRange(A,p1,p2));
            if (sum<desiredSum){
                p2=p2+1;
                continue;
            }
            else {
                if ((p2-p1)<minLength){
                    minLength = p2-p1;
                    found = true;
                }
                p1+=1;
                p2=p1+1;
                continue;
            }
        }
        if (found) {return minLength;}
        return 0;
    }

    public static int sum(int[] A){
        int sum=0;
        for(int i: A){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args){
        int[] A1 = new int[]{2,3,1,2,4,3};
        System.out.println(minimumLengthSubArray(A1,4));
    }

}
