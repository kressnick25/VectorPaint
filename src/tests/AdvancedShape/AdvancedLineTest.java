package AdvancedShape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedLineTest {
    AdvancedLine Linetest;
    @BeforeEach
    void setUp() {
        AdvancedLine Linetest = null;
    }
    @Test
    void constructorTestX1(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        assertEquals(0.1, Linetest.getX1());
    }
    @Test
    void constructorTestX2(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        assertEquals(0.3, Linetest.getX2());
    }
    @Test
    void constructorTestY1(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        assertEquals(0.1, Linetest.getY1());
    }
    @Test
    void constructorTestY2(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        assertEquals(0.3, Linetest.getY2());
    }

    @Test
    void constructorTestStartX1(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        ArrayList startList = Linetest.startGetter();
        Object startX = startList.get(0);
        assertEquals(0.1, startX);
    }
    @Test
    void constructorTestStartY1(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        ArrayList startList = Linetest.startGetter();
        Object startY = startList.get(1);
        assertEquals(0.1, startY);
    }
    @Test
    void constructorTestStartX2(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        ArrayList startList = Linetest.startGetter();
        Object endX = startList.get(2);
        assertEquals(0.3, endX);
    }
    @Test
    void constructorTestStartY2(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        ArrayList startList = Linetest.startGetter();
        Object endY = startList.get(3);
        assertEquals(0.3, endY);
    }
    @Test
    void fillColorTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color fillColor = new Color(255,255,255);
        assertEquals(fillColor, Linetest.getFillColor());
    }
    @Test
    void fillColorChangeTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color fillColor = new Color(255,0,0);
        Linetest.setFillColor(fillColor);
        assertEquals(fillColor, Linetest.getFillColor());
    }
    @Test
    void TransparencyConstructorTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color fillColor = new Color(255,0,0);
        Linetest.setFillColor(fillColor);
        assertEquals(true, Linetest.isTransparent());
    }
    @Test
    void TransparencyTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        assertEquals(true, Linetest.isTransparent());
    }
    @Test
    void TransparencyChangeTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Linetest.setTransparent(true);
        assertEquals(true, Linetest.isTransparent());
    }
    @Test
    void PenColorConstructorTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color penColor = new Color(0, 0, 0);

        assertEquals(penColor, Linetest.getPenColor());
    }
    @Test
    void PenColorChangeTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color penColor = new Color(255,0,0);
        Linetest.setPenColor(penColor);
        assertEquals(penColor, Linetest.getPenColor());
    }
    @Test
    void updateSizeTestX(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Linetest.updateSize(4,4);
        assertEquals(4, Linetest.getX2());
    }
    @Test
    void updateSizeTestY(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Linetest.updateSize(4,4);
        assertEquals(4, Linetest.getY2());
    }
    @Test
    void toStringTest(){
        AdvancedLine Linetest = new AdvancedLine(0.1,0.1, 0.3, 0.3);
        Color penColor = new Color(255,0,0);
        Linetest.setPenColor(penColor);
        assertEquals("LINE 0.000100 0.000100 0.000300 0.000300", Linetest.toString(1000,1000));
    }

}