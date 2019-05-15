package component;

import java.awt.*;
import java.util.StringJoiner;

public class Plot implements Drawable {
    private static Type type = Type.PLOT;
    private VectorPoint point;

    public Plot (VectorPoint point){
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
