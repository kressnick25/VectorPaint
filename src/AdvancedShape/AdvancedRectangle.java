package AdvancedShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 Shape AdvancedRectangle, initiate a color, fill color, and transparency.
 The shape Rectangle can be drawn and passed into the GUI_Frame
 @Extends Rectangle2D.Double
 @Implements AdvancedShape
 **/
public class AdvancedRectangle extends Rectangle2D.Double implements AdvancedShape {
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx;
    double starty;

    /**
     * AdvancedRectangle draws a rectangle at the x and y parameters, with the width (w) and height (h)
     * parameters specified
     * @param x x-coordinate start point
     * @param y y-coordinate start point
     * @param w width of rectangle
     * @param h height of rectangle
     */
    public AdvancedRectangle(double x, double y, double w, double h) {

        super(x, y, w, h);
        this.startx = x;
        this.starty = y;
    }
    //adds the starting x and y coordinates
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startx);
        listA.add(starty);
        return listA;
    }
    //returns the current fill color
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * sets the fill color when changed
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        this.isTransparent = false;
    }

    /**
     *     returns the current pen color
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     * sets the pen color
     * @param penColor
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     * returns if the rectangle is transparent or not
     * @return
     */
    public boolean isTransparent() {
        return isTransparent;
    }

    /**
     * sets the transparency status of the class
     * @param transparent
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
        this.fillColor = null;
    }

    /**
     * updates the size of the rectangle
     * @param x
     * @param y
     */
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

    /**
     * renders the rectangle depending on transparency
     * @param g2d
     */
    public void render(Graphics2D g2d){
        if (!this.isTransparent()){
            g2d.setPaint(this.getFillColor());
            g2d.fill(this);
            g2d.setPaint(this.getPenColor());
        }
        g2d.draw(this);
    }

    /**
     * returns the string type of the shape and size
     * @param screenWidth width of the GUI window
     * @param screenHeight height of the GUI window
     * @return string
     */
    public String toString(int screenWidth, int screenHeight) {
        return String.format(
                "RECTANGLE %f %f %f %f",
                this.x / screenWidth,
                this.y / screenHeight,
                (this.x + this.width) / screenWidth,
                (this.y + this.height) / screenHeight);
    }

    /**
     * updates the scale of the rectangle when the GUI window is being dragged
     * @param screenWidthDiffPercent difference in screen width after resizing the GUI window
     * @param screenHeightDiffPercent difference in screen height after resizing the GUI window
     */
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x -= x * screenWidthDiffPercent;
        this.y -= y * screenHeightDiffPercent;
        this.width -= width * screenWidthDiffPercent;
        this.height -= height * screenHeightDiffPercent;
    }
}
