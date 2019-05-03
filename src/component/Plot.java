package component;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.StringJoiner;

public class Plot implements DrawComponent {
    private static Type type = Type.PLOT;
    private Point point1;
    private Point2D.Float point2;

    public Plot (Point point1, Point2D.Float point2){
        this.point1 = point1;
        this.point2 = point2;
    }

    public Type getType(){
        return type;
    }

    public String toString(){
        // Construct string with space after each variable
        StringJoiner output = new StringJoiner(" ");
        output.add(getType().toString())
                .add(point1.toString())
                .add(point2.toString());

        return output.toString();
    }
}
