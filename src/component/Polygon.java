package component;

import java.awt.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Vector;

public class Polygon implements Drawable {

    private static Type type = Type.POLYGON;
    private ArrayList<VectorPoint> pointsList;

    // Constructor
    public Polygon(ArrayList<VectorPoint> pointsList){
        this.pointsList = pointsList;
    }
    public ArrayList getArrayPointsList(){ return pointsList; }

    public Type getType(){
        return type;
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(" ");
        // Add type to command
        joiner.add(type.toString());
        // For each point in list, add X coord, a space,  Y coord
        for (VectorPoint point : pointsList){
            joiner.add(point.toString());
        }

        return joiner.toString();
    }
}
