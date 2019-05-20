package AdvancedShape;

import java.awt.*;
import java.awt.geom.Line2D;

public class AdvancedLine extends Line2D.Double implements AdvancedShape {
    private Color penColor = new Color(255,255,255);
    private boolean isTransparent = true;

    public AdvancedLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Color getFillColor() {
        return null;
    }
    public void setFillColor(Color fillColor) {

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

    }

    public void updateSize(int x, int y){
        this.setLine(this.getX1(), this.getY1(), x, y);
    }
}
