package component;

import java.awt.geom.Point2D;

public class Line extends Shape {

    private static DrawComponent.Type type = DrawComponent.Type.LINE;

    public Line(Point2D.Float point1, Point2D.Float point2) {
        super (point1, point2);
    }

    public Type getType(){
        return type;
    }

}