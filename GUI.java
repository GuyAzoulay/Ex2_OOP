package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class GUI extends JPanel implements ActionListener {
    private JFrame this_frame = new JFrame("My Graph");
    public DirectedGAlgo graph = new DirectedGAlgo();
    private LinkedList<Integer> scale_x;
    private LinkedList<Integer> scale_y;
    GUI(DirectedGAlgo g) {
        graph = g;
        JFrame frame = new JFrame("Graph Interface");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        MenuItem item3 = new MenuItem("Add vertex");
        item3.addActionListener(this);


        Container contPane = frame.getContentPane();
        contPane.setLayout(null);

        JButton button = new JButton("Show Graph");
        button.setLocation(0, 0);
        button.setSize(150, 100);

        contPane.add(button);

        button.addActionListener(this);
        frame.setVisible(true);

        int size = graph.g1.vertix.size();
        LinkedList<Double> x_values = new LinkedList<>();
        LinkedList<Double> y_values = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            x_values.add(graph.g1.vertix.get(i).getLocation().x());
            y_values.add(graph.g1.vertix.get(i).getLocation().y());
        }

        scale_x = scale(x_values, 10, 1000);
        scale_y = scale(y_values, 10, 650);
    }


    public void showGraph(){
        this_frame.add(new JPanel() {
            public void paintComponent(Graphics g2) {
                g2.setFont(g2.getFont().deriveFont(16.0F));
                Graphics2D g = (Graphics2D) g2;
                for (int i = 0; i < scale_x.size(); i++) {
                    g.drawString(String.valueOf(i), scale_x.get(i) + 5, scale_y.get(i) + 18);
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(1));
                    g.drawArc(scale_x.get(i), scale_y.get(i), 20, 20, 0, 360);
                }

                for (Integer src : graph.g1.edges.keySet()) {
                    for (Integer dest : graph.g1.edges.get(src).keySet()) {
                        g2.setColor(Color.green);
                        g.setStroke(new BasicStroke(2));
                        g2.drawLine(scale_x.get(src) , scale_y.get(src) , scale_x.get(dest) , scale_y.get(dest));
                        double endx = (scale_x.get(dest)+scale_x.get(src))/2;
                        double endy = (scale_y.get(dest)+scale_y.get(src))/2;
                        drawArrowHead(g,scale_x.get(src) , scale_y.get(src) ,(int)endx  , (int)endy);

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

                    for (int i = 0; i < size; i++) {
                        x_values.add(graph.g1.vertix.get(i).getLocation().x());
                        y_values.add(graph.g1.vertix.get(i).getLocation().y());
                    }

                    scale_x = scale(x_values, 10, 1000);
                    scale_y = scale(y_values, 10, 650);
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
                this_frame.setVisible(true);
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
                                double endx = (scale_x.get(dest) + scale_x.get(src)) / 2;
                                double endy = (scale_y.get(dest) + scale_y.get(src)) / 2;
                                drawArrowHead((Graphics2D) g, scale_x.get(src), scale_y.get(src), (int) endx, (int) endy);
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
                JOptionPane.showMessageDialog(null, "The centered node is "+ center.getKey());
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
                break;
            }

            case "Clean Graph": {
                this_frame.getContentPane().removeAll();
                this_frame.repaint();
                this_frame.setVisible(true);
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

    private LinkedList<Integer> scale(LinkedList<Double> value, int start, int finish) {
        LinkedList<Integer> scaled_values = new LinkedList<>();
        double min = findMin(value);
        double max = findMax(value);
        for (int i = 0; i < value.size(); i++) {
            scaled_values.add((int) ((value.get(i) - min) * (finish - start) / (max - min) + start));
        }
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