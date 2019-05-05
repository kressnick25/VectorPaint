package component;

import java.awt.*;

public class Eclipse extends Shape {
    private static Drawable.Type type = Drawable.Type.ECLIPSE;

    public Eclipse(Point point1, Point point2) {
        super (point1, point2);
    }

    public Type getType() {
        return type;
    }
}
