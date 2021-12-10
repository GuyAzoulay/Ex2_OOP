package api;

import api.DirectedG;
import api.NodeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectWeightGraphAlgoTest {
    DirectedG d1 = new DirectedG();
    DirectedGAlgo d11 = new DirectedGAlgo();
    DirectedG d2 = new DirectedG();
    DirectedGAlgo d22 = new DirectedGAlgo();

    @BeforeEach
    void setUp() {
        d11.init(d1);
        d11.load("src/main/resources/data/G1.json");
        d1 = (DirectedG) d11.getGraph();
        d22.init(d2);
//        d22.load("1000Nodes.json");
    }

    @Test
    void init() {

    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        d11.getGraph().removeNode(16);
        d11.getGraph().removeNode(12);
        
//        boolean ans = d11.isConnected();
        assertFalse(d11.isConnected());
//        ans = d22.isConnected();
//        assertTrue(ans);
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
//        System.out.println(dwga1.center());;
    }

//    @Test
//    void tsp() {
//        List<NodeData> cities = new LinkedList<>();
//        cities.add(this.d11.getGraph().getNode(5));
//        cities.add(this.d11.getGraph().getNode(9));
//        cities.add(this.d11.getGraph().getNode(10));
//        assertEquals("[5, 6, 7, 8, 9, 10]" , d11.tsp(cities).toString());
//
//        List<NodeData> citiesBig = new LinkedList<>();
//        citiesBig.add(this.dwga1.getGraph().getNode(7));
//        citiesBig.add(this.dwga1.getGraph().getNode(300));
//        citiesBig.add(this.dwga1.getGraph().getNode(50));
//        assertEquals("[7, 599, 6, 50, 149, 799, 817, 895, 486, 284, 300]" , dwga1.tsp(citiesBig).toString());
//    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}