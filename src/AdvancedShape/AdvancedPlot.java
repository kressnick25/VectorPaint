package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AdvancedPlot extends Ellipse2D.Double implements AdvancedShape{

    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);

    /**
     * Contstruct a new plot at co-ords
     * @param x x pixel co-ord
     * @param y y pixel co-ord
     */
    public AdvancedPlot(double x, double y) {
        super(x, y, 5, 5);
    }

    /**
     *
     * @return get the current fill Color
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     *
     * @param fillColor set the fill to a new Color
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     *
     * @return get the current pen Color
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     *
     * @param penColor set the pen to a new Color
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     *
     * @return return false, because plot cannot be transparent
     */
    public boolean isTransparent() {
        return false;
    }

    /**
     *
     * @param transparent do nothing, because plot cannot be transparent
     */
    public void setTransparent(boolean transparent) {}

    /**
     * Do nothing, as plot size is constant
     * @param x -
     * @param y -
     */
    public void updateSize(int x, int y) {}

    /**
     * Draws the Plot with color to a Graphics2D object
     * @param g2d Graphics2D object to draw to
     */
    public void render(Graphics2D g2d){
        g2d.setPaint(this.getPenColor());
        g2d.fill(this);
    }

    /**
     * Outputs the Line in VEC format
     * @param screenWidth current width of window
     * @param screenHeight current height of window
     * @return a string in VEC formatting eg: (PLOT 0.1 0.1 0.6 0.7)
     */
    public String toString(int screenWidth, int screenHeight){
        return String.format(
                "PLOT %f %f",
                this.getX() / screenWidth,
                this.getX() / screenHeight);
    }

    /**
     * Updates the size of the line based on the size of the window
     * @param screenWidthDiffPercent % difference as a decimal between old -> new window width.
     * @param screenHeightDiffPercent % difference as a decimal between old -> new window height.
     */
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        this.x -= x * screenWidthDiffPercent;
        this.y -= y * screenHeightDiffPercent;
    }
}

