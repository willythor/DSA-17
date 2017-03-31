import java.lang.reflect.Array;
import java.util.*;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    private static HashMap<Integer,HashSet<Integer>> copyOf(HashMap<Integer,HashSet<Integer>> dict){
        HashSet<Integer> tempSet;
        HashMap<Integer,HashSet<Integer>> res = new HashMap<>(dict);
        for (int i: dict.keySet()){
            tempSet = new HashSet<>(res.get(i));
            res.put(i,tempSet);
        }
        return res;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        HashMap<Integer,HashSet<Integer>> notAllowed = new HashMap<>();
        HashMap<Integer,HashSet<Integer>> tempNotAllowed;
        HashSet<Integer> tempSet;
        List<char[][]> res = new ArrayList<>();
        char[][] board = new char[n][n];
        char[][] tempBoard;
        List<char[][]> tempList;

        //create empty initial board
        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                board[i][j] = '.';
            }
        }

        for (int i = 0; i<n; i++){
            tempSet = new HashSet<>();
            notAllowed.put(i,tempSet);
        }

        //loop through all positions of first row of board
        for (int i=0; i<n; i++){ // O(N*
            //dict of not allowed positions
            tempNotAllowed = copyOf(notAllowed);
            tempNotAllowed = notAllowed(tempNotAllowed,n,0,i);

            //temp board to be passed along to nQueensChecker
            tempBoard = copyOf(board);
            tempBoard[0][i] = 'Q';

            //list of possible boards
            tempList = (nQueensChecker(tempBoard, n,1,tempNotAllowed));

            if (tempList == null){
                continue;
            }

            res.addAll(tempList);
        }
//
//        for (int i = 0; i<res.size(); i++){
//            System.out.println(Arrays.toString(res.get(i)));
//        }
        return res;
    }

    public static List<char[][]> nQueensChecker(char[][] board, int n, int row, HashMap<Integer,HashSet<Integer>> notAllowed){
        List<char[][]> res = new ArrayList<>();
        List<char[][]> tempList;
        char[][] tempBoard;
        HashMap<Integer,HashSet<Integer>> tempNotAllowed;

        if (n-1==row){
            for (int i = 0; i<n; i++){
                tempBoard = copyOf(board);
                if (notAllowed.get(row).contains(i)){
                    continue;
                }
                else {
                    tempBoard[row][i]='Q';
                    res.add(tempBoard);
                }
            }
            if (res.size()== 0){return null;}
            return res;
        }

        /*
        T_N = N * (N-1) * (N-2) * T_(N-3) ... + 0
        T_(N-1) = (N-1) * T_(N-2)
        ...
         */

        for (int i = 0; i<n; i++){ // O(N * T(N-1))
            //copy of board and notAllowed dict
            tempBoard = copyOf(board);
            tempNotAllowed = copyOf(notAllowed);

            if (notAllowed.get(row).contains(i)){
                //if position is not allowed, skip
                continue;
            }

            //add queen to allowed position
            tempBoard[row][i] = 'Q';
            //update notAllowed dict
            tempNotAllowed = notAllowed(tempNotAllowed,n,row,i);
            //get list of possibilities
            tempList = (nQueensChecker(tempBoard,n,row+1,tempNotAllowed)); // problem size N-1

            //if list of possibilities is not null, add them all to res
            if (tempList!=null){
                res.addAll(tempList);
            }
        }

        if (res.size()==0) {return null;}
        return res;
    }

    private static HashMap<Integer,HashSet<Integer>> notAllowed(HashMap<Integer,HashSet<Integer>> notAllowed, int n, int row, int column){
        int indexColumn = column;
        int indexRow = row;

        while (indexRow<n){
            notAllowed.get(indexRow).add(column);
            indexRow++;
        }

        indexRow = row;
        while (indexColumn<n && indexRow<n){
            notAllowed.get(indexRow).add(indexColumn);
            indexRow++;
            indexColumn++;
        }

        indexColumn = column;
        indexRow = row;
        while (indexColumn>=0&&indexRow<n){
            notAllowed.get(indexRow).add(indexColumn);
            indexColumn--;
            indexRow++;
        }

        return notAllowed;
    }

    public static void main(String[] args){

        List<char[][]> res = (nQueensSolutions(4));
        for (int i = 0; i<res.size();i++){
            for (int j = 0;j<res.get(i).length; j++){
                System.out.println(Arrays.toString(res.get(i)[j]));
            }
            System.out.println("");
        }
    }


}
