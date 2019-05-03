package component;

import java.util.StringJoiner;

public abstract class Shape implements DrawComponent {
    private float x1, y1, x2, y2;

    // Default constructor
    public Shape(){

    }

    public Shape(float x1, float y1, float x2, float y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     *
     * @return Command as a string.
     */
    public String toString(){
        StringJoiner output = new StringJoiner(" ");
        output.add(getType().toString())
                .add(Float.toString(x1))
                .add(Float.toString(y1))
                .add(Float.toString(x2))
                .add(Float.toString(y2));

        return output.toString();
    }
}
