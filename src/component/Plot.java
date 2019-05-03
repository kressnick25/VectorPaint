package component;

import java.util.StringJoiner;

public class Plot implements DrawComponent {
    private static Type type = Type.PLOT;
    private float x1, y1, x2, y2;

    public Plot (float x1, float x2, float y1, float y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Type getType(){
        return type;
    }

    public String toString(){
        // Construct string with space after each variable
        StringJoiner output = new StringJoiner(" ");
        output.add(getType().toString())
                .add(Float.toString(x1))
                .add(Float.toString(y1))
                .add(Float.toString(x2))
                .add(Float.toString(y2));

        return output.toString();
    }
}
