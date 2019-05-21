import component.*;
import component.Polygon;
import AdvancedShape.*;
import component.Shape;
import component.Plot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.*;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class VecTest {

    // Setup
    private ArrayList<AdvancedShape> pq = new ArrayList<>();

    private String filename = "VecTest.vec";
    @BeforeEach
    void Setup(){
        //pq.add(new AdvancedShape(new VectorPoint(0.1, 0.2), new VectorPoint(0.5, 0.9)));
       // pq.add(new Plot(new VectorPoint(0.1, 0.2)));
        pq.add(new AdvancedEllipse(13, 13,5, 5));
        pq.add(new AdvancedRectangle(13, 13,5, 5));
        pq.add(new AdvancedLine(13, 13,5, 5));
       // pq.add(new AdvancedPolygon(13, 13,5, 5));
        pq.add(new AdvancedLine(13, 13,5, 5));

       // pq.add(new PenColour(new Color(255, 200, 200)) );
       // pq.add(new FillColour(new Color(100, 200, 201)));
        ArrayList<VectorPoint> points = new ArrayList<>();
        points.add(new VectorPoint(0.1, 0.6));
        points.add(new VectorPoint(0.2,0.5));
        points.add(new VectorPoint(0.3,0.4));
        points.add(new VectorPoint(0.4,0.3));
        points.add(new VectorPoint(0.5,0.2));
        points.add(new VectorPoint(0.6,0.1));
       // pq.add(new Polygon(points));
    }


//TODO figure out how to test read when read is void

//    @Test
//    void testReadConstructor(){
//        Vec vec = new Vec(filename);
//
//        ArrayList<String> testLines = new ArrayList<>();
//
//        assertEquals(,vec.read())
//    }
//TODO update when exception has error message popup

//    @Test
//    public void testReadThrowsException() {
//        boolean thrown = false;
//
//        try {
//            foo.doStuff();
//        } catch (Exception e) {
//            thrown = true;
//        }
//
//        assertTrue(thrown);
//    }
//
//    @Test
//    void testWriteConstructor(){
//        Vec vec = new Vec(filename, pq);
//    }
//
//    @Test
//    void testWrite(){
//        Vec vec = new Vec(filename, pq);
//        vec.save();
//    }

    // FIXME assert equals failing because of object reference comparison
    //update, runs successfully, confirm
    @Test
    void testRead(){
        ArrayList<Drawable> base = new ArrayList<pq>(pq);
        Vec vec = new Vec(filename);
        vec.read();
        ArrayList<AdvancedShape> list = vec.get();

        for (Drawable obj : base) {
            assertEquals(obj, list.iterator().next());
        }
    }

}