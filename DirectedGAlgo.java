package api;

import com.google.gson.*;
import org.json.*;
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

    private void creat_list(int src , int dest){
        int curr = Integer.MAX_VALUE;
        this.shortestPath = new LinkedList<>();
        this.shortestPath.add(new Node(this.g1.vertix.get(dest).getKey(),this.g1.vertix.get(dest).getLocation()));
        while(dest!=src){
       //     System.out.println(dest);
            NodeData temp = this.g1.vertix.get(dest);
            dest = temp.get_Prev();
            this.shortestPath.add(new Node(this.g1.vertix.get(dest).getKey(),this.g1.vertix.get(dest).getLocation()));
        }
    }
    private double helpme(List<NodeData> list){
        double ans = 0;
        for (int i = list.size()-1; i > 0 ; i--) {
            int src= list.get(i).getKey();
            int dest = list.get(i-1).getKey();
            double temp = this.g1.edges.get(src).get(dest).getWeight();
            ans+=temp;
        }
        return ans;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
     //   if (!isConnected()) return -1.0;
        HashSet<Integer> helper = new HashSet<>();
        PriorityQueue<NodeData> queue= new PriorityQueue<>();
        Dijkstra(queue,helper,src,dest);
        double ans = g1.vertix.get(dest).getWeight();
        creat_list(src,dest);
        SetTag0();
        return helpme(this.shortestPath);

    }
    private void Dijkstra(PriorityQueue<NodeData> queue, HashSet<Integer> helper,int src, int dest ){

        for (NodeData n:this.g1.vertix.values()) {
            n.setWeight(Double.MAX_VALUE);
        }
        g1.vertix.get(src).setWeight(0.0);
        int curr_src=src;
        int size = 0;
        for (HashMap<Integer, EdgeData> id: g1.edges.values()) {
            size += id.size();
        }

        while (helper.size() != size){
            if (curr_src != dest);{
                g1.vertix.get(curr_src).setInfo("grey");}
            for (EdgeData e:this.g1.edges.get(curr_src).values()) { //here vertix become gray

                    if (e.getTag() !=1 && this.g1.vertix.get(e.getDest()).getInfo().equals("white")) {
                        queue.add(e);
                    }
                    else if(this.g1.vertix.get(e.getDest()).getInfo().equals("grey")) {
                        e.setTag(1);
                        helper.add(e);
                    }
                }//creating a queue with all the edges from src vertix
            //updating the weight of the poll one.
            if (queue.isEmpty()) return;
            EdgeData t = queue.poll();
            double  weight_val=t.getWeight();
            double new_weight= weight_val + this.g1.vertix.get(t.getSrc()).getWeight(); //check
            if (new_weight < this.g1.vertix.get(t.getDest()).getWeight()){//weight from src to current dest
             //   System.out.println("this is t:"+t.getSrc()+"  " +t.getDest()+": "+weight_val+" + " + this.g1.vertix.get(t.getSrc()).getWeight());
                this.g1.vertix.get(t.getDest()).setWeight(new_weight); //updating the node weight
                this.g1.vertix.get(t.getDest()).set_Prev(t.getSrc());
            }
            t.setTag(1);
            if (helper.contains(t)) continue;
            helper.add(t);
            All_Neighbers(t,queue,helper,dest);
            curr_src=t.getDest();
        }

    }

    private void All_Neighbers(EdgeData t,Queue<EdgeData> queue, HashSet<EdgeData> helper,int dest) {
        double weight_val=-1;
        double new_weight=-1;
        EdgeData curr ;
        for (EdgeData e : queue) {
            e.setTag(1);
            if (!helper.contains(e) && e.getDest() != dest){
                weight_val=e.getWeight();
                new_weight= weight_val + this.g1.vertix.get(t).getWeight();
                curr = t;
                if (new_weight < this.g1.vertix.get(e.getDest()).getWeight()){
                    this.g1.vertix.get(e.getDest()).setWeight(new_weight);
                    this.g1.vertix.get(e.getDest()).set_Prev(curr);
                }
            }
            if (e.getDest()==dest){
                weight_val=e.getWeight();
                new_weight = weight_val + this.g1.vertix.get(t.getSrc()).getWeight();
                curr = t;
                if (new_weight < this.g1.vertix.get(e.getDest()).getWeight()){

                    this.g1.vertix.get(e.getDest()).setWeight(new_weight);
                    this.g1.vertix.get(e.getDest()).set_Prev(curr.getSrc());
                    e.setTag(1);
                }
            }
            boolean b= true;
            for (Integer id :this.g1.intoNode.get(e.getSrc())) {
                if (this.g1.edges.get(id).get(e.getSrc()).getTag() != 1)
                    b=false;
            }
            if(b)
                this.g1.vertix.get(e.getSrc()).setInfo("black");
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(this.shortestPath!=null){
            this.shortestPath = new LinkedList<>();
        }
        shortestPathDist(src,dest);
        return this.shortestPath;
    }

    @Override
    public NodeData center() {
        return null;
    }

    private void swap(List<NodeData> input, int a, int b) {
        NodeData tmp = new Node( input.get(a).getKey(),input.get(a).getLocation());
        input.set(a, input.get(b));
        input.set(b, tmp);
    }
    public double getNextRoute(List<NodeData> elements){
        double curr_sum = 0;
        boolean hasNext = true;
        while(hasNext) {
            // System.out.println(elements);
            for (int i = 0; i <elements.size()-1; i++) {
                curr_sum += shortestPathDist(elements.get(i).getKey(),elements.get(i+1).getKey());
            }
            curr_sum += shortestPathDist(elements.get(elements.size()-1).getKey(),elements.get(0).getKey());
            if (curr_sum<this.tsp) {
                this.tsp = curr_sum;
                System.out.println(curr_sum);
            }
            int k = 0, l = 0;
            hasNext = false;
            for (int i = elements.size() - 1; i > 0; i--) {
                if (elements.get(i).getKey() >(elements.get(i - 1).getKey())) {
                    k = i - 1;
                    hasNext = true;
                    break;
                }
            }

            for (int i = elements.size() - 1; i > k; i--) {
                if (elements.get(i).getKey()>elements.get(k).getKey()) {
                    l = i;
                    break;
                }
            }

            swap(elements, k, l);
            Collections.reverse(elements.subList(k + 1, elements.size()));
        }
        return this.tsp;
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {







        return null;
    }

    @Override
    public boolean save(String file) {
        JSONObject json = new JSONObject(); //creates main json
        try {
            JSONObject valuesJson = new JSONObject(); //another object
            valuesJson.put("Car", "Maruti");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;}
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
            n.setInfo("white");
            n.setTag(0);
        }
    }
}