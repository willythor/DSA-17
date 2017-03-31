import java.util.*;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();
        queue.add(v);
        seen.add(v);
        int currentNode;

        while(queue.size()!=0){
            currentNode = queue.poll();
            for(int n: g.getNeighbors(currentNode)){
                if (n==u) {return true;}
                if (!seen.contains(n)) {
                    seen.add(n);
                    queue.add(n);
                }
            }
        }
        return false;
    }

    static void dfs(Digraph g, HashSet<Integer> seen, List<Integer> res, int n) {
        seen.add(n);
        for (int v : g.getNeighbors(n))
            if (!seen.contains(v))
                dfs(g, seen, res, v);
        res.add(0,n);
    }


    public static List<Integer> topologicalOrder(Digraph g) {
        HashSet<Integer> seen = new HashSet<>();
        List<Integer> res = new LinkedList<>();
        for (int n = 0; n < g.numVertices(); n++){
            if (!seen.contains(n)){
                dfs(g,seen,res,n);
            }
        }
        return res;

    }

    public static boolean hasCycle(UndirectedGraph g) {

        HashSet<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer,Integer> prev = new HashMap<>();
        int currentNode;

        for (int i = 0; i < g.numVertices(); i++) {
            currentNode = i;
            seen.clear();
            stack.add(currentNode);
            seen.add(currentNode);


            while (stack.size() != 0) {
                currentNode = stack.pop();
                for (int n : g.getNeighbors(currentNode)) {
                    if (seen.contains(n)) {
                        if (prev.get(currentNode)!=null) {
                            if (n == prev.get(currentNode)){
                                continue;
                            }
                            return true;
                        }
                    } else {
                        stack.add(n);
                        seen.add(n);
                    }
                    prev.put(n,currentNode);

                }
            }
        }
        return false;
    }
}