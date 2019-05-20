package AdvancedShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AdvancedRectangle extends Rectangle2D.Double implements AdvancedShape {
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx = 0;
    double starty = 0;

    public AdvancedRectangle(double x, double y, double w, double h) {
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
        double width = 0;
        double height = 0;

        if(getBounds2D().getX() >= x && getBounds2D().getY() >= y){

//            width = x - this.getX();
//            height = y - this.getY();
            width = startx-x;
            height = starty-y;
            this.setRect(x, y, width, height);
            System.out.println(getBounds2D().getX());
        }
        else{
             startx = this.getX();
             starty = this.getY();
             width = x - this.getX();
             height = y - this.getY();
            this.setRect(this.getX(), this.getY(), width, height );
        }


            //System.out.println(y);




    }

}
