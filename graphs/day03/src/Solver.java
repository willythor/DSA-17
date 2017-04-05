/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    private boolean solvable = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves;
        public int cost;
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            cost = moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        /*
         * Return the cost difference between two states
         */
        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
    	while (state.prev != null){
    	    state = state.prev;
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        solvable = initial.solvable();
        State initialState = new State(initial, 0, null);
        PriorityQueue<State> open = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        HashSet<State> closed = new HashSet<>();

        open.add(initialState);

        while (!open.isEmpty() && !solved && solvable){
            State q = open.poll();

            for (Board u : q.board.neighbors()) {
                if (u.isGoal()) {
                    System.out.println("Goal");
                    solved = true;
                    solutionState = new State(u, q.moves + 1, q);
                    minMoves = q.moves +1;
                }

                int uCost = q.moves + 1 + u.manhattan();

                boolean ignore = false;

                State s = find(open, u);
                if (s != null && s.cost < uCost){
                    ignore =true;
                }

                s = find(closed, u);
                if (!ignore && s != null && s.cost < uCost){
                    ignore =true;
                }

                if (!ignore) {
                    open.offer(new State(u, q.moves + 1, q));
                }
            }

            closed.add(q);
        }
    }

    /*
     * Is the input board a solvable state
     */
    public boolean isSolvable() {
        return solvable;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
    	List<Board> solutionTree = new LinkedList<>();
    	State base = solutionState;
    	minMoves = 0;
    	while (base.prev != null){
            System.out.println(minMoves);
            solutionTree.add(0,base.board);
    	    base = base.prev;
    	    minMoves++;
        }
        return solutionTree;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space: Your solution can have whatever output you find useful
     * Optional challenge: create a command line interaction for users to input game
     * states
     */
    public static void main(String[] args) {
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
        Board initial = new Board(initState);

        // Solve the puzzle
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
            System.out.println("No solution");
        else {
            for (Board board : solver.solution()) {
                board.printBoard();
            }
            System.out.println(solver.minMoves);
        }
    }


}
