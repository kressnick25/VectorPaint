package AdvancedShape;

import java.awt.*;
import java.util.IllegalFormatWidthException;
import java.util.StringJoiner;

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

    public void render(Graphics2D g2d){
        if (!this.isTransparent()){
            g2d.setPaint(this.getFillColor());
            g2d.fill(this);
            g2d.setPaint(this.getPenColor());
        }
        g2d.draw(this);
    }

    public String toString(int screenWidth, int screenHeight){
        // use stringjoiner to create string with space after each add
        StringJoiner outString = new StringJoiner(" ");
        outString.add("POLYGON");
        for (int i=0; i < this.xpoints.length; i++){
            double x = (double) xpoints[i] / screenWidth;
            double y = (double) ypoints[i] / screenHeight;
            // ignore case where == 0.0, 0.0
            if (x != 0.0 || y != 0.0){
                outString.add( Double.toString(x) );
                outString.add( Double.toString(y) );
            }


        }
        return outString.toString();
    }
}
