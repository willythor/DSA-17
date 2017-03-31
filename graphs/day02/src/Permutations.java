import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        //n! permutations
        List<List<Integer>> res = new ArrayList<>();

        if (A.size()==0){
            return res;
        }

        if (A.size()==1) {
            res.add(A);
            return res;
        }

        List<Integer> tempList = new LinkedList<Integer>(A);

        int loc = 0;

        for (Integer head: A){
            tempList.remove(head);
            for (List<Integer> perms: (permutations(tempList))){
                List<Integer> permsRecurs = new LinkedList<Integer>(perms);
                permsRecurs.add(0,head);
                res.add(permsRecurs);
            }
            tempList.add(loc,head);
            loc++;
        }
        return res;
    }

}
