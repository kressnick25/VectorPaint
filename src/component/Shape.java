package component;

import java.awt.*;
import java.util.StringJoiner;

public abstract class Shape implements DrawComponent {
    private Point point1;
    private Point point2;

    // Default constructor
    public Shape(){

    }

    public Shape(Point point1, Point point2){
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
