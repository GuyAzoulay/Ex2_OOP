package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.util.*;

public class GUI extends JPanel implements ActionListener {
    private JFrame this_frame = new JFrame("My Graph");
    public DirectedGAlgo graph = new DirectedGAlgo();
    private LinkedList<Double> x_values;
    private LinkedList<Double> y_values;
    private LinkedList<Integer> scale_x;
    private LinkedList<Integer> scale_y;
    private boolean mouseActive;
    private MouseListener ms = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(mouseActive) {
                int x = e.getX();
                int y = e.getY();
                scale_x.add(x);
                scale_y.add(y);
                double unscale_x = unScale(x, x_values, 10, 1000);
                double unscale_y = unScale(y, y_values, 10, 650);
                x_values.add(unscale_x);
                y_values.add(unscale_y);
                GeoLocation loc = new GeoLoc(x_values.getLast(),y_values.getLast(),0.0);
                int id = 0;
                for (int i = 0; i <graph.g1.vertix.size()+1 ; i++) {
                    if(!graph.g1.vertix.containsKey(i)) {
                        id = i;
                        break;
                    }
                }
                NodeData n = new Node(id,loc);
                graph.g1.addNode(n);
                graph.g1.scaled_x.put(id,x);
                graph.g1.scaled_y.put(id,y);
                this_frame.getContentPane().removeAll();
                this_frame.repaint();
                this_frame.setVisible(true);
                showGraph();
                mouseActive = false;
            }
        }
    };

    GUI(DirectedGAlgo g) {
        graph = g;
        JFrame frame = new JFrame("Graph Interface");
        frame.setSize(250, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu edit = new Menu("Edit");
        Menu algo = new Menu("Algorithms");
        menuBar.add(menu);
        menuBar.add(edit);
        menuBar.add(algo);
        frame.setMenuBar(menuBar);

        MenuItem load = new MenuItem("Load Graph");
        load.addActionListener(this);
        MenuItem save_graph = new MenuItem("Save Graph");
        save_graph.addActionListener(this);
        MenuItem clean = new MenuItem("Clean Graph");
        clean.addActionListener(this);

        menu.add(load);
        menu.add(save_graph);
        menu.add(clean);

        MenuItem isConnected = new MenuItem("Connected or not?");
        isConnected.addActionListener(this);
        MenuItem shortestDist = new MenuItem("Shortest Path Distance");
        shortestDist.addActionListener(this);
        MenuItem shortest_path = new MenuItem("Shortest Path");
        shortest_path.addActionListener(this);
        MenuItem center = new MenuItem("Center");
        center.addActionListener(this);

        algo.add(isConnected);
        algo.add(shortestDist);
        algo.add(shortest_path);
        algo.add(center);

        MenuItem add_vertex = new MenuItem("Add Vertex");
        add_vertex.addActionListener(this);
        MenuItem connect = new MenuItem("Connect Vertexes");
        connect.addActionListener(this);
        MenuItem remove_edge = new MenuItem("Remove Edge");
        remove_edge.addActionListener(this);
        MenuItem remove_vertex = new MenuItem("Remove Vertex");
        remove_vertex.addActionListener(this);

        edit.add(add_vertex);
        edit.add(connect);
        edit.add(remove_edge);
        edit.add(remove_vertex);


        Container contPane = frame.getContentPane();
        contPane.setLayout(null);

        JButton button = new JButton("Show Graph");
        button.setLocation(0, 0);
        button.setSize(150, 100);

        contPane.add(button);

        button.addActionListener(this);
        frame.setVisible(true);
        this_frame.addMouseListener(this.ms);
        int size = graph.g1.vertix.size();
         x_values = new LinkedList<>();
         y_values = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            x_values.add(graph.g1.vertix.get(i).getLocation().x());
            y_values.add(graph.g1.vertix.get(i).getLocation().y());
        }

        scale_x = scale(x_values, 10, 1000,1);
        scale_y = scale(y_values, 10, 650,0);
    }


    public void showGraph(){
        this_frame.add(new JPanel() {
            public void paintComponent(Graphics g2) {
                g2.setFont(g2.getFont().deriveFont(16.0F));
                Graphics2D g = (Graphics2D) g2;
                if(scale_x.size()!=graph.g1.vertix.size()){
                    x_values= new LinkedList<>();
                    y_values= new LinkedList<>();
                    for (NodeData n: graph.g1.vertix.values()) {
                        x_values.add(graph.g1.vertix.get(n.getKey()).getLocation().x());
                        y_values.add(graph.g1.vertix.get(n.getKey()).getLocation().y());
                    }
                    graph.g1.scaled_x =new HashMap<>();
                    graph.g1.scaled_y =new HashMap<>();
                    scale_x = scale(x_values, 10, 1000,1);
                    scale_y = scale(y_values, 10, 650,0);
                }
                Set<Integer> a = graph.g1.vertix.keySet();
                Iterator <Integer>  data = a.iterator();
                for (int i = 0; i < scale_x.size() && data.hasNext(); i++) {
                    int temp = data.next();
                    g.drawString(String.valueOf(temp), graph.g1.scaled_x.get(temp) + 5, graph.g1.scaled_y.get(temp) + 18);
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(1));
                    g.drawArc(graph.g1.scaled_x.get(temp), graph.g1.scaled_y.get(temp), 20, 20, 0, 360);
                }
                for (Integer src : graph.g1.edges.keySet()) {
                    for (Integer dest : graph.g1.edges.get(src).keySet()) {
                        g2.setColor(Color.green);
                        g.setStroke(new BasicStroke(2));
                        g2.drawLine(graph.g1.scaled_x.get(src) , graph.g1.scaled_y.get(src) , graph.g1.scaled_x.get(dest) , graph.g1.scaled_y.get(dest));
                        double endx = (graph.g1.scaled_x.get(dest)+graph.g1.scaled_x.get(src))/2;
                        double endy = (graph.g1.scaled_y.get(dest)+graph.g1.scaled_y.get(src))/2;
                        drawArrowHead(g,graph.g1.scaled_x.get(src) , graph.g1.scaled_y.get(src) ,(int)endx  , (int)endy);

                    }
                }
            }
        });

        this_frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String btn_str = event.getActionCommand();
        switch (btn_str) {

            case "Load Graph": {
                this.graph = new DirectedGAlgo();
                String file_name = JOptionPane.showInputDialog(null, "Enter file name");

                this.graph.load(file_name);
                if (this.graph.g1.vertix.size() != 0) {
                    int size = graph.g1.vertix.size();
                    LinkedList<Double> x_values = new LinkedList<>();
                    LinkedList<Double> y_values = new LinkedList<>();
                    Iterator<Integer> iter = graph.g1.vertix.keySet().iterator();
                    for (int i = 0; i < size; i++) {
                        int temp = iter.next();
                        x_values.add(graph.g1.vertix.get(temp).getLocation().x());
                        y_values.add(graph.g1.vertix.get(temp).getLocation().y());
                    }

                    scale_x = scale(x_values, 10, 1000,1);
                    scale_y = scale(y_values, 10, 650,0);
                    System.out.println("Graph loaded successfully");
                    break;
                }
            }

            case "Show Graph": {
                this_frame.getContentPane().removeAll();
                this_frame.repaint();
                this_frame.setSize(1080, 720);
                this_frame.setLocationRelativeTo(null);
                this_frame.setResizable(false);
                showGraph();
            //    this_frame.setVisible(true);

                break;
            }

            case "Shortest Path Distance":{
                String inputString1 = JOptionPane.showInputDialog(null, "Enter node source ID");
                String inputString2 = JOptionPane.showInputDialog(null, "Enter node destination ID");
                try {
                    int src = Integer.parseInt(inputString1);
                    int dest = Integer.parseInt(inputString2);
                    double path_dist = this.graph.shortestPathDist(src, dest);
                    JOptionPane.showMessageDialog(null, "The Shortest Distance to " + dest + " from " + src + " is: " + path_dist);
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
                break;
            }

            case "Shortest Path":{
                showGraph();
                String inputString1 = JOptionPane.showInputDialog(null, "Enter node source ID");
                String inputString2 = JOptionPane.showInputDialog(null, "Enter node destination ID");
                try {
                    int src = Integer.parseInt(inputString1);
                    int dest = Integer.parseInt(inputString2);
                    LinkedList<NodeData> path = (LinkedList<NodeData>) this.graph.shortestPath(src, dest);
                    String ans = "";
                    for (int i = path.size() - 1; i > 0; i--) {
                        ans += path.get(i).getKey() + " -> ";
                    }
                    ans += path.get(0).getKey();
                    JOptionPane.showMessageDialog(null, "The Shortest Path is: " + ans);

                    this_frame.add(new JPanel() {
                        public void paintComponent(Graphics g) {
                            g.setColor(Color.blue);
                            for (int i = path.size() - 1; i > 0; i--) {
                                Graphics2D line = (Graphics2D) g;
                                line.setColor(Color.blue);
                                line.setStroke(new BasicStroke(2));
                                int src = path.get(i).getKey();
                                int dest = path.get(i - 1).getKey();
                                g.drawLine(scale_x.get(src), scale_y.get(src), scale_x.get(dest), scale_y.get(dest));
                                double endx = (graph.g1.scaled_x.get(dest) + graph.g1.scaled_x.get(src)) / 2;
                                double endy = (graph.g1.scaled_y.get(dest) + graph.g1.scaled_y.get(src)) / 2;
                                drawArrowHead((Graphics2D) g, graph.g1.scaled_x.get(src), graph.g1.scaled_y.get(src), (int) endx, (int) endy);
                            }
                        }
                    });
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
                this_frame.setVisible(true);
                break;
            }

            case "Center":{
                NodeData center = this.graph.center();
                if(center!=null) {
                    JOptionPane.showMessageDialog(null, "The centered node is " + center.getKey());
                    this_frame.add(new JPanel() {
                        public void paintComponent(Graphics g) {
                            int x = scale_x.get(center.getKey());
                            int y = scale_y.get(center.getKey());
                            g.setColor(Color.blue);
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setColor(Color.red);
                            g2.setStroke(new BasicStroke(2));
                            g2.drawArc(x, y, 20, 20, 0, 360);
                        }
                    });
                    this_frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "There's no center in an unconnected graph");
                }
                break;
            }

            case "Connected or not?":{
                boolean connected = graph.isConnected();
                if(connected)
                    JOptionPane.showMessageDialog(null, "This graph is connected");
                else
                    JOptionPane.showMessageDialog(null, "This graph is not connected");
                break;
            }

            case "Save Graph":{
                String inputString1 = JOptionPane.showInputDialog(null, "Enter File name:");
                this.graph.save(inputString1);
            }

            case "Clean Graph": {
                this_frame.getContentPane().removeAll();
                this_frame.repaint();
                this_frame.setVisible(true);
                break;
            }

            case "Add Vertex":{
                JOptionPane.showMessageDialog(null, "Press on the wanted location for the new Vertex");
                mouseActive=true;
                break;
            }

            case "Connect Vertexes": {
                JTextField vertex1 = new JTextField();
                JTextField vertex2 = new JTextField();
                JTextField weight = new JTextField();
                Object[] input = {
                        "Vertex source:", vertex1,
                        "Vertex destination:", vertex2,
                        "Weight :", weight

                };
                int option = JOptionPane.showConfirmDialog(null, input, "Add Edge", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try{
                        int id1 = Integer.parseInt(vertex1.getText());
                        int id2 = Integer.parseInt(vertex2.getText());
                        double wght = Double.parseDouble(weight.getText());
                        graph.g1.connect(id1,id2,wght);
                        this_frame.getContentPane().removeAll();
                        this_frame.repaint();
                        this_frame.setVisible(true);
                        showGraph();
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Error, please enter only numeric values and correct ID's");
                    }
                }
                break;
            }

            case "Remove Edge":{
                JTextField vertex1 = new JTextField();
                JTextField vertex2 = new JTextField();
                Object[] input = {
                        "Vertex source:", vertex1,
                        "Vertex destination:", vertex2,

                };
                int option = JOptionPane.showConfirmDialog(null, input, "Remove Edge", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int id1 = Integer.parseInt(vertex1.getText());
                        int id2 = Integer.parseInt(vertex2.getText());
                        if (graph.g1.edges.get(id1).remove(id2)!=null){
                            JOptionPane.showMessageDialog(null, "Edge deleted successfully");}
                        else{
                            JOptionPane.showMessageDialog(null, "No Edge between given vertexes");}
                        this_frame.getContentPane().removeAll();
                        this_frame.repaint();
                        this_frame.setVisible(true);
                        showGraph();
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Please enter only numeric values");
                    }
                }
                break;
            }

            case "Remove Vertex":{
                String id = JOptionPane.showInputDialog(null, "Enter vertex ID to delete");
                try{
                    int delete = Integer.parseInt(id);

                    if (graph.g1.removeNode(delete)!=null){
                        graph.g1.scaled_x.remove(delete);
                        graph.g1.scaled_y.remove(delete);
                        JOptionPane.showMessageDialog(null, "Vertex deleted successfully");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ID doesn't exists");}
                    this_frame.getContentPane().removeAll();
                    this_frame.repaint();
                    this_frame.setVisible(true);
                    showGraph();

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid id");
                }
                break;
            }
        }
    }
    private double findMin(LinkedList<Double> list) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min)
                min = list.get(i);
        }
        return min;
    }

    private double findMax(LinkedList<Double> list) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max)
                max = list.get(i);
        }
        return max;
    }

    private LinkedList<Integer> scale(LinkedList<Double> value, int start, int finish,int k) {
        LinkedList<Integer> scaled_values = new LinkedList<>();
        double min = findMin(value);
        double max = findMax(value);
        Iterator<Integer> id = graph.g1.vertix.keySet().iterator();
        for (int i = 0; i < value.size(); i++) {
            int curr = (int) ((value.get(i) - min) * (finish - start) / (max - min) + start);
            scaled_values.add(curr);
            if(k==1)
                graph.g1.scaled_x.put(id.next(), curr);
            else
                graph.g1.scaled_y.put(id.next(), curr);

        }
        return scaled_values;
    }
    private double unScale(int loc,LinkedList<Double> value, int start, int finish) {
        double min = findMin(value);
        double max = findMax(value);
        double scaled_values = min + (loc-start)*(max-min)/(finish-start);
        return scaled_values;
    }


    private void drawArrowHead(Graphics2D g2,int x, int y, int endX, int endY) {

        Polygon arrowHead = new Polygon();

        arrowHead.addPoint(0, 5);
        arrowHead.addPoint(-5, -5);
        arrowHead.addPoint(5, -5);
        double angle = Math.atan2(endY - y, endX - x);

        AffineTransform tx1 = g2.getTransform();

        AffineTransform tx2 = (AffineTransform) tx1.clone();

        tx2.translate(endX, endY);
        tx2.rotate(angle - Math.PI / 2);

        g2.setTransform(tx2);
        g2.setColor(Color.black);
        g2.fill(arrowHead);

        g2.setTransform(tx1);
    }

}