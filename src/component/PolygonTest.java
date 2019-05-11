package component;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
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
    @Test
    void constructorTest(){
        ArrayList<VectorPoint> arrayPoint = new ArrayList<VectorPoint>();

        VectorPoint pointTest = new VectorPoint(0.6,0.5);
        arrayPoint.add(pointTest);

        polygenTest = new Polygon(arrayPoint);
        assertEquals(arrayPoint, polygenTest.getArrayPointsList());
    }
    @Test
    void getArrayPointsListTest(){
        ArrayList<VectorPoint> arrayPoint = new ArrayList<VectorPoint>();
        VectorPoint pointTest = new VectorPoint(0.6,0.5);
        arrayPoint.add(pointTest);
        polygenTest = new Polygon(arrayPoint);
        assertEquals(arrayPoint, polygenTest.getArrayPointsList());
    }
}