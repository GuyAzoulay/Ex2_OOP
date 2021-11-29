package api;

import java.util.HashMap;

public class check {
    public static void main(String[] args) {
        HashMap<Integer , NodeData> nodes = new HashMap<>();
        HashMap<NodeData , HashMap<Integer , EdgeData>> edges = new HashMap<>();
        DirectedG g = new DirectedG(nodes,edges);
        Node n1 = new Node(0,0,new GeoLoc(1,0,0));
        Node n2 = new Node(1,0,new GeoLoc(2,1,0));
        Node n3 = new Node(2,0,new GeoLoc(3,5,0));
        Node n4 = new Node(3,0,new GeoLoc(5,4,0));
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        System.out.println(g.nodeSize()); // should print 4
        g.connect(0,1,3);
        g.connect(0,3,7);
        g.connect(0,2,7);
        System.out.println(edges.get(0).size()); //should print 3
//        g.removeEdge(0,3);
//        System.out.println(edges.get(0).size()); // should print 2
//        g.removeNode(0);
//        System.out.println(g.edgeSize()); // should print 0
//        System.out.println(g.nodeSize()); // should print 3

    }
}
