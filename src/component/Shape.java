package component;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.StringJoiner;

public abstract class Shape implements DrawComponent {
    private Point2D.Float point1, point2;

    // Default constructor
    public Shape(){

    }

    public Shape(Point2D.Float point1, Point2D.Float point2){
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     *
     * @return Command as a string.
     */
    public String toString(){
        StringJoiner output = new StringJoiner(" ");
        output.add(getType().toString())
                .add(Double.toString(point1.getX()))
                .add(Double.toString(point1.getY()))
                .add(Double.toString(point2.getX()))
                .add(Double.toString(point2.getY()));

        return output.toString();
    }
}
