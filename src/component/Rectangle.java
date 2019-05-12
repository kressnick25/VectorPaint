package component;

import java.awt.*;
import java.util.Vector;

public class Rectangle extends Shape{
    private static Drawable.Type type = Drawable.Type.RECTANGLE;

    public Rectangle(VectorPoint point1, VectorPoint point2) {
        super (point1, point2);
    }

    public Type getType(){
        return type;
    }

}
//do we need a toString() method?
