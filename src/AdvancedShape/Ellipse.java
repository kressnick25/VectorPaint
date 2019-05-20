package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ellipse extends Ellipse2D.Double implements AdvancedShape{
    private Color penColor = new Color(255,255,255);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;

    public Ellipse(double x, double y, double w, double h) {
        super(x, y, w, h);
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
        double width = x - this.getX();
        double height = y - this.getY();
        this.setFrame(this.getX(), this.getY(), width, height );
    }
}
