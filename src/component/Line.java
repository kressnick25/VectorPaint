package component;

import java.awt.*;

public class Line extends Shape {

    private static DrawComponent.Type type = DrawComponent.Type.LINE;

    public Line(Point point1, Point point2) {
        super (point1, point2);
    }

    public Type getType(){
        return type;
    }

}