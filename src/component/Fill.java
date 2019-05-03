package component;

import java.awt.*;

public class Fill implements  ColourTool {
    private static ToolType type = ToolType.FILL;
    private Color colour;

    public Fill(Color colour){
        this.colour = colour;
    }

    public ToolType getType() {
        return type;
    }

    public String Code() {

        return colour.toString();
    }

    public String toString() {
        return type.toString() + " " + colour.toString();
    }

}
