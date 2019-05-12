package component;
import component.Plot;
import org.junit.jupiter.api.*;
import java.util.Random;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {
    Plot plotTest;
    Random random = new Random();
    int randomInt = random.nextInt(100);
    int randomInt2 = random.nextInt(100);
    VectorPoint pointTestLocation = new VectorPoint (randomInt, randomInt2);
    private static Drawable.Type typeTest;

    @Test
    public void testGetType() {
        plotTest = new Plot(pointTestLocation);


        typeTest = Drawable.Type.PLOT;
        assertEquals(typeTest, plotTest.getType());

    }

    @Test
    public void testToString(){
        plotTest = new Plot(pointTestLocation);
        typeTest = Drawable.Type.PLOT;

        StringJoiner testOutput = new StringJoiner(" ");
        String testString = testOutput.toString();

        assertEquals(testString,plotTest.toString());
    }




}