package api;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class check {
    public static void main(String[] args) {
        HashMap<Integer,NodeData> vertix = new HashMap<>();
        HashMap<Integer, HashMap<Integer,EdgeData>> edges = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> intome= new HashMap<>();
        DirectedG g = new DirectedG(vertix,edges,intome);
        Node n1 = new Node(0,new GeoLoc(1.0,0.0,0.0));
        Node n2 = new Node(1, new GeoLoc(2,1,0.0));
        Node n3 = new Node(2, new GeoLoc(3,5,0));
        Node n4 = new Node(3, new GeoLoc(5,4,0));
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        System.out.println(g.nodeSize()); // should print 4
        g.connect(0,1,3);
        g.connect(0,3,7);
        g.connect(0,2,7);
        g.connect(1,2,4);
        System.out.println(g.edgeSize()); //should print 3
//        g.removeEdge(0,3);
//        System.out.println(edges.get(0).size()); // should print 2
        System.out.println("*******************");
       // g.removeNode(0);
        g.removeNode(3);
        g.removeNode(10);
        g.removeEdge(1,2);
        System.out.println(g.nodeSize());
        System.out.println(g.edgeSize());
        System.out.println(g.getMC());


//            System.out.println(g.nodeSize()); // should print 0
//        System.out.println(g.edgeSize() ); // should print 3

    }
}
