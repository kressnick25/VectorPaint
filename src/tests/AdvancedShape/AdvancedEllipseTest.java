package AdvancedShape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedEllipseTest {
    AdvancedEllipse Ellipsetest;
    @BeforeEach
    void setUp() {
        AdvancedEllipse Ellipsetest = null;
    }
    @Test
    void constructorTestX(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        assertEquals(0.1, Ellipsetest.getX());
    }
    @Test
    void constructorTestY(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.2,0.1,0.1);
        assertEquals(0.2, Ellipsetest.getY());
    }
    @Test
    void constructorTestWidth(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.3,0.1);
        assertEquals(0.3, Ellipsetest.getWidth());
    }
    @Test
    void constructorTestHeight(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.4);
        assertEquals(0.4, Ellipsetest.getHeight());
    }
    @Test
    void constructorTestStartX(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.2,0.1,0.1,0.1);
        ArrayList startList = Ellipsetest.startGetter();
        Object startX = startList.get(0);
        assertEquals(0.2, startX);
    }
    @Test
    void constructorTestStartY(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.2,0.1,0.1);
        ArrayList startList = Ellipsetest.startGetter();
        Object startY = startList.get(1);
        assertEquals(0.2, startY);
    }

    @Test
    void fillColorTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(255,255,255);
        assertEquals(fillColor, Ellipsetest.getFillColor());
    }
    @Test
    void fillColorChangeTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(255,0,0);
        Ellipsetest.setFillColor(fillColor);
        assertEquals(fillColor, Ellipsetest.getFillColor());
    }
    @Test
    void TransparencyConstructorTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color fillColor = new Color(255,0,0);
        Ellipsetest.setFillColor(fillColor);
        assertEquals(false, Ellipsetest.isTransparent());
    }
    @Test
    void TransparencyTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        assertEquals(false, Ellipsetest.isTransparent());
    }
    @Test
    void TransparencyChangeTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Ellipsetest.setTransparent(true);
        assertEquals(true, Ellipsetest.isTransparent());
    }
    @Test
    void PenColorConstructorTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color penColor = new Color(0, 0, 0);

        assertEquals(penColor, Ellipsetest.getPenColor());
    }
    @Test
    void PenColorChangeTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color penColor = new Color(255,0,0);
        Ellipsetest.setPenColor(penColor);
        assertEquals(penColor, Ellipsetest.getPenColor());
    }
    @Test
    void toStringTest(){
        AdvancedEllipse Ellipsetest = new AdvancedEllipse(0.1,0.1,0.1,0.1);
        Color penColor = new Color(255,0,0);
        Ellipsetest.setPenColor(penColor);
        assertEquals("ELLIPSE 0.000100 0.000100 0.000100 0.000100", Ellipsetest.toString(1000,1000));
    }

    //TODO Can test setFrame in updateSize


}