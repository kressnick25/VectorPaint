package component;

import java.awt.geom.Point2D;
import java.util.StringJoiner;

public class Plot implements DrawComponent {
    private static Type type = Type.PLOT;
    private Point2D.Float point1, point2;

    public Plot (Point2D.Float point1, Point2D.Float point2){
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
                .add(Double.toString(point1.getX()))
                .add(Double.toString(point1.getY()))
                .add(Double.toString(point2.getX()))
                .add(Double.toString(point2.getY()));

        return output.toString();
    }
}
