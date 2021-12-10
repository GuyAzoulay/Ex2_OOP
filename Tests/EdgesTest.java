package api;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgesTest {

    @Test
    void testToString() {
        EdgeData e= new Edges(2,3,1.0631605142699874);
        assertEquals("Edges{src=2, dest=3, weight=1.0631605142699874'}",e.toString());
    }

    @Test
    void getSrc() {
        EdgeData e = new Edges(0,1 , 2.010);
        assertEquals(0 , e.getSrc());
    }

    @Test
    void getDest() {
        EdgeData e = new Edges(0,1 , 2.010);
        assertEquals(1 , e.getDest());
    }

    @Test
    void getWeight() {
        EdgeData e = new Edges(0,1 , 2.010);
        assertEquals(2.010 , e.getWeight());
    }

    @Test
    void getInfo() {
        EdgeData e = new Edges(0,1 , 2.010);
        e.setInfo("visit");
        assertEquals("visit" , e.getInfo());
    }

    @Test
    void setInfo() {
        EdgeData e = new Edges(0,1 , 2.010);
        e.setInfo("visit");
        assertEquals("visit" , e.getInfo());
    }

    @Test
    void getTag() {
        EdgeData e = new Edges(0,1 , 2.010);
        e.setTag(1);
        assertEquals(1 , e.getTag());
    }

    @Test
    void setTag() {
        EdgeData e = new Edges(0,1 , 2.010);
        e.setTag(1);
        assertEquals(1 , e.getTag());
    }
}