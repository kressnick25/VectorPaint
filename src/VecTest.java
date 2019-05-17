//import component.*;
//import component.Polygon;
//import component.Rectangle;
//import component.Shape;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.awt.*;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//class VecTest {
//
//    // Setup
//    private ArrayList<Shape> pq = new ArrayList<>();
//
//    private String filename = "VecTest.vec";
//    @BeforeEach
//    void Setup(){
//        pq.add(new Rectangle(new VectorPoint(0.1, 0.2), new VectorPoint(0.5, 0.9)));
//        pq.add(new Plot(new VectorPoint(0.1, 0.2)));
//        pq.add(new Ellipse(new VectorPoint(0.1, 0.2), new VectorPoint(0.5, 0.9)));
//        pq.add(new PenColour(new Color(255, 200, 200)) );
//        pq.add(new FillColour(new Color(100, 200, 201)));
//        ArrayList<VectorPoint> points = new ArrayList<>();
//        points.add(new VectorPoint(0.1, 0.6));
//        points.add(new VectorPoint(0.2,0.5));
//        points.add(new VectorPoint(0.3,0.4));
//        points.add(new VectorPoint(0.4,0.3));
//        points.add(new VectorPoint(0.5,0.2));
//        points.add(new VectorPoint(0.6,0.1));
//        pq.add(new Polygon(points));
//    }
//
//    @Test
//    void testReadConstructor(){
//        Vec vec = new Vec(filename);
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
//
//    // FIXME assert equals failing because of object reference comparison
//    @Test
//    void testRead(){
//        ArrayList<Drawable> base = new ArrayList<>(pq);
//        Vec vec = new Vec(filename);
//        vec.read();
//        ArrayList<Drawable> list = vec.get();
//
//        for (Drawable obj : base) {
//            assertEquals(obj, list.iterator().next());
//        }
//    }
//
//}