import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        Map<Integer,Integer> boom_hash = new HashMap<>();
        int [] coords_start;
        int [] coords_next;
        int dist;
        int boom_count = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                coords_start = points[i];
                coords_next = points[j];
                dist = getSquaredDistance(coords_start, coords_next);
                Integer val = boom_hash.get(dist);
                if (val == null) {
                    boom_hash.put(dist,1);
                }
                else {
                    boom_hash.put(dist,val+1);
                }

            }
            for (int m: boom_hash.values()){
                boom_count += m*(m-1);
            }
            boom_hash.clear();

        }

        return boom_count;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
