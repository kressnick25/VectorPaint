package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class AdvancedPlot extends Ellipse2D.Double implements AdvancedShape{
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startX;
    double startY;
    public AdvancedPlot(double x, double y) {
        super(x, y, 5, 5);
        this.startX = x;
        this.startY = y;
    }
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startX);
        listA.add(startY);
        return listA;
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
        g2d.setPaint(this.getPenColor());
        g2d.fill(this);
    }
    public String toString(int screenWidth, int screenHeight){
        return String.format(
                "PLOT %f %f",
                this.startX / screenWidth,
                this.startY / screenHeight);
    }
}

