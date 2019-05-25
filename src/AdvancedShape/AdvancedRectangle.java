package AdvancedShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startx);
        listA.add(starty);
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
    public void render(Graphics2D g2d){
        if (!this.isTransparent()){
            g2d.setPaint(this.getFillColor());
            g2d.fill(this);
            g2d.setPaint(this.getPenColor());
        }
        g2d.draw(this);
    }
    public String toString(int screenWidth, int screenHeight) {
        return String.format(
                "RECTANGLE %f %f %f %f",
                this.x / screenWidth,
                this.y / screenHeight,
                (this.x + this.width) / screenWidth,
                (this.y + this.height) / screenHeight);
    }
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x -= x * screenWidthDiffPercent;
        this.y -= y * screenHeightDiffPercent;
        this.width -= width * screenWidthDiffPercent;
        this.height -= height * screenHeightDiffPercent;
    }
}
