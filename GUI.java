package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GUI extends JPanel implements ActionListener {
    public DirectedGAlgo g= new DirectedGAlgo();
    GUI(){
        JFrame frame = new JFrame("Graph Interface");
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contPane = frame.getContentPane();
        contPane.setLayout(null);

        JButton button1 = new JButton("Load Graph");
        button1.setLocation(0, 0);
        button1.setSize(150, 100);
        JButton button2 = new JButton("Show Graph");
        button2.setLocation(150, 0);
        button2.setSize(150, 100);

        contPane.add(button1);
        contPane.add(button2);

        button1.addActionListener(this);
        button2.addActionListener(this);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn_str = e.getActionCommand();
        switch (btn_str) {
            case "Load Graph":
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter file name:");
                String file_name = sc.nextLine();
                DirectedGAlgo algo = null;
                try {
                    this.g.load(file_name);
                    System.out.println("Graph loaded successfully");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                break;

            case "Show Graph":
                JFrame frame = new JFrame("My Graph");
                frame.setSize(700,500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add( new JPanel() {
                    public void paintComponent( Graphics g ) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D)g;
                        g2.setColor(Color.blue);
                        g2.setStroke(new BasicStroke(2));
                        g2.drawArc(15, 15, 20, 20, 0, 360);
                    }
                });
                frame.setVisible( true );
                break;
        }
    }
    public static void main(String[] args) {
        GUI win = new GUI();
    }

}
