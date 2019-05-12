package component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {
    private Line LineTest;

    @BeforeEach
    void setUp() {
        LineTest = null;
    }
    @Test
    void LineConstructorTest(){
        VectorPoint pointTest1 = new VectorPoint(0.6,0.5);
        VectorPoint pointTest2 = new VectorPoint(0.6,0.8);
        ArrayList<VectorPoint> arrayPoint = new ArrayList<>();
        arrayPoint.add(pointTest1);
        arrayPoint.add(pointTest2);
        LineTest = new Line(pointTest1, pointTest2);
        assertEquals(arrayPoint, LineTest.VectorPointGetter());

    }


    @Test
    void getTypeTest() {
        Drawable.Type typeTest = Drawable.Type.LINE;
        VectorPoint pointTest1 = new VectorPoint(0.6,0.5);
        VectorPoint pointTest2 = new VectorPoint(0.6,0.8);
        ArrayList<VectorPoint> arrayPoint = new ArrayList<>();
        arrayPoint.add(pointTest1);
        arrayPoint.add(pointTest2);
        LineTest = new Line(pointTest1, pointTest2);
        assertEquals(typeTest, LineTest.getType());
    }
}