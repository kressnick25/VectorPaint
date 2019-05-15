package component;

import java.awt.*;

public class PenColour implements ColourTool {
    private static ToolType type = ToolType.PEN;
    private Color colour;

    public PenColour(Color colour){
        this.colour = colour;
    }

    public ToolType getType() {
        return type;
    }

    public String Code() {

        return colour.toString();
    }

    public String toString(){
        return type.toString() + " " + colour.toString();
    }
}
