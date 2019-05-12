package component;
import component.Rectangle;
import org.junit.jupiter.api.*;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    Rectangle recTest;
    Random random = new Random();
    private int randomInt = random.nextInt(100);
    private int randomInt2 = random.nextInt(100);
    VectorPoint pointTestVector1 = new VectorPoint (randomInt, randomInt2);
    VectorPoint pointTestVector2 = new VectorPoint (randomInt, randomInt2);
    private static Drawable.Type typeTest;

    @Test
    public void testGetType() {
        recTest = new Rectangle(pointTestVector1, pointTestVector2 );

        typeTest = Drawable.Type.RECTANGLE;
        assertEquals(typeTest, recTest.getType());

    }


}