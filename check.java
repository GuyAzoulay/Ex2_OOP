package api;

import java.awt.*;
import java.lang.invoke.CallSite;
import java.util.*;
import java.util.List;

public class check {
    public static void main(String[] args) {
        HashMap<Integer,NodeData> vertix = new HashMap<>();
        HashMap<Integer, HashMap<Integer,EdgeData>> edges = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> intome= new HashMap<>();
        DirectedG g = new DirectedG();
        DirectedGAlgo algo= new DirectedGAlgo();

        algo.load("G1.json");
        System.out.println(algo.shortestPathDist(16,4));
      //  System.out.println(algo.shortestPath(0,11));
//        for (int i = 0; i < algo.g1.vertix.size(); i++) {
//            for (EdgeData e : algo.g1.edges.get(i).values()) {
//                System.out.println(e +"   "+ e.getTag());
//            }
//        }
//        System.out.println(algo.g1.nodeSize());
//        System.out.println(algo.getGraph().edgeSize());
//        System.out.println(algo.getGraph().getEdge(15,16));
//        System.out.println(algo.shortestPathDist(2,12));
//        System.out.println(algo.shortestPath(2,12));
       /* double[][] shortest_path = new double[algo.g1.vertix.size()][algo.g1.vertix.size()];
        for (int i = 0; i < shortest_path.length; i++) {
            for (int j = 0; j < shortest_path.length; j++) {
                if(i!=j)
                    shortest_path[i][j] = algo.shortestPathDist(i,j);
            }
        }*/
//        List<NodeData> l =new LinkedList<>() ;
//        int size  =algo.g1.vertix.size();
//        for (int i = 0; i <size ; i++) {
//            l.add(algo.g1.getNode(i));
//        }
//        //System.out.println(algo.getNextRoute(l));
//       // double i = test.minCost(shortest_path);
//        algo.save("");
//        System.out.println(algo.g1.vertix.get(0).getLocation());
//        TreeMap<Double, Integer> tree_map = new TreeMap<Double, Integer>();
//        tree_map.put(0.5,0);
//        tree_map.put(0.1,1);
//        tree_map.put(8.9,4);
//        tree_map.put(7.1,2);
//        tree_map.put(6.1,3);
//        System.out.println(tree_map.lastEntry());
//        TreeMap<Double, Integer> ans = new TreeMap<Double, Integer>();
//        ans.put(0.1,1);
//        ans.put(tree_map.lastEntry().getKey(),tree_map.lastEntry().getValue());
//        System.out.println(ans.firstEntry());
//        ans.put(0.5,1);

//       // algo.getGraph().removeNode(6);
//        algo.getGraph().removeNode(7);
//        System.out.println(algo.getGraph().edgeSize());
//
//      System.out.println(algo.isConnected());











//        Node n1 = new Node(0,new GeoLoc(1.0,0.0,0.0));
//        Node n2 = new Node(1, new GeoLoc(2,1,0.0));
//        Node n3 = new Node(2, new GeoLoc(3,5,0));
//        Node n4 = new Node(3, new GeoLoc(5,4,0));
//        g.addNode(n1);
//        g.addNode(n2);
//        g.addNode(n3);
//        g.addNode(n4);
//        System.out.println(g.nodeSize()); // should print 4
//        g.connect(0,1,3);
//        g.connect(0,3,7);
//        g.connect(0,2,7);
//        g.connect(1,2,4);
//        System.out.println(g.edgeSize()); //should print 3
////        g.removeEdge(0,3);
////        System.out.println(edges.get(0).size()); // should print 2
//        System.out.println("*******************");
//       // g.removeNode(0);
//        g.removeNode(3);
//        g.removeNode(10);
//        g.removeEdge(1,2);
//        System.out.println(g.nodeSize());
//        System.out.println(g.edgeSize());
//        System.out.println(g.getMC());
//

//            System.out.println(g.nodeSize()); // should print 0
//        System.out.println(g.edgeSize() ); // should print 3

    }
}
