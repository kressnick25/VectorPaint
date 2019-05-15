package component;

import java.awt.*;

public class Ellipse extends Shape {
    private static Drawable.Type type = Drawable.Type.ELLIPSE;

    public Ellipse(VectorPoint point1, VectorPoint point2) {
        super (point1, point2);
    }

    public Type getType() {
        return type;
    }
}
