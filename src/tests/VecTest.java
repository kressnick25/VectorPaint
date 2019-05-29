import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AdvancedShape.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VecTest {
    Vec vec;
    int[] listA = new int[]{10,20,30,40,50,60,70,80,90,100};
    int[] listB = new int[]{10,20,30,40,50,60,70,80,90,100};
    @BeforeEach
    void setUp() {
        vec = null;
    }
    @Test
    void VecConstructorTest1(){
        Vec vec = new Vec("test");
        assertEquals("test", vec.getFileName());
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
        Vec vec = new Vec("test", shapes);
        assertEquals(shapes, vec.getShapes());
    }
    @Test
    void parseShapeToStringRecTest(){
        AdvancedRectangle rectangle = new AdvancedRectangle(100, 100, 100,100);
        Vec vec = new Vec("test");
        //vec.parseShapeToString(rectangle);

    }
    @Test
    void parseShapeToStringEllipseTest(){

    }
    @Test
    void parseShapeToStringLineTest(){

    }
    @Test
    void parseShapeToStringPlotTest(){

    }
    @Test
    void parseShapeToStringPolygonTest(){

    }
}