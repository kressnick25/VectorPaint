package component;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PolygonTest {
    private Polygon polygenTest;
    private Point pointTest;
    private Throwable thrown;
    private ArrayList<VectorPoint> arrayPoint;
    private static Drawable.Type typeTest;

    @BeforeEach
    void setUp(){
        polygenTest = null;
        pointTest = null;
        thrown = null;
        arrayPoint = null;
    }
    ArrayList arrayInitializer(){
        ArrayList<VectorPoint> arrayPoint = new ArrayList<>();

        VectorPoint pointTest = new VectorPoint(0.6,0.5);
        arrayPoint.add(pointTest);
        return arrayPoint;
    }
    @Test
    void constructorTest(){
        ArrayList<VectorPoint> arrayPoint = arrayInitializer();

        polygenTest = new Polygon(arrayPoint);
        assertEquals(arrayPoint, polygenTest.getArrayPointsList());
    }
    @Test
    void getArrayPointsListTest(){
        ArrayList<VectorPoint> arrayPoint = arrayInitializer();

        polygenTest = new Polygon(arrayPoint);
        assertEquals(arrayPoint, polygenTest.getArrayPointsList());
    }
    @Test
    void getTypeTest() {
        ArrayList<VectorPoint> arrayPoint = arrayInitializer();

        polygenTest = new Polygon(arrayPoint);
        assertEquals(Drawable.Type.POLYGON, polygenTest.getType());
    }

    @Test
    void testToString(){
        ArrayList<VectorPoint> arrayPoint = arrayInitializer();

        polygenTest = new Polygon(arrayPoint);

        typeTest = Drawable.Type.POLYGON;

        String testString = "POLYGON 0.6 0.5";

        assertEquals(testString, polygenTest.toString());
    }

}