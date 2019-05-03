package component;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Polygon implements DrawComponent{

    private static Type type = Type.POLYGON;
    private ArrayList<Point> pointsList;

    // Constructor
    public Polygon(ArrayList<Point> pointsList){
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
        for (Point point : pointsList){
            joiner.add(point.toString());
        }

        return joiner.toString();
    }
}
