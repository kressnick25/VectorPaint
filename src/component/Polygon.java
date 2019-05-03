package component;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Polygon implements DrawComponent{

    private static Type type = Type.POLYGON;
    private ArrayList<Point2D.Float> pointsList;

    // Constructor
    public Polygon(ArrayList<Point2D.Float> pointsList){
        this.pointsList = pointsList;
    }

    public Type getType(){
        return type;
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(" ");
        // Add type to command
        joiner.add(type.toString());
        // For each point in list, add X coord, a space,  Y coord
        for (Point2D.Float point : pointsList){
            joiner.add(Double.toString(point.getX()));
            joiner.add(Double.toString(point.getY()));
        }

        return joiner.toString();
    }
}
