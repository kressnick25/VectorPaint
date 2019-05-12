package component;

import java.awt.*;
import java.util.ArrayList;
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

    public ArrayList VectorPointGetter(){
        ArrayList<VectorPoint> arrayPoint = new ArrayList<>();
        arrayPoint.add(point1);
        arrayPoint.add(point2);


        return arrayPoint;
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
