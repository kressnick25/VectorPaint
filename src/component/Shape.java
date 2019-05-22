package component;

import java.awt.*;
import java.util.StringJoiner;

public abstract class Shape implements Drawable {
    private VectorPoint point1;
    private VectorPoint point2;

    // Default constructor
    public Shape(){

    }

    public Shape(VectorPoint point1, VectorPoint point2){
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
                .add(point1.toString())
                .add(point2.toString());
        return output.toString();
    }
}
