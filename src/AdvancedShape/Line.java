package AdvancedShape;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Line2D.Double implements AdvancedShape {
    private Color penColor = new Color(255,255,255);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;

    public Line(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Color getFillColor() {
        return fillColor;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        this.isTransparent = false;
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
        this.fillColor = null;
    }

    public void updateSize(int x, int y){
        this.setLine(this.getX1(), this.getY1(), x, y);
    }
}
