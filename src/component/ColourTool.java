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
        return Integer.toString(colour.hashCode());
    }

    public String toString(){
        return this.getType().toString() + " #" + Integer.toHexString(colour.getRGB()).substring(2).toUpperCase();
    }

}
