package component;
import component.Plot;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {
    Plot plotTest;
    VectorPoint pointTestLocation = new VectorPoint (0.9, 0.9);
    private static Drawable.Type typeTest;

    @Test
    public void testgetType() {
        plotTest = new Plot(pointTestLocation);


        typeTest = Drawable.Type.PLOT;
        assertEquals(typeTest, plotTest.getType());

    }






}