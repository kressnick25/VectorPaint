package AdvancedShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class AbstractAdvancedShape implements AdvancedShape {
    private Color penColor;
    private Color fillColor;
    private boolean isTransparent;

    public Color getFillColor() {
        return fillColor;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    public Color getPenColor() {
        return penColor;
    }
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }
    public boolean isTransparent() {
        return isTransparent;
    }
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }
}
