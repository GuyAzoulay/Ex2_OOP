package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class GUI extends JPanel implements ActionListener {
    private JFrame this_frame = new JFrame("My Graph");
    public DirectedGAlgo graph = new DirectedGAlgo();

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

        menu.add(load);

        MenuItem shortestDist = new MenuItem("Shortest Path Distance");
        shortestDist.addActionListener(this);
        MenuItem shortest_path = new MenuItem("Shortest Path");
        shortest_path.addActionListener(this);

        algo.add(shortestDist);
        algo.add(shortest_path);

        MenuItem item3 = new MenuItem("Add vertex");
        item3.addActionListener(this);




        Container contPane = frame.getContentPane();
        contPane.setLayout(null);

        JButton button2 = new JButton("Show Graph");
        button2.setLocation(0, 0);
        button2.setSize(150, 100);

        contPane.add(button2);

        button2.addActionListener(this);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String btn_str = event.getActionCommand();
        switch (btn_str) {

            case "Load Graph": {
                JTextField inputField = new JTextField(10);

                JTextArea textArea = new JTextArea(100, 200);
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                        , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                this_frame.add(inputField);
                textArea.setText("SURPRISE MOTHERFUCKER");
                this_frame.add(scrollPane);

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter file name:");
                String file_name = sc.nextLine();
                DirectedGAlgo algo = null;
                this.graph.load(file_name);
                if (this.graph.g1.vertix.size() != 0)
                    System.out.println("Graph loaded successfully");
                break;
            }

            case "Show Graph": {
                this_frame.setSize(1080, 720);
                this_frame.setLocationRelativeTo(null);

                this_frame.add(new JPanel() {
                    public void paintComponent(Graphics g) {

                        int size = graph.g1.vertix.size();
                        Graphics2D[] arr = new Graphics2D[size];
                        double[] x_values = new double[size];
                        double[] y_values = new double[size];

                        for (int i = 0; i < size; i++) {
                            x_values[i] = graph.g1.vertix.get(i).getLocation().x();
                            y_values[i] = graph.g1.vertix.get(i).getLocation().y();
                        }
                        int[] scale_x = scale(x_values, 0, 1000);
                        int[] scale_y = scale(y_values, 0, 650);
                        g.setFont(g.getFont().deriveFont(20.0F));
                        for (int i = 0; i < arr.length; i++) {
                            g.setColor(Color.blue);
                            g.drawString(String.valueOf(i), scale_x[i] + 5, scale_y[i] + 20);
                            arr[i] = (Graphics2D) g;
                            arr[i].setColor(Color.black);
                            arr[i].setStroke(new BasicStroke(2));
                            arr[i].drawArc(scale_x[i], scale_y[i], 30, 30, 0, 360);
                        }
                        g.setColor(Color.green);
                        for (Integer src : graph.g1.edges.keySet()) {
                            for (Integer dest : graph.g1.edges.get(src).keySet()) {
                                g.drawLine(scale_x[src] + 20, scale_y[src] + 15, scale_x[dest] + 10, scale_y[dest]);
                            }
                        }
                    }
                });

                this_frame.setVisible(true);
                break;
            }

            case "Shortest Path Distance":{
                String inputString1 = JOptionPane.showInputDialog(null, "Enter node source ID");
                String inputString2 = JOptionPane.showInputDialog(null, "Enter node destination ID");
                int src = Integer.parseInt(inputString1);
                int dest = Integer.parseInt(inputString2);
                double path_dist= this.graph.shortestPathDist(src,dest);
                JOptionPane.showMessageDialog(null, "The Shortest Distance to "+ dest +" from "+src+" is: " + path_dist);
            }
            case "Shortest Path":{
                String inputString1 = JOptionPane.showInputDialog(null, "Enter node source ID");
                String inputString2 = JOptionPane.showInputDialog(null, "Enter node destination ID");
                int src = Integer.parseInt(inputString1);
                int dest = Integer.parseInt(inputString2);
                LinkedList<NodeData> path_dist= (LinkedList<NodeData>) this.graph.shortestPath(src,dest);
                JOptionPane.showMessageDialog(null, "The Shortest Distance to "+ dest +" from "+src+" is: " + path_dist);
            }
        }
    }
    private double findMin(double[] arr) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    private double findMax(double[] arr) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    private int[] scale(double[] value, int start, int finish) {
        int[] new_arr = new int[value.length];
        double min = findMin(value);
        double max = findMax(value);
        for (int i = 0; i < value.length; i++) {
            new_arr[i] = (int) ((value[i] - min) * (finish - start) / (max - min) + start);
        }
        return new_arr;
    }


}