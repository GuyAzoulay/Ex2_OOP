package api;

import api.*;
import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DirectWeightGraphTest1 {

    DirectedG d1 = new DirectedG();
    DirectedG d2 = new DirectedG();
    static int gIndex;
    static int a = 0, b = 0;


    @BeforeEach
    void buildGraph() {
        for (int i = 0; i < 5; i++) {
            d1.addNode(new Node(i, new GeoLoc(i, 0, 0)));
            gIndex++;
        }
        d1.connect(0, 2, 3);
        d1.connect(0, 4, 6);
        d1.connect(0, 8, 2);
        d1.connect(1, 4, 3);
        d1.connect(1, 6, 6);
        d1.connect(3, 5, 5);
        d1.connect(7, 5, 5);
    }

    @BeforeEach
    void buildBigGraph() {
        int i;
        int j;
        double weight;

        for (i = 0; i < 1000; i++) {
            d1.addNode(new Node(i, new GeoLoc(i, 0, 0)));
        }

        i = 0;
        j = 1;
        weight = 1.0;

        while (j <= 999) {
            d1.connect(i, j, weight);
            i++;
            j++;
            weight += 0.5;
        }

    }


    @Test
    @Order(1)
    void getNode(){
        NodeData n = d1.getNode(0);
        NodeData n2 = d1.getNode(2);

        Assertions.assertEquals(n,d1.getNode(0));
        assertSame(n,d1.getNode(0));
        Assertions.assertEquals(n.getKey(),d1.getNode(0).getKey());
        assertNotEquals(n2, d1.getNode(1));
    }
    @Test
    @Order(2)
    void getEdge() {
        EdgeData e = d1.getEdge(0,2);
        Assertions.assertEquals(e, d1.getEdge(0,2));

    }

    @Test
    @Order(3)
    void addNode() {
        NodeData n = new Node(12000,new GeoLoc(5,4,5));
        NodeData n2 = new Node(13000,new GeoLoc(8,7,8));
        d1.addNode(n);
        d1.addNode(n2);
        Assertions.assertEquals(n, d1.getNode(12000));
        Assertions.assertEquals(n2, d1.getNode(13000));
    }

    @Test
    void connect() {
        d1.connect(0,8,2);
        EdgeData e = d1.getEdge(0,8);
        assertSame(e, d1.getEdge(0,8));
        Assertions.assertEquals(e,d1.getEdge(0,8));
    }

    @Test
    void nodeIter() {
        Iterator<NodeData> it = d1.nodeIter();
        Iterator<NodeData> it2 = d2.nodeIter();
        while(it.hasNext()){
            assertTrue(d1.contain(it.next()));
        }
        while(it2.hasNext()){
            assertTrue(d2.contain(it2.next()));
        }

    }

    @Test
    void removeNode() {
        NodeData n = d1.removeNode(4);
        assertFalse(d1.contain(n));
        NodeData n2 = d2.removeNode(5);
        assertFalse(d2.contain(n2));
    }

    @Test
    void removeEdge() {
        EdgeData e = d1.removeEdge(1,2);
        assertEquals(null , d1.getEdge(1,2));
    }

    @Test
    void nodeSize() {
        int count = 0 ;
        Iterator<NodeData> it0 = this.d1.nodeIter();
        while(it0.hasNext()){
            it0.next();
            count++;
        }
        Assertions.assertEquals(d1.nodeSize(),count);
    }


    @Test
    void getMC() {
        int mc = d1.getMC();
        Assertions.assertEquals(mc, d1.getMC());
    }
}