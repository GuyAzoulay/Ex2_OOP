package api;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DirectedGAlgo implements DirectedWeightedGraphAlgorithms{
    DirectedG g1;

    public DirectedGAlgo(){
        this.g1= new DirectedG();
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        this.g1 = (DirectedG) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g1;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedG newG= this.g1;
        return newG;
    }

    @Override
    public boolean isConnected() {
        for (NodeData n:this.g1.vertix.values()) {
            DFS(n.getKey());
            for (NodeData n2:this.g1.vertix.values()) {
                if(n2.getTag()==0) return false;
            }
          SetTag0();
        }

        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (!isConnected()) return -1.0;
        HashSet<Integer> helper = new HashSet<>();
        PriorityQueue<NodeData> queue= new PriorityQueue<>();
        Dijkstra(queue,helper,src,dest);
        return g1.vertix.get(dest).getWeight();

    }
    private void Dijkstra(PriorityQueue<NodeData> queue, HashSet<Integer> helper,int src, int dest ){

        for (NodeData n:this.g1.vertix.values()) {
            n.setWeight(Double.MAX_VALUE);
        }
        g1.vertix.get(src).setWeight(0.0);
        queue.add(new Node(src,g1.vertix.get(src).getLocation()));

        while (helper.size() != g1.vertix.size()){
            if (queue.isEmpty()) return;
            int t = queue.poll().getKey();

            if (helper.contains(t)) continue;
            helper.add(t);
            All_Neighbers(t,queue,helper);
        }

    }

    private void All_Neighbers(int t,PriorityQueue<NodeData> queue, HashSet<Integer> helper) {
        double weight_val=-1;
        double new_weight=-1;
        for (EdgeData e : this.g1.edges.get(t).values()) {
            if (!helper.contains(e.getDest())){
                weight_val=e.getWeight();
                new_weight= weight_val + this.g1.vertix.get(t).getWeight();
                if (new_weight < this.g1.vertix.get(e.getDest()).getWeight()){
                    this.g1.vertix.get(e.getDest()).setWeight(new_weight);
                }
                queue.add(new Node(e.getDest(),g1.vertix.get(e.getDest()).getLocation()));

            }
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray nodesJson = fileObject.get("Nodes").getAsJsonArray();
            for (int i = 0; i < nodesJson.size(); i++) {
                JsonObject nodeObj = new Gson().fromJson(nodesJson.get(i) , JsonObject.class);
                String posStr = nodeObj.get("pos").getAsString();
                double[] pos = new double[3];
                String[] posArray = posStr.split(",");
                for (int j = 0; j < posArray.length; j++) {
                    pos[j] = Double.parseDouble(posArray[j]);
                }
                String idStr = nodeObj.get("id").getAsString();
                int id = Integer.parseInt(idStr);
                double x = pos[0];
                double y = pos[1];
                double z = pos[2];
                GeoLoc g = new GeoLoc(x,y,z);
                Node tmp = new Node(id,g);
                this.g1.addNode(tmp);
            }

            JsonArray edgesJson = fileObject.get("Edges").getAsJsonArray();
            for (int i = 0; i < edgesJson.size(); i++) {
                JsonObject edgeObj = new Gson().fromJson(edgesJson.get(i) , JsonObject.class);
                String srcStr = edgeObj.get("src").getAsString();
                String wStr = edgeObj.get("w").getAsString();
                String destStr = edgeObj.get("dest").getAsString();
                int src = Integer.parseInt(srcStr);
                double w = Double.parseDouble(wStr);
                int dest = Integer.parseInt(destStr);
                this.g1.connect(src , dest , w);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    } //v
    private void DFS(int vetrix){
        this.g1.getNode(vetrix).setTag(1);
        for (EdgeData e:this.g1.edges.get(vetrix).values()) {
            if(g1.vertix.get(e.getDest()).getTag()==0){
                DFS(e.getDest());
            }
        }
    }
    private void SetTag0(){
        for (NodeData n:g1.vertix.values()) {
            n.setTag(0);
        }
    }
}
