package AdvancedShape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedRectangleTest {
    AdvancedRectangle Rectangletest;
    @BeforeEach
    void setUp() {
        AdvancedRectangle Rectangletest = null;
    }
    @Test
    void constructorTestX(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        assertEquals(0.1, Rectangletest.getX());
    }
    @Test
    void constructorTestY(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.2,0.1,0.1);
        assertEquals(0.2, Rectangletest.getY());
    }

    @Test
    void constructorTestWidth(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.3,0.1);
        assertEquals(0.3, Rectangletest.getWidth());
    }
    @Test
    void constructorTestHeight(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.4);
        assertEquals(0.4, Rectangletest.getHeight());
    }

    @Test
    void fillColorTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(255,255,255);
        assertEquals(fillColor, Rectangletest.getFillColor());
    }
    @Test
    void fillColorChangeTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(200,100,0);
        Rectangletest.setFillColor(fillColor);
        assertEquals(fillColor, Rectangletest.getFillColor());
    }
    @Test
    void TransparencyConstructorTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(5,70,89);
        Rectangletest.setFillColor(fillColor);
        assertEquals(false, Rectangletest.isTransparent());
    }
    @Test
    void TransparencyTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        assertEquals(false, Rectangletest.isTransparent());
    }
    @Test
    void TransparencyChangeTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Rectangletest.setTransparent(true);
        assertEquals(true, Rectangletest.isTransparent());
    }
    @Test
    void PenColorConstructorTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color penColor = new Color(0, 0, 0);

        assertEquals(penColor, Rectangletest.getPenColor());
    }
    @Test
    void PenColorChangeTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color penColor = new Color(25,67,20);
        Rectangletest.setPenColor(penColor);
        assertEquals(penColor, Rectangletest.getPenColor());
    }
    @Test
    void toStringTest(){
        AdvancedRectangle Rectangletest = new AdvancedRectangle(0.1,0.1,0.1,0.1);
        Color penColor = new Color(255,0,0);
        Rectangletest.setPenColor(penColor);
        assertEquals("RECTANGLE 0.000100 0.000100 0.000100 0.000100", Rectangletest.toString(1000,1000));
    }

    //TODO Can test setFrame in updateSize


}