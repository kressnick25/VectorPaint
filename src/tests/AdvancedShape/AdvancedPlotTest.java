package AdvancedShape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedPlotTest {
    AdvancedPlot Plottest;
    @BeforeEach
    void setUp() {
        AdvancedPlot Plottest = null;
    }
    @Test
    void constructorTestX(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        assertEquals(0.1, Plottest.getX());
    }
    @Test
    void constructorTestY(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.2);
        assertEquals(0.2, Plottest.getY());
    }
    @Test
    void constructorTestWidth(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        assertEquals(5, Plottest.getWidth());
    }
    @Test
    void constructorTestHeight(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        assertEquals(5, Plottest.getHeight());
    }

    @Test
    void fillColorTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color fillColor = new Color(255,255,255);
        assertEquals(fillColor, Plottest.getFillColor());
    }
    @Test
    void fillColorChangeTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color fillColor = new Color(255,0,0);
        Plottest.setFillColor(fillColor);
        assertEquals(fillColor, Plottest.getFillColor());
    }
    @Test
    void TransparencyConstructorTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color fillColor = new Color(255,0,0);
        Plottest.setFillColor(fillColor);
        assertEquals(false, Plottest.isTransparent());
    }
    @Test
    void TransparencyTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        assertEquals(false, Plottest.isTransparent());
    }
    @Test
    void TransparencyChangeTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Plottest.setTransparent(true);
        assertEquals(true, Plottest.isTransparent());
    }
    @Test
    void PenColorConstructorTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color penColor = new Color(0, 0, 0);

        assertEquals(penColor, Plottest.getPenColor());
    }
    @Test
    void PenColorChangeTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color penColor = new Color(255,0,0);
        Plottest.setPenColor(penColor);
        assertEquals(penColor, Plottest.getPenColor());
    }
    @Test
    void toStringTest(){
        AdvancedPlot Plottest = new AdvancedPlot(0.1,0.1);
        Color penColor = new Color(255,0,0);
        Plottest.setPenColor(penColor);
        assertEquals("PLOT 0.000100 0.000100", Plottest.toString(1000,1000));
    }

    //TODO Can test setFrame in updateSize


}