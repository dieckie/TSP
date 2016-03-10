package justus.tsp;

import justus.tsp.engine.Engine;
import justus.tsp.graph.TSPFrame;

public class Main {

   public static TSPFrame tspf;
   public static Engine engine;

    public static void main(String[] args) {
        tspf = new TSPFrame();
        engine = new Engine();
    }

}
