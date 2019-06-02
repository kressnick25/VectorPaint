import AdvancedShape.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VecTest {
    Vec vec;
    int[] listA = new int[]{10,20,30,40,50,60,70,80,90,100};
    int[] listB = new int[]{10,20,30,40,50,60,70,80,90,100};
    String path = "src/tests/VecTestSamples/";
    @BeforeEach
    void setUp() {
        vec = null;
    }
    @Test
    void VecConstructorTest1(){
        Vec vec = new Vec(path + "test.VEC");
        assertEquals(path + "test.VEC", vec.getFileName());
    }
    @Test
    void VecConstructorTest2(){
        ArrayList<AdvancedShape> shapes = new ArrayList<AdvancedShape>();
        AdvancedEllipse ellipse = new AdvancedEllipse(100, 100, 100,100);
        AdvancedRectangle rectangle = new AdvancedRectangle(100, 100, 100,100);
        AdvancedLine line = new AdvancedLine(100, 100, 100,100);

        AdvancedPolygon polygon = new AdvancedPolygon(listA, listB, 10);
        AdvancedPlot plot = new AdvancedPlot(100, 100);
        shapes.add(ellipse);
        shapes.add(rectangle);
        shapes.add(line);
        shapes.add(polygon);
        shapes.add(plot);
        Vec vec = new Vec(path + "test.VEC", shapes);
        assertEquals(shapes, vec.getShapes());
    }
    @Test
    void parseShapeToStringRecTest(){
        AdvancedRectangle thing = new AdvancedRectangle(100, 100, 100,100);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("RECTANGLE 0.100000 0.100000 0.200000 0.200000", otherThing);

    }
    @Test
    void parseShapeToStringEllipseTest(){
        AdvancedEllipse thing = new AdvancedEllipse(100, 100, 100, 100);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("ELLIPSE 0.100000 0.100000 0.200000 0.200000", otherThing);
    }

    @Test
    void parseShapeToStringLineTest(){
        AdvancedLine thing = new AdvancedLine(100, 100, 200, 200);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("LINE 0.100000 0.100000 0.200000 0.200000", otherThing);
    }

    @Test
    void parseShapeToStringPlotTest(){
        AdvancedPlot thing = new AdvancedPlot(100, 100);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PLOT 0.100000 0.100000", otherThing);
    }

    @Test
    void parseShapeToStringPolygonTest(){
        int[] points = new int[3];
        points[0] = 100;
        points[1] = 200;
        points[2] = 300;
        AdvancedPolygon thing = new AdvancedPolygon(points, points, 3);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("POLYGON 0.1 0.1 0.2 0.2 0.3 0.3", otherThing);
    }

    @Test
    void parseShapeToStringRecTestWithColor(){
        AdvancedRectangle thing = new AdvancedRectangle(100, 100, 100,100);
        thing.setPenColor(Color.blue);
        thing.setFillColor(Color.black);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PEN #0000ff\nFILL #000000\nRECTANGLE 0.100000 0.100000 0.200000 0.200000", otherThing);

    }
    @Test
    void parseShapeToStringEllipseTestWithColor(){
        AdvancedEllipse thing = new AdvancedEllipse(100, 100, 100, 100);
        thing.setPenColor(Color.blue);
        thing.setFillColor(Color.black);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PEN #0000ff\nFILL #000000\nELLIPSE 0.100000 0.100000 0.200000 0.200000", otherThing);
    }

    @Test
    void parseShapeToStringLineTestWithColor(){
        AdvancedLine thing = new AdvancedLine(100, 100, 200, 200);
        thing.setPenColor(Color.blue);
        thing.setFillColor(Color.black);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PEN #0000ff\nFILL #000000\nLINE 0.100000 0.100000 0.200000 0.200000", otherThing);
    }

    @Test
    void parseShapeToStringPlotTestWithColor(){
        AdvancedPlot thing = new AdvancedPlot(100, 100);
        thing.setPenColor(Color.blue);
        thing.setFillColor(Color.black);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PEN #0000ff\nFILL #000000\nPLOT 0.100000 0.100000", otherThing);
    }

    @Test
    void parseShapeToStringPolygonTestWithColor(){
        int[] points = new int[3];
        points[0] = 100;
        points[1] = 200;
        points[2] = 300;
        AdvancedPolygon thing = new AdvancedPolygon(points, points, 3);
        thing.setPenColor(Color.blue);
        thing.setFillColor(Color.black);
        Vec vec = new Vec(path + "test.VEC");
        String otherThing = vec.parseShapeToString(thing);
        assertEquals("PEN #0000ff\nFILL #000000\nPOLYGON 0.1 0.1 0.2 0.2 0.3 0.3", otherThing);
    }

    @Test
    void parseMultipleShapesToString(){
//        int numberOfShapes = (int )(Math.random() * 5 + 1);
        int numberOfShapes = 5;

        Vec vec = new Vec(path + "test.VEC");
        String saveString = "";

        for (var i = 0; i < numberOfShapes; i++) {
            AdvancedRectangle shape = new AdvancedRectangle(i * 100, i * 100, 100, 100);
            shape.setPenColor(Color.pink);
            shape.setFillColor(Color.orange);
            saveString = saveString.concat(vec.parseShapeToString(shape));
        }

        assertEquals("PEN #ffafaf\n" +
                "FILL #ffc800\n" +
                "RECTANGLE 0.000000 0.000000 0.100000 0.100000" +
                "PEN #ffafaf\n" +
                "RECTANGLE 0.100000 0.100000 0.200000 0.200000" +
                "PEN #ffafaf\n" +
                "RECTANGLE 0.200000 0.200000 0.300000 0.300000" +
                "PEN #ffafaf\n" +
                "RECTANGLE 0.300000 0.300000 0.400000 0.400000" +
                "PEN #ffafaf\n" +
                "RECTANGLE 0.400000 0.400000 0.500000 0.500000", saveString);
    }

    @Test
    void testSaveThenReadFile() {
        int numberOfShapes = 5;

        Vec vec = new Vec(path + "test");
        ArrayList<AdvancedShape> shapes = new ArrayList<>();
        for (var i = 0; i < numberOfShapes; i++) {
            AdvancedRectangle shape = new AdvancedRectangle(i * 100, i * 100, 100, 100);
            shape.setPenColor(Color.pink);
            shape.setFillColor(Color.orange);
            shapes.add(shape);
        }

        vec.setShapes(shapes);

        try {
            vec.save();
            vec.read();
        } catch (Exception e) {
            fail(e.toString());
        }

        ArrayList<AdvancedShape> savedShapes = vec.getShapes();

        boolean success = true;
        for (var i = 0; i < numberOfShapes; i++) {
            String oldShapeString = vec.parseShapeToString(shapes.get(i));
            String newShapeString = vec.parseShapeToString(savedShapes.get(i));
            if (!oldShapeString.equals(newShapeString)) {
                success = false;
            }
        }

        assertTrue(success);
    }

    @Test
    void fileNameTest() {
        Vec vec = new Vec(path + "test.VEC");
        testSaveThenReadFile();
        assertEquals(path + "test.VEC", vec.getFileName());
    }

    @Test
    void largeFileTest() {
        Vec vec = new Vec(path + "LargeFile.VEC");
        try {
            vec.read();
        } catch (Exception e) {
            fail(e.toString());
        }

        ArrayList<AdvancedShape> shapes = vec.get();

        assertEquals(187, shapes.size());
    }

    @Test
    void loadExample1FromBlackboard() {
        Vec vec = new Vec(path + "blackboardExample1.VEC");
        try {
            vec.read();
        } catch (Exception e) {
            fail(e.toString());
        }

        ArrayList<AdvancedShape> shapes = vec.get();

        assertEquals(50, shapes.size());
    }

    @Test
    void loadExample2FromBlackboard() {
        Vec vec = new Vec(path + "blackboardExample2.VEC");
        try {
            vec.read();
        } catch (Exception e) {
            fail(e.toString());
        }

        ArrayList<AdvancedShape> shapes = vec.get();

        assertEquals(16, shapes.size());
    }

    @Test
    void loadExample3FromBlackboard() {
        Vec vec = new Vec(path + "blackboardExample3.VEC");
        try {
            vec.read();
        } catch (Exception e) {
            fail(e.toString());
        }

        ArrayList<AdvancedShape> shapes = vec.get();

        assertEquals(166, shapes.size());
    }
}