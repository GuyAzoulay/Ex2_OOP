package api;

import java.util.HashMap;

public class Node implements NodeData, Comparable<Node>{
    int key;
    private int tag;
    GeoLocation g1;
    private String info;
    private double weight;
    private int prev_Node;
    // We add this to method which will help us later in the assignment.
    public int get_Prev(){
        return this.prev_Node;
    }
    public void set_Prev(int t){
        this.prev_Node=t;
    }


    // A node constructor
    public Node(int key, GeoLocation g1) {
        this.key = key;
        this.g1 = g1;
    }


    // getters and setters to all the Nodes variables including to String method.
    public int getKey() {
        return this.key;
    }

    public GeoLocation getLocation() {
        return g1;
    }

    public void setLocation(GeoLocation p) {
        this.g1=p;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double w) {this.weight=w;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String s) {
        this.info=s;
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int t) {
        this.tag=t;

    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", g1=" + g1 +
                '\'' +
                '}';
    }


    //In aim to compare between 2 nodes, we need this class to be comparable
    @Override
    public int compareTo(Node node) {

        if (this.getWeight() < node.getWeight()) return 1;

        if (this.getWeight() > node.getWeight()) return 0;

        return -1;

    }
}
