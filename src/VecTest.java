import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class VecTest {

    // Setup
    private Queue<String> pq = new PriorityQueue<>();
    private String filename = "VecTest.txt";
    @BeforeEach
    void Setup(){
        pq.add("LINE 0.1 0.2 0.3 0.4");
        pq.add("RECTANGLE 0.1 0.2 0.3 0.4");
        pq.add("PLOT 0.1 0.2 0.3 0.4");
        pq.add("ELLIPSE 0.1 0.2 0.3 0.4");
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

    @Test
    void testRead(){
        ArrayList<String> base = new ArrayList<>(pq);
        Vec vec = new Vec(filename);
        vec.read();
        ArrayList<String> list = vec.get();

        assertEquals(base, list);
    }

}