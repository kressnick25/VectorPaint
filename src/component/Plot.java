package component;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.StringJoiner;

public class Plot implements DrawComponent {
    private static Type type = Type.PLOT;
    private Point point;

    public Plot (Point point){
        this.point = point;
    }

    public Type getType(){
        return type;
    }

    public String toString(){
        // Construct string with space after each variable
        StringJoiner output = new StringJoiner(" ");
        output.add(getType().toString())
                .add(point.toString());

        return output.toString();
    }
}
