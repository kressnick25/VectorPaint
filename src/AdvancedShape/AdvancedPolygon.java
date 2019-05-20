package AdvancedShape;

import java.awt.*;

public class AdvancedPolygon extends java.awt.Polygon implements AdvancedShape {
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;


    public AdvancedPolygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    public AdvancedPolygon() {
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
        this.addPoint(x, y);
    }
}
