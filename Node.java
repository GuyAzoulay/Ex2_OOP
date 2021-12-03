package api;

import java.util.HashMap;

public class Node implements NodeData{
    int key;
    private int tag;
    GeoLocation g1;
    private String info;

    public Node(int key, GeoLocation g1) {
        this.key = key;
        this.g1 = g1;

    }

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
        return 0;
    }

    public void setWeight(double w) {
        return;
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
}
