package AdvancedShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AdvancedRectangle extends Rectangle2D.Double implements AdvancedShape {
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx;
    double starty;

    public AdvancedRectangle(double x, double y, double w, double h) {

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

    public void updateSize(int x, int y){
        double width = 0;
        double height = 0;

        if(startx >= x && starty >= y){
            width = startx-x;
            height = starty-y;
            this.setRect(x, y, width, height);
        }
        else if( startx >= x){
            width = startx-x;
            height = y - this.getY();
            this.setRect(x, this.getY(), width, height );
        }
        else if( starty >= y){
            width = x - this.getX();
            height = starty-y;
            this.setRect(this.getX(), y, width, height );
        }
        else{

             width = x - this.getX();
             height = y - this.getY();
            this.setRect(this.getX(), this.getY(), width, height );
        }


            //System.out.println(y);




    }

}
