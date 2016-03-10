package justus.tsp.engine;

import java.util.ArrayList;
import java.util.Random;

import justus.tsp.Main;
import justus.tsp.graph.TSPFrame.DrawPanel;
import justus.tsp.ui.Point;

public class Engine {

    ArrayList<Point> points = new ArrayList<Point>();
    DrawPanel panel;

    public Engine() {
        generateRandom(6);
        panel = Main.tspf.panel;
        
        printPoints();
        Thread t = new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                panel.set(points, getLength());
                try {
                    swapEdges();
                    wait(1000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                panel.set(points, getLength());
            }
        });
        t.start();
    }

    public void generateCircle(int pointAmount) {
        Point start = null;
        Point current = null, last = null;

        for(int i = 0; i < pointAmount; i++) {
            if(i == 0) { 
                start = new Point(300 + (int) (Math.cos((Math.PI * 2 / pointAmount) * i) * 200), 300 - (int) (Math.sin((Math.PI * 2 / pointAmount) * i) * 200), 0, null, "p1");
                last = start;
                points.add(start);
            } else {
                current = new Point(300 + (int) (Math.cos((Math.PI * 2 / pointAmount) * i) * 200), 300 - (int) (Math.sin((Math.PI * 2 / pointAmount) * i) * 200), i, last, "p" + (i + 1));
                points.add(current);
                last = current;
            }
        }
        start.setNextPoint(last);
        points.set(0, start);
    }

    public void generateRandom(int pointAmount) {
        Random rnd = new Random();
        Point start = null;
        Point current = null, last = null;

        for(int i = 0; i < pointAmount; i++) {
            if(i == 0) {
                start = new Point(rnd.nextInt(550) + 25, rnd.nextInt(550) + 25, 0, null, "p1");
                last = start;
                points.add(start);
            } else {
                current = new Point(rnd.nextInt(550) + 25, rnd.nextInt(550) + 25, i, last, "p" + (i + 1));
                points.add(current);
                last = current;
            }
        }
        start.setNextPoint(last);
        points.set(0, start);
    }

    public float getLength() {
        float length = 0;
        Point start = points.get(0);
        Point current = start;
        Point last = null;
        do {
            if(last != null) {
                length += Math.sqrt(Math.pow(current.getX() - last.getX(), 2) + Math.pow(current.getY() - last.getY(), 2));
            }
            if(current.getNextPoint() == null) {
                break;
            } else {
                last = current;
                current = last.getNextPoint();
            }
        } while(current != start);
        return length;
    }

    public void swapEdges() throws Exception {
        ArrayList<Point> ps = new ArrayList<>(points);
        Random rnd = new Random();
        Point p1 = ps.remove(rnd.nextInt(ps.size()));
        ps.remove(p1.getNextPoint());
        Point p2 = null;
        do {
            p2 = ps.remove(rnd.nextInt(ps.size()));
            if(ps.size() == 0) {
                throw new Exception("Nomore Items in list");
            }
        } while(p2 == null || p2.getNextPoint() == p1);
        Point temp = p1.getNextPoint();
        p1.setNextPoint(p2.getNextPoint());
        p2.setNextPoint(temp);
        System.out.println(p1 +  ", " + p2 );
        printPoints();
        
    }
    
    public void printPoints(){
        for(Point p : points){
            System.out.println(p);
        }
    }
}