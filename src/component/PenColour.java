package component;

import java.awt.*;

public class PenColour extends ColourTool {
    private static Type type = Type.PEN;

    public PenColour(Color colour){
        super(colour);
    }

    public Type getType() {
        return type;
    }


}
