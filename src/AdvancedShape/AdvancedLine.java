package AdvancedShape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class AdvancedLine extends Line2D.Double implements AdvancedShape {
    private Color fillColor = new Color(255,255,255);
    private Color penColor = new Color(0, 0, 0);
    private boolean isTransparent = true;
    double startX1;
    double startY1;
    double endX2;
    double endY2;

    public AdvancedLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
        this.startX1 = x1;
        this.startY1 = y1;
        this.endX2 = x2;
        this.endY2 = y2;
    }

    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x1 -= x1 * screenWidthDiffPercent;
        this.y1 -= y1 * screenHeightDiffPercent;
        this.x2 -= x2 * screenWidthDiffPercent;
        this.y2 -= y2 * screenHeightDiffPercent;
    }

    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startX1);
        listA.add(startY1);
        listA.add(endX2);
        listA.add(endY2);
        return listA;
    }


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

    }

    public void updateSize(int x, int y){
        this.setLine(this.getX1(), this.getY1(), x, y);
    }

    public void render(Graphics2D g2d){
        g2d.setPaint(this.getPenColor());
        g2d.draw(this);
    }

    public String toString(int screenWidth, int screenHeight) {
        return String.format(
                "LINE %f %f %f %f",
                this.x1 / screenWidth,
                this.y1 / screenHeight,
                this.x2 / screenWidth,
                this.y2 / screenHeight);
    }
}
