package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Shape AdvancedEllipse, initiate a color, fill color, and transparency.
 * The shape Ellipse can be drawn and passed into the GUI_Frame
 */
public class AdvancedEllipse extends Ellipse2D.Double implements AdvancedShape{
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx;
    double starty;

    /**
     * AdvancedEllipse draws a Ellipse at the x and y parameters, with the width (w) and height (h)
     * parameters specified
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public AdvancedEllipse(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.startx = x;
        this.starty = y;
    }
    /**
     * adds the starting x and y coordinates
     * @return
     */
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startx);
        listA.add(starty);
        return listA;
    }

    /**
     * returns the current fill color
     * @return
     */
    public Color getFillColor() {
        return fillColor;
    }
    /**
     * sets the fill color when changed
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        if (fillColor == null){
            this.isTransparent = true;
        }
        else {
            this.fillColor = fillColor;
            this.isTransparent = false;
        }

    }
    /**
     * returns the current pen color
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
     * returns if the ellipse is transparent or not
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
     * updates the size of the ellipse
     * @param x
     * @param y
     */
    public void updateSize(int x, int y) {
        double width = 0;
        double height = 0;

        if (startx >= x && starty >= y) {
            width = startx - x;
            height = starty - y;
            this.setFrame(x, y, width, height);
        } else if (startx >= x) {
            width = startx - x;
            height = y - this.getY();
            this.setFrame(x, this.getY(), width, height);
        } else if (starty >= y) {
            width = x - this.getX();
            height = starty - y;
            this.setFrame(this.getX(), y, width, height);
        } else {

            width = x - this.getX();
            height = y - this.getY();
            this.setFrame(this.getX(), this.getY(), width, height);
        }
    }
    /**
     * renders the ellipse depending on transparency
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
                "ELLIPSE %f %f %f %f",
                this.x / screenWidth,
                this.y / screenHeight,
                (this.x + this.width) / screenWidth,
                (this.y + this.height) / screenHeight);
    }
    /**
     * updates the scale of the ellipse when the GUI window is being dragged
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
