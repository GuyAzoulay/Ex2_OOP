package api;

import javax.swing.text.html.HTMLDocument;
import java.util.*;


// in this class we implement what we have written so far (Nodes, GeoLocation, Edges)
// into a graph, in here we choose that the best data structure to use in is HashMap

public class DirectedG implements DirectedWeightedGraph {
    private int MC = 0;
    HashMap<Integer, NodeData> vertix; //represent the nodes
    HashMap<Integer, HashMap<Integer, EdgeData>> edges; // First Integer is src second Integer is dest, represent the edges
    HashMap<Integer, HashSet<Integer>> intoNode; // represent the edges id which came in to specific node

    private int edgescount = 0; // variable which help us to save the number of edges

    // constructor to Directed Weighted Graph
    public DirectedG() {
        this.vertix = new HashMap<Integer, NodeData>();
        this.edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.intoNode = new HashMap<Integer, HashSet<Integer>>();

    }


    public NodeData getNode(int key) {
        return this.vertix.get(key);

    }

    public EdgeData getEdge(int src, int dest) {
        if (getNode(src) != null && getNode(dest) != null) {
            if (this.edges.get(src).get(dest) != null) {
                return this.edges.get(src).get(dest);
            }
            return null;
        }
        return null;
    }

    //adding a new node according to his keys
    public void addNode(NodeData n) {
        if (this.vertix.containsKey(n.getKey())) return;
        this.vertix.put(n.getKey(), n);
        this.edges.put(n.getKey(), new HashMap<Integer, EdgeData>());
        this.intoNode.put(n.getKey(), new HashSet<Integer>());
        MC++;
    }

    //connect between 2 id (keys) of nodes
    // first, we check if the keys (id) are in the nodes list
    // if so we create new edge and add it into the edges HashMap
    // and into the intoNode HashMap
    public void connect(int src, int dest, double w) {
        if (this.vertix.containsKey(src) && this.vertix.containsKey(dest)) {
            Edges e = new Edges(src, dest, w);
            this.edges.get(src).put(dest, e);
            this.intoNode.get(dest).add(src);
            MC++;
            edgescount++;
        } else {
            System.out.println("Invalid Values");
        }
    }


    public Iterator<NodeData> nodeIter() {
        return this.vertix.values().iterator();
    }

    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edges.get(node_id).values().iterator();
    }


    // when we are removing node we need to remove all the edges of this nodes
    // we ran on all the node's edges and delete them.
    public NodeData removeNode(int key) {
        if (this.vertix.containsKey(key)) {
            NodeData n = this.vertix.get(key);
            int edgeToDel = this.edges.get(key).size() + this.intoNode.get(key).size();   //number of all the edges which connect to nose
            this.vertix.remove(key);
            this.edgescount -= edgeToDel;
            for (Integer e : intoNode.get(key)) {
                if (this.edges.get(e) != null) this.edges.get(e).remove(key);
                removeEdge(e, key);
            }
            this.edges.remove(key);
            this.intoNode.get(key).clear();
            this.intoNode.remove(key);
            MC++;
            return n;
        } else {
            System.out.println("No such key: " + key);
        }
        return null;
    }
    // removing edge from our graph
    public EdgeData removeEdge(int src, int dest) {
        if (getEdge(src, dest) != null) {

            this.intoNode.get(dest).remove(src); // self check if the edge has been deleted

            EdgeData e = this.edges.get(src).remove(dest);
            System.out.println(e);
            this.edgescount--;
            MC++;
            return e;
        }
        return null;
    }

    public int nodeSize() {
        return vertix.size();
    }

    public int edgeSize() {
        return this.edgescount;
    }

    public int getMC() {
        return MC;
    }
    public boolean contain(NodeData n){
        boolean flag= false;
        if (n!=null){
            flag=this.vertix.containsKey(n.getKey());
        }
        return flag;
    }
    //function which help us in the algo part.
    public boolean contain(EdgeData e){
        boolean flag= false;
        if(e != null){
            flag= this.edges.get(e.getSrc()).containsKey(e.getDest());
        }
        return flag;
    }
    public HashMap<Integer,NodeData> getNodes(){
        return this.vertix;
    }
    public HashMap<Integer,HashMap<Integer,EdgeData>> getEdges(){
        return this.edges;
    }

}