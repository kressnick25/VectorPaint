package AdvancedShape;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Shape AdvancedLine, initiate a color, fill color, and transparency.
 * The shape Line can be drawn and passed into the GUI_Frame
 */
public class AdvancedLine extends Line2D.Double implements AdvancedShape {
    private Color fillColor = new Color(255,255,255);
    private Color penColor = new Color(0, 0, 0);

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
    }

    /**
     * Updates the size of the line based on the size of the window
     * @param screenWidthDiffPercent % difference as a decimal between old -> new window width.
     * @param screenHeightDiffPercent % difference as a decimal between old -> new window height.
     */
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x1 -= x1 * screenWidthDiffPercent;
        this.y1 -= y1 * screenHeightDiffPercent;
        this.x2 -= x2 * screenWidthDiffPercent;
        this.y2 -= y2 * screenHeightDiffPercent;
    }

    /**
     *
     * @return gets the current fill Color
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     *
     * @param fillColor sets fill to a new Color
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     *
     * @return the current Color of the pen
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     *
     * @param penColor sets the pen to a new Color
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     *
     * @return always false because Line cannot be transparent
     */
    public boolean isTransparent() {
        return false;
    }

    /**
     *
     * @param transparent does nothing, because Line cannot be transparent
     */
    public void setTransparent(boolean transparent){}

    /**
     * Updates the size of the line with new end co-ords
     * @param x X pixel at end of line
     * @param y Y pixel at end of line
     */
    public void updateSize(int x, int y){
        this.setLine(this.getX1(), this.getY1(), x, y);
    }

    /**
     * Draws the with color Line to a Graphics2D object
     * @param g2d Graphics2D object to draw to
     */
    public void render(Graphics2D g2d){
        g2d.setPaint(this.getPenColor());
        g2d.draw(this);
    }

    /**
     * Outputs the Line in VEC format
     * @param screenWidth current width of window
     * @param screenHeight current height of window
     * @return a string in VEC formatting eg: (LINE 0.1 0.1 0.6 0.7)
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
