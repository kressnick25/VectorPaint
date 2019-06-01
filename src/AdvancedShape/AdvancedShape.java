package AdvancedShape;

import java.awt.*;

public interface AdvancedShape extends Shape {
    /**
     * returns the string type of the shape and size
     * @param screenWidth width of the GUI window
     * @param screenHeight height of the GUI window
     * @return string in VEC format eg: (RECTANGLE 0.1 0.2 0.3 0.4)
     */
    String toString(int screenWidth, int screenHeight);

    /**
     * Draws the Plot with color to a Graphics2D object
     * @param g2d Graphics2D object to draw to
     */
    void render(Graphics2D g2d);

    /**
     *
     * @param color set the fill to a new Color
     */
    void setFillColor(Color color);

    /**
     *
     * @return get current fill Color
     */
    Color getFillColor();

    /**
     *
     * @param color set the pen to a new Color
     */
    void setPenColor(Color color);

    /**
     *
     * @return get the current pen Color
     */
    Color getPenColor();

    /**
     * Updates the size of the AdvancedShape to new width and height
     * @param x new width in pixels
     * @param y new height in pixels
     */
    void updateSize(int x, int y);

    /**
     *
     * @return return true if object is transparent, false if not
     */
    boolean isTransparent();

    /**
     *
     * @param transparent set object transparency true/false
     */
    void setTransparent(boolean transparent);

    /**
     * updates the scale of the AdvancedShape when the GUI window is being dragged
     * @param screenWidthDiffPercent difference in screen width after resizing the GUI window
     * @param screenHeightDiffPercent difference in screen height after resizing the GUI window
     */
    void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent);
}
