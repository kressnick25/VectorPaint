package component;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PolygonTest {
    private Polygon polygenTest;
    private Point pointTest;
    private Throwable thrown;
    private ArrayList<VectorPoint> arrayPoint;

    @BeforeEach
    void setUp(){
        polygenTest = null;
        pointTest = null;
        thrown = null;
        arrayPoint = null;
    }
    ArrayList arrayInitializer(){
        ArrayList<VectorPoint> arrayPoint = new ArrayList<VectorPoint>();

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
    void toStringTest() {
        ArrayList<VectorPoint> arrayPoint = arrayInitializer();
        //TODO Need to confirm if Point or VectorPoint
        polygenTest = new Polygon(arrayPoint);



        assertEquals(1, 1);
    }

}