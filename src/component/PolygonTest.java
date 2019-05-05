package component;

import component.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class PolygonTest {
    private Polygon polygenTest;
    private VectorPoint pointTest;
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

        VectorPoint pointTest = new VectorPoint(0.6,0.5);
        arrayPoint.add(pointTest);

        //polygenTest = new Polygon(arrayPoint);
        assertEquals(1, 1);
    }
}