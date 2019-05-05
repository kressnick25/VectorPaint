package tests;
import component.Plot;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {
    Plot plotTest;
    Point pointTestLocation = new Point (1.0, 1.0);
    private static Drawable.Type typeTest;

    @Test
    public void testgetType()throws SequenceException {
        plotTest = new Plot(pointTestLocation);


        typeTest = Type.PLOT;
        assertEquals(typeTest, Plot.getType());

    }






}