import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        HashMap<Integer,Integer> distance = new HashMap<>();
        HashMap<Integer,Integer> prev = new HashMap<>();
        HashSet<Integer> seen = new HashSet<>();
        Queue<Integer> pq = new LinkedList<>();
        distance.put(v,0);
        pq.add(v);
        prev.put(v,null);
        seen.add(v);

        while (pq.size()!=0){
            int current_node = pq.poll();
            for (int n : g.getNeighbors(current_node)) {
                if (!seen.contains(n)){
                    seen.add(n);
                    prev.put(n,current_node);
                    pq.add(n);
                    int dist = distance.get(prev.get(n))+1;
                    if (distance.get(n)!=null){
                        if(distance.get(n)>dist){
                            distance.put(n,dist);
                        }
                    }
                    else {
                        distance.put(n,dist);
                    }
                }
            }

        }

        LinkedList<Integer> orderedList = new LinkedList<>();
        if (prev.get(w)==null){
            return null;
        }
        for (int i = w; i!=v; i = prev.get(i)){
            orderedList.add(i);
        }
        orderedList.add(v);
        Collections.reverse(orderedList);
        return orderedList;
    }

    public static int distanceBetween(Graph g, int v, int w) {
        if (shortestPath(g,v,w) == null){
            return -1;
        }
        else {return shortestPath(g,v,w).size()-1;}
    }



    public static void main(String[] args){
        UndirectedGraph g1 = new UndirectedGraph(9);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        g1.addEdge(3, 4);
        g1.addEdge(4, 5);
        g1.addEdge(5, 6);
        g1.addEdge(3, 7);
        g1.addEdge(6, 7);


        System.out.println(shortestPath(g1,0,6));
        System.out.println(shortestPath(g1,0,7));
        System.out.println(shortestPath(g1,3,2));

        System.out.println(distanceBetween(g1,0,6));
        System.out.println(distanceBetween(g1,0,4));
        System.out.println(distanceBetween(g1,0,3));
        System.out.println(distanceBetween(g1,0,8));

    }
}