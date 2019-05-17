package component;

import java.awt.*;

public class FillColour extends  ColourTool {
    private static Type type = Type.FILL;

    public FillColour(Color colour){
        super(colour);
    }

    public Type getType() {
        return type;
    }
}
