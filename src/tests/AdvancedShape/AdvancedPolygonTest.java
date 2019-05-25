package AdvancedShape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedPolygonTest {
    AdvancedPolygon Polygontest;

    @BeforeEach
    void setUp() {
        AdvancedPolygon Polygontest = null;
    }

    @Test
    void firstX() {
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Polygontest.updateSize(4, 4);
        assertEquals(4, Polygontest.xpoints[0]);
    }

    @Test
    void firstY() {
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Polygontest.updateSize(4, 4);
        assertEquals(4, Polygontest.ypoints[0]);
    }

    @Test
    void updateXTest() {
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Polygontest.updateSize(4, 4);
        Polygontest.updateSize(7, 7);

        assertEquals(7, Polygontest.xpoints[1]);
    }

    @Test
    void updateYTest() {
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Polygontest.updateSize(4, 4);
        Polygontest.updateSize(7, 7);

        assertEquals(7, Polygontest.ypoints[1]);
    }


    @Test
    void fillColorTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color fillColor = new Color(255,255,255);
        assertEquals(fillColor, Polygontest.getFillColor());
    }
    @Test
    void fillColorChangeTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color fillColor = new Color(255,0,0);
        Polygontest.setFillColor(fillColor);
        assertEquals(fillColor, Polygontest.getFillColor());
    }
    @Test
    void TransparencyConstructorTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color fillColor = new Color(255,0,0);
        Polygontest.setFillColor(fillColor);
        assertEquals(false, Polygontest.isTransparent());
    }
    @Test
    void TransparencyTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        assertEquals(false, Polygontest.isTransparent());
    }
    @Test
    void TransparencyChangeTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Polygontest.setTransparent(true);
        assertEquals(true, Polygontest.isTransparent());
    }
    @Test
    void PenColorConstructorTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color penColor = new Color(0, 0, 0);

        assertEquals(penColor, Polygontest.getPenColor());
    }
    @Test
    void PenColorChangeTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color penColor = new Color(255,0,0);
        Polygontest.setPenColor(penColor);
        assertEquals(penColor, Polygontest.getPenColor());
    }
    @Test
    void toStringTest(){
        AdvancedPolygon Polygontest = new AdvancedPolygon();
        Color penColor = new Color(255,0,0);
        Polygontest.setPenColor(penColor);
        Polygontest.updateSize(4, 4);
        Polygontest.updateSize(5, 5);
        Polygontest.updateSize(8, 8);
        assertEquals("POLYGON 0.004 0.004 0.005 0.005 0.008 0.008", Polygontest.toString(1000,1000));
    }

    //TODO Can test setFrame in updateSize


}