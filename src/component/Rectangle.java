package component;

import java.awt.*;

public class Rectangle extends Shape{
    private static Drawable.Type type = Drawable.Type.RECTANGLE;

    public Rectangle(Point point1, Point point2) {
        super (point1, point2);
    }

    public Type getType(){
        return type;
    }

}
