package component;

import java.awt.*;

public abstract class ColourTool implements Drawable {
    private Color colour;

    // Default constructor
    public ColourTool(){

    }

    public ColourTool(Color colour){
        this.colour = colour;
    }

    public String Code() {
        return colour.toString();
    }

    public String toString(){
        return this.getType().toString() + " " + this.Code();
    }

}
