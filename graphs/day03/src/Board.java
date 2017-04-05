import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = b;
        n = tiles.length;
    }

    /*
     * Size of the board (equal to 3 for 8 puzzle, but in theory the Board
     * class should  work for any puzzle size)
     */
    private int size() {
        return tiles.length;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     * Estimated cost from the current node to the goal for A* (h(n))
     */
    public int manhattan() {
        int elem;
        int sum=0;

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles.length; j++){
                elem = tiles[i][j];
                if (elem != 0) {
                    for (int y = 0; y < goal.length; y++) {
                        for (int x = 0; x < goal.length; x++) {
                            if (goal[y][x] == elem) {
                                sum += Math.abs(y - i) + Math.abs(x - j);
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return (manhattan()==0);
    }

    /*
     * Returns true if the board is solvable
     */
    public boolean solvable() {
        List<Integer> tilesList = new ArrayList();
        int inversions = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tilesList.add(tiles[i][j]);
            }
        }

        for (int i = 0; i < tilesList.size(); i++) {
            if (tilesList.get(i)==0){continue;}
            for (int j = i; j < tilesList.size(); j++) {
                if (tilesList.get(i) > tilesList.get(j)){
                    if (tilesList.get(j)==0){continue;}
                    inversions++;
                }
            }
        }

        if (inversions % 2 == 0) {
            return true;
        }
        return false;
    }

    /*
     * Return the neighboring boards in the state tree
     * One possible method: Create a duplicate board state, try moving the
     * blank space up, down, left, and right, and if the resulting board state
     * is valid, add it to an accumulator.
     */
    public Iterable<Board> neighbors() {
        ArrayList<Board> possibilities = new ArrayList<Board>();
        int x = 0;
        int y = 0;
        //find 0
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0){
                    x = i;
                    y = j;
                }
            }
        }
        // Check the 4 possible switches
        if (x > 0) {
            possibilities.add(new Board(switchTiles(x-1,y,x,y)));
        }
        if (x < tiles.length-1){
            possibilities.add(new Board(switchTiles(x+1,y,x,y)));
        }
        if (y > 0) {
            possibilities.add(new Board(switchTiles(x,y-1,x,y)));
        }
        if (y < tiles.length-1){
            possibilities.add(new Board(switchTiles(x,y+1,x,y)));
        }
        return possibilities;
    }

    public int[][] switchTiles(int targetX, int targetY, int zeroX, int zeroY){
        int[][] newTiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            newTiles[i] = tiles[i].clone();
        }
        newTiles[zeroX][zeroY] = tiles[targetX][targetY];
        newTiles[targetX][targetY] = tiles[zeroX][zeroY];
        return newTiles;
    }

    /*
     * Prints out the board state nicely for debugging purposes
     */
    public void printBoard() {
        for (int[] tile : tiles) {
            for (int aTile : tile) System.out.print(aTile + " ");
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        board.printBoard();
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for (Board b : it)
            b.printBoard();
    }
}