package api;


import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @org.junit.jupiter.api.Test
    void getKey() {
        NodeData n = new Node(0,new GeoLoc(7.0,1.0,0.0));
        assertEquals(0 , n.getKey());
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        NodeData n = new Node(0,new GeoLoc(10.0,11,0));
        assertEquals(10 , n.getLocation().x());
        assertEquals(11 , n.getLocation().y());
        assertEquals(0 , n.getLocation().z());
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        NodeData n = new Node(1,null);
        n.setLocation(new GeoLoc(4,3,1));
        assertEquals(4, n.getLocation().x());
        assertEquals(1, n.getLocation().z());
        assertEquals(3, n.getLocation().y());
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        NodeData n = new Node(2,null);
        NodeData n2 = new Node(5,null);
        n.setWeight(45.17);
        n2.setWeight(18.2);
        assertEquals(45.17, n.getWeight());
        assertEquals(18.2, n2.getWeight());
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
        NodeData n = new Node(4,null);
        n.setWeight(11.45);
        assertEquals(11.45, n.getWeight());
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
        NodeData n = new Node(5,null);
        NodeData n2 = new Node(3,null);
        n.setInfo("visit");
        n2.setInfo("visit");
        assertEquals("visit" , n.getInfo());
        assertEquals("visit" , n2.getInfo());
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
        NodeData n = new Node(3,null);
        NodeData n2 = new Node(4,null);
        n.setInfo("visit");
        n2.setInfo("visit");
        assertEquals("visit" , n.getInfo());
        assertEquals("visit" , n2.getInfo());
    }

    @org.junit.jupiter.api.Test
    void getTag() {
        NodeData n = new Node(0,null);
        NodeData n2 = new Node(2,null);
        n.setTag(1);
        n2.setTag(2);
        assertEquals(1, n.getTag());
        assertEquals(2, n2.getTag());
    }

    @org.junit.jupiter.api.Test
    void setTag() {
        NodeData n = new Node(0,null);
        n.setTag(1);
        assertEquals(1, n.getTag());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        NodeData n = new Node(0,new GeoLoc(1.0,2.0,3.0));
        assertEquals("Node{key=0, g1=x=1.0, y=2.0, z=3.0}'}" , n.toString());
    }
    @org.junit.jupiter.api.Test
    void testSet_Prev(){
        NodeData n= new Node(0,new GeoLoc(1,2,3));
        n.set_Prev(1);
        assertEquals(1,n.get_Prev());
    }
    @org.junit.jupiter.api.Test
    void testGet_Prev() {
        NodeData n = new Node(0, new GeoLoc(1, 2, 3));
        n.set_Prev(1);
        assertEquals(1,n.get_Prev());
    }
}