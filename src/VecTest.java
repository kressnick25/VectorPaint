import component.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;



class VecTest {

    // Setup
    private ArrayList<Drawable> pq = new ArrayList<>();

    private String filename = "VecTest.vec";
    @BeforeEach
    void Setup(){
        pq.add(new Rectangle(new VectorPoint(0.1, 0.2), new VectorPoint(0.5, 0.9)));
        pq.add(new Plot(new VectorPoint(0.1, 0.2)));
        pq.add(new Ellipse(new VectorPoint(0.1, 0.2), new VectorPoint(0.5, 0.9)));
    }

    @Test
    void testReadConstructor(){
        Vec vec = new Vec(filename);
    }

    @Test
    void testWriteConstructor(){
        Vec vec = new Vec(filename, pq);
    }

    @Test
    void testWrite(){
        Vec vec = new Vec(filename, pq);
        vec.save();
    }

    // FIXME assert equals failing because of object reference comparison
    @Test
    void testRead(){
        ArrayList<Drawable> base = new ArrayList<>(pq);
        Vec vec = new Vec(filename);
        vec.read();
        ArrayList<Drawable> list = vec.get();

        for (Drawable obj : base) {
            assertEquals(obj, list.iterator().next());
        }
    }

}