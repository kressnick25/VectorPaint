package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class AdvancedPlot extends Ellipse2D.Double implements AdvancedShape{
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx;
    double starty;
    public AdvancedPlot(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.startx = x;
        this.starty = y;
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

    public void updateSize(int x, int y) {

    }

    public void render(Graphics2D g2d){
        g2d.setPaint(this.getFillColor());
        g2d.fill(this);
    }
}

