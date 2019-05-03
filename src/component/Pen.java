package component;

import java.awt.*;

public class Pen implements ColourTool {
    private static ToolType type = ToolType.PEN;
    private Color colour;

    public Pen(Color colour){
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
