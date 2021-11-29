package api;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedG implements DirectedWeightedGraph{
    private int MC= 0;
    HashMap<Integer,NodeData> vertix;
    HashMap<NodeData,HashMap<Integer,EdgeData>> edges;
    private int edgescount = 0;

    public DirectedG(HashMap<Integer, NodeData> vertix, HashMap<NodeData, HashMap<Integer, EdgeData>> edges) {

        this.vertix = new HashMap<Integer, NodeData>();
        this.edges = new HashMap<NodeData,HashMap<Integer, EdgeData>>();

    }

    public NodeData getNode(int key) {
        return this.vertix.get(key);
    }

    public EdgeData getEdge(int src, int dest) {
        if (edges.get(src).get(dest) != null) {
            return this.edges.get(src).get(dest);
        }
        return null;
    }

    public void addNode(NodeData n) {
        if(vertix.containsKey(n.getKey())) return;
        this.vertix.put(n.getKey(),n);
        this.edges.put(n,new HashMap<Integer,EdgeData>());


    }

    public void connect(int src, int dest, double w) {
        Edges e1 = new Edges(src, dest, 0, w);
        if(edges.get(src).get(dest)==null && vertix.get(src) != null && vertix.get(dest) != null) {
            edges.get(src).put(dest , e1);
            edgescount++;
        }
    }

    public Iterator<NodeData> nodeIter() {
        return this.vertix.values().iterator();
    }

    public Iterator<EdgeData> edgeIter() {
        return edgeIter(3);
    }

    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.edges.get(node_id).values().iterator();
    }

    public NodeData removeNode(int key) {
        return null;
    }

    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    public int nodeSize() {
        return vertix.size();
    }

    public int edgeSize() {
        return 0;
    }

    public int getMC() {
        return MC;
    }
}
