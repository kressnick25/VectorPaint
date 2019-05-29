package AdvancedShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Shape AdvancedEllipse, initiate a color, fill color, and transparency.
 * The shape Ellipse can be drawn and passed into the GUI_Frame
 */
public class AdvancedEllipse extends Ellipse2D.Double implements AdvancedShape{
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    private double startx;
    private double starty;

    /**
     * Construct a new AdvancedEllipse with position
     * @param x the x-coordinate of the top left corner
     * @param y the y-coordinate of the top left corder
     * @param w inital width
     * @param h initial height
     */
    public AdvancedEllipse(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.startx = x;
        this.starty= y;
    }

    /**
     *
     * @return gets the current fill color.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     *
     * @param fillColor sets the current fill color
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
     *
     * @return get the current pen Color
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     *
     * @param penColor set the pen color to a new Color
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     *
     * @return true if transparent, false if not
     */
    public boolean isTransparent() {
        return isTransparent;
    }

    /**
     *
     * @param transparent set the transparency true/false
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
        this.fillColor = null;
    }
    /**
     * Updates the size of the ellipse wth new co-ordinates
     * @param x new X co-ord
     * @param y new Y co-ord
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
     * Draws to the ellipse a graphics canvas, applying fill and pen Color.
     * @param g2d the Graphics2D object to draw to.
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
     * Outputs a string for VEC formatting.
     * @param screenWidth current width in pixels of the GUI window
     * @param screenHeight current height in pixels of the GUI window
     * @return string a String in VEC format eg: (ELLIPSE 0.2 0.1 0.4 0.1)
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
     * Updates the scale of the ellipse when the GUI window is being dragged
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
