package api;

public class Edges implements EdgeData{
    int src,dest,tag;
    double weight;
    private String info;

    public Edges(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;

    }

    public int getSrc() {
        return this.src;
    }

    public int getDest() {
        return this.dest;
    }

    public double getWeight() {
        return this.weight;
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
        return "Edges{" +
                "src=" + this.src +
                ", dest=" + this.dest +
                ", weight=" + this.weight +
                 '\'' +
                '}';
    }
}
