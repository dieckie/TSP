package justus.tsp.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import justus.tsp.ui.Point;

public class TSPFrame extends JFrame {

    private static final long serialVersionUID = 4378300843044527392L;

    public DrawPanel panel;

    public TSPFrame() {
        Insets i = getInsets();
        setSize(650 + i.left + i.right, 650 + i.top + i.bottom);
        setTitle("Travelling Salesman Problem");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new DrawPanel();
        add(panel);
        setBackground(Color.WHITE);
        setVisible(true);

    }

    public class DrawPanel extends JPanel {

        ArrayList<Point> points = new ArrayList<Point>();
        float length;

        private static final long serialVersionUID = 5475415706475883069L;

        public DrawPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if(points != null && points.size() > 0) {
                Point start = points.get(0);
                Point current = start;
                Point last = null;
                do {
                    // System.out.println(current + " " + last + " " + start);
                    g.fillRect(current.getX() - 5, current.getY() - 5, 10, 10);
                    String s = current + "";
                    g.drawString(s, current.getX() - g.getFontMetrics().stringWidth(s) / 2, current.getY() - 10);
                    if(last != null) {
                        g.drawLine(last.getX(), last.getY(), current.getX(), current.getY());
                    }
                    if(current.getNextPoint() == null) {
                        break;
                    } else {
                        last = current;
                        current = last.getNextPoint();
                    }
                } while(current != start);
                g.drawLine(last.getX(), last.getY(), start.getX(), start.getY());
                if(length != 0) {
                    g.drawString("Length " + Math.round(length), 10, getHeight() - 10);
                }
            }
        }

        public void setPoints(ArrayList<Point> points) {
            this.points = points;
        }

        public void setLength(float length) {
            this.length = length;
            System.out.println(length);
        }
        
        public void set(ArrayList<Point> points, float length){
            this.points = points;
            this.length = length;
            repaint();
        }

        
    }

}
