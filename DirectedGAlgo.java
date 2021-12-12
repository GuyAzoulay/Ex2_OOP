package api;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedGAlgo implements DirectedWeightedGraphAlgorithms{
    DirectedG g1;
    private List<NodeData> shortestPath; // a variable which will help us in the shortestpath function

    // Constructor for a new graph algo
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
    // in aim to check if graph is connected we ran on all the nodes, and using the DFS method
    // and "coloring" these nodes, if one of the nodes after the DFS running is still "0"
    // than the graph isn't connected
    @Override
    public boolean isConnected() {
        for (NodeData n:this.g1.vertix.values()) {
            DFS(n.getKey());
            for (NodeData n2:this.g1.vertix.values()) {
                if(n2.getTag()==0) return false;
            }
        }
        SetTag0();

        return true;
    }


    //this method helps us to create the list that the shortestpath go through, we do it until the
    // dest != src
    private void creat_list(int src , int dest){
        Stack<NodeData> s = new Stack<>();
        int curr = Integer.MAX_VALUE;
        this.shortestPath = new LinkedList<>();
        this.shortestPath.add(new Node(this.g1.vertix.get(dest).getKey(),this.g1.vertix.get(dest).getLocation()));
        while(dest!=src){
            NodeData temp = this.g1.vertix.get(dest);
            dest = temp.get_Prev();
            this.shortestPath.add(new Node(this.g1.vertix.get(dest).getKey(),this.g1.vertix.get(dest).getLocation()));
        }
        while(!this.shortestPath.isEmpty()){
            s.add(this.shortestPath.remove(0));
        }
        while (!s.isEmpty()){
            this.shortestPath.add(s.pop());
        }
    }
    //this function calculate the shortest dist after we activate some function on it.
    private double shortestDist(List<NodeData> list){
        double ans = 0;
        for (int i = list.size()-1; i > 0 ; i--) {
            int src= list.get(i).getKey();
            int dest = list.get(i-1).getKey();
            double temp = this.g1.edges.get(src).get(dest).getWeight();
            ans+=temp;
        }
        return ans;
    }


    // this is the main function for the shortestpathDist computing
    @Override
    public double shortestPathDist(int src, int dest) {
        //   if (!isConnected()) return -1.0;
        HashSet<EdgeData> helper = new HashSet<>();
        Queue <EdgeData> queue= new LinkedList<>() ;
        Dijkstra(queue,helper,src,dest);
        double ans = g1.vertix.get(dest).getWeight();
        creat_list(src,dest);
        SetTag0();
        return shortestDist(this.shortestPath);

    }
    // this is the known Dijkstra algo, in this algo, we ran on all the nodes
    //and change their weight to infinity and their color to white.
    // Then we start to run on all the edges in the graph and start looking
    // the shortest path between 2 nodes, by that we're always updating the node weight,
    // and at the end the dest node's weight will be with the shortest path distance.
    private void Dijkstra(Queue<EdgeData> queue, HashSet<EdgeData> helper,int src, int dest ){

        for (NodeData n:this.g1.vertix.values()) { //updating the nodes weight to infinity
            n.setWeight(Double.MAX_VALUE);
            n.setInfo("white");
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
    //this function help to the Dijkstra algo to ran and compare between all the
    // possible path, and always updating the node's weight.
    // In addition, in this function we are "coloring" the nodes and the edges that
    // we won't run on an edge twice and on a vertix more than twice.
    private void All_Neighbers(EdgeData t,Queue<EdgeData> queue, HashSet<EdgeData> helper,int dest) {
        double weight_val=-1;
        double new_weight=-1;
        EdgeData curr ;
        for (EdgeData e : queue) {
            e.setTag(1);
            if (!helper.contains(e) && e.getDest() != dest){
                weight_val=e.getWeight();
                new_weight= weight_val + this.g1.vertix.get(e.getSrc()).getWeight(); //check
                if (new_weight < this.g1.vertix.get(e.getDest()).getWeight()){//weight from src to current dest

                    this.g1.vertix.get(e.getDest()).setWeight(new_weight); //updating the node weight
                    this.g1.vertix.get(e.getDest()).set_Prev(e.getSrc());
                    e.setTag(1);
                }
            }
            if (e.getDest()==dest){
                weight_val=e.getWeight();
                new_weight = weight_val + this.g1.vertix.get(e.getSrc()).getWeight();
                curr = t;
                if (new_weight < this.g1.vertix.get(e.getDest()).getWeight()){

                    this.g1.vertix.get(e.getDest()).setWeight(new_weight);
                    this.g1.vertix.get(e.getDest()).set_Prev(e.getSrc());
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

    //this function shows us what is the shortest path we ran in a list of nodes
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(this.shortestPath!=null){
            this.shortestPath = new LinkedList<>();
        }
        shortestPathDist(src,dest);
        return this.shortestPath;
    }

    // this function finds the center of a graph, in aim to find the center
    // of a graph, we are computing what is the max distance between node to all the
    // other nodes, and then we are taking the min between all the max distances.
    @Override
    public NodeData center() {
        if (!isConnected()) return null;
        TreeMap<Double, Integer> ans = new TreeMap<Double, Integer>();
        for (NodeData n1 : this.g1.vertix.values()) {
            TreeMap<Double, Integer> tree_map = new TreeMap<Double, Integer>();
            for (NodeData n2 : this.g1.vertix.values()) {
                if(n1!=n2) {
                    double w = shortestPathDist(n1.getKey(), n2.getKey());
                    tree_map.put(w, n1.getKey());
                    System.out.println(n1.getKey()+" "+n2.getKey()+": "+w);
                }
            }
            ans.put(tree_map.lastEntry().getKey(), n1.getKey());
        }
        NodeData n= new Node(ans.firstEntry().getValue(),this.g1.vertix.get(ans.firstEntry().getValue()).getLocation());
        return n;
    }


    // this is a known algo which called TSP (travel SalesMan Problem)
    // here we are going through a list of "cities" and need to find the shortest
    // one that will "visited" in all this cities.
    // first, we run on the graph to see if all the ids are in the cities list.
    // and then we add all the possible paths into list of list.
    // in the end we ran on all the possible paths and check which is the shortest between
    // them.

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<List<NodeData>> Paths = new LinkedList<>();
        List<NodeData> result = new LinkedList<>();

        for (NodeData city : cities){
            if (!g1.getNodes().containsKey(city.getKey())) return null;
        }

        for (NodeData city : cities){
            for (NodeData cityDest : cities){
                if (city.getKey() == cityDest.getKey()) continue;
                Paths.add(shortestPath(city.getKey(),cityDest.getKey()));
            }
        }

        for (List<NodeData> notContainsAll : Paths){
            for (List<NodeData> notContains2 : Paths){
                if (!notContainsAll.containsAll(cities) && !notContains2.containsAll(cities)){
                    if (notContains2.get(0).getKey() == notContainsAll.get(notContainsAll.size()-1).getKey() && notContainsAll.get(0).getKey() != notContains2.get(notContains2.size()-1).getKey()){
                        for (NodeData node : notContains2){
                            if (node.getKey()!= notContains2.get(0).getKey()) {
                                notContainsAll.add(node);
                            }
                        }
                    }
                }
            }
        }

        double compare = Double.MAX_VALUE;
        for (List<NodeData> listCheck : Paths) {
            for (NodeData node : cities){
                if (!listCheck.contains(node)) break;
            }
                if (pathWeight(listCheck) < compare) {
                    result = listCheck;
                    compare = pathWeight(listCheck);
                }

        }


        return result;
    }

    // a function which help to calculate the path weight
    private double pathWeight(List<NodeData> nodes){
        double result = 0;
        Iterator<NodeData> src = nodes.iterator();
        Iterator<NodeData> dst = nodes.iterator();
        dst.next();
        while (dst.hasNext()){
            NodeData source = src.next();
            NodeData dest = dst.next();
            result += g1.getEdges().get(source.getKey()).get(dest.getKey()).getWeight();
        }
        return result;
    }


    // a method which save to a json file
    @Override
    public boolean save(String file) {
        //This json object will be written to json file
        JsonObject graph = new JsonObject();
        String directory = "src/data/";
        JsonArray nodeJ = new JsonArray();
        JsonArray edgeJ = new JsonArray();
        JsonObject node;
        JsonObject edge;
        Iterator<NodeData> iter = this.g1.nodeIter();
        int i = 0;
        while (iter.hasNext()) {
            NodeData temp = iter.next();
            node = new JsonObject();
            node.addProperty("pos", temp.getLocation().x() + "," + temp.getLocation().y() + "," + temp.getLocation().z());
            node.addProperty("id", i);
            nodeJ.add(node);
            Iterator<EdgeData> edgeIter = this.g1.edgeIter(i);
            while (edgeIter != null && edgeIter.hasNext()) {
                EdgeData edgeData = edgeIter.next();
                edge = new JsonObject();
                edge.addProperty("src", edgeData.getSrc());
                edge.addProperty("dest", edgeData.getDest());
                edge.addProperty("w", edgeData.getWeight());
                edgeJ.add(edge);
            }
            i++;
        }

        graph.add("Edges", edgeJ);
        graph.add("Nodes", nodeJ);

        // Writing to the json file
        try (FileWriter fileWriter = new FileWriter(directory + file)) {
            fileWriter.write(String.valueOf(graph));
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //this function loads a json file into our project.
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
            System.err.println("File doesn't exist");
        }
        return false;
    }

    //the DFS function ran on all the nodes and "coloring" them.
    private void DFS(int vetrix){
        this.g1.getNode(vetrix).setTag(1);
        for (EdgeData e:this.g1.edges.get(vetrix).values()) {
            if(g1.vertix.get(e.getDest()).getTag()==0){
                DFS(e.getDest());
            }
        }
    }

    // in the end of every function we change the tag and info, we
    // need to return it to the regular value.
    private void SetTag0(){
        for (NodeData n:g1.vertix.values()) {
            n.setInfo("white");
            n.setTag(0);
        }
        for (HashMap<Integer, EdgeData> id: g1.edges.values()) {
            for (EdgeData e : id.values()) {
                e.setTag(0);
            }
        }
    }

}