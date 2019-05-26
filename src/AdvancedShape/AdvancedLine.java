package AdvancedShape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Shape AdvancedLine, initiate a color, fill color, and transparency.
 * The shape Line can be drawn and passed into the GUI_Frame
 */
public class AdvancedLine extends Line2D.Double implements AdvancedShape {
    private Color fillColor = new Color(255,255,255);
    private Color penColor = new Color(0, 0, 0);
    private boolean isTransparent = true;
    double startX1;
    double startY1;
    double endX2;
    double endY2;

    /**
     * AdvancedLine draws a Line at the x1 and y1 starting coordinates
     * then an end x2 and y2 coordinates
     * @param x1 start x coordinate
     * @param y1 start y coordinate
     * @param x2 end x coordinate
     * @param y2 end y coordinate
     */
    public AdvancedLine(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
        this.startX1 = x1;
        this.startY1 = y1;
        this.endX2 = x2;
        this.endY2 = y2;
    }

    /**
     * updates the size of the line based on the size of the screen
     * @param screenWidthDiffPercent
     * @param screenHeightDiffPercent
     */
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x1 -= x1 * screenWidthDiffPercent;
        this.y1 -= y1 * screenHeightDiffPercent;
        this.x2 -= x2 * screenWidthDiffPercent;
        this.y2 -= y2 * screenHeightDiffPercent;
    }

    /**
     * used for testing the constructor
     * gets array of start and end points of line
     * @return
     */
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startX1);
        listA.add(startY1);
        listA.add(endX2);
        listA.add(endY2);
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
        this.fillColor = fillColor;
    }

    /**
     * returns the current pen color
     * @return
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

    }
    /**
     * updates the size of the Line
     * @param x
     * @param y
     */
    public void updateSize(int x, int y){
        this.setLine(this.getX1(), this.getY1(), x, y);
    }
    /**
     * renders the Line depending on transparency
     * @param g2d
     */
    public void render(Graphics2D g2d){
        g2d.setPaint(this.getPenColor());
        g2d.draw(this);
    }

    /**
     * returns the string type of the shape and starting and ending coordinates
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public String toString(int screenWidth, int screenHeight) {
        //converts location details to string
        return String.format(
                "LINE %f %f %f %f",
                this.x1 / screenWidth,
                this.y1 / screenHeight,
                this.x2 / screenWidth,
                this.y2 / screenHeight);
    }
}
