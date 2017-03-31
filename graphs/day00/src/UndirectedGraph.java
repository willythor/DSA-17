import java.util.*;

public class UndirectedGraph implements Graph {

    private static HashMap<Integer,HashSet<Integer>> verticesHash = new HashMap<>();
    private static int numEdges;

    public UndirectedGraph(int n) {
        numEdges=0;
        for (int i = 0; i<n; i++){
            verticesHash.put(i,new HashSet<Integer>());
        }
    }

    @Override
    public void addEdge(int v, int w) {
        if (verticesHash.get(v)!=null&&verticesHash.get(w)!=null){
            verticesHash.get(v).add(w);
            verticesHash.get(w).add(v);
            numEdges++;
        }
    }

    @Override
    public List<Integer> vertices() {
    	return new ArrayList<>(verticesHash.keySet());
    }

    @Override
    public int numVertices() {
        return verticesHash.keySet().size();
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
        Iterable<Integer> iterable = (verticesHash.get(v));
        return iterable;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	return (verticesHash.get(v).contains(w));
    }

//    public static void main(String[] args){
//        UndirectedGraph g1 = new UndirectedGraph(9);
//        g1.addEdge(0, 1);
//        g1.addEdge(0, 2);
//        g1.addEdge(1, 2);
//        g1.addEdge(1, 3);
//        g1.addEdge(2, 4);
//        g1.addEdge(3, 4);
//        g1.addEdge(4, 5);
//        g1.addEdge(5, 6);
//        g1.addEdge(3, 7);
//        g1.addEdge(6, 7);
//        System.out.println(g1.getNeighbors(0));
//
//    }

}
