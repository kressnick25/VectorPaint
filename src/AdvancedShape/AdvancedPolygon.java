package AdvancedShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.StringJoiner;

public class AdvancedPolygon extends java.awt.Polygon implements AdvancedShape {
    private Color penColor = new Color(0, 0, 0);
    private Color fillColor = new Color(255,255,255);
    private boolean isTransparent = false;
    double startx;
    double starty;

    /**
     * Construct a new AdvancedPolygon with points
     * @param xpoints an int array of x co-ords
     * @param ypoints an int array of y co-ords
     * @param npoints the length of either xpoints or y points
     */
    public AdvancedPolygon(int[] xpoints, int[] ypoints, int npoints) {
            super(xpoints, ypoints, npoints);
        this.startx = xpoints[0];
        this.starty = ypoints[0];
    }
    /**
     * Construcs a new empty AdvancedPolygon
     */
    public AdvancedPolygon() {
    }
    public ArrayList startGetter(){
        ArrayList listA = new ArrayList();
        listA.add(startx);
        listA.add(starty);
        return listA;
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
        this.isTransparent = false;
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
     * @return true if object is transparent, false if not
     */
    public boolean isTransparent() {
        return isTransparent;
    }
    /**
     *
     * @param transparent set transparency boolean
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
        this.fillColor = null;
    }
    /**
     * Adds a new point to the Advanced Polygon
     * @param x new point x pixel co-ord
     * @param y new point y pixel co-ord
     */
    public void updateSize(int x, int y){
        this.addPoint(x, y);
    }
    /**
     * Draws the Plot with color to a Graphics2D object
     * @param g2d Graphics2D object to draw to
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
     * Outputs the Line in VEC format
     * @param screenWidth current width of window
     * @param screenHeight current height of window
     * @return a string in VEC formatting eg: (POLYGON 0.1 0.1 0.6 0.7 0.4 0.3 0.1 0.5)
     */
    public String toString(int screenWidth, int screenHeight){
        // use stringJoiner to create string with space after each addShape
        StringJoiner outString = new StringJoiner(" ");
        outString.add("POLYGON");
        for (int i=0; i < this.xpoints.length; i++){
            double x = (double) xpoints[i] / screenWidth;
            double y = (double) ypoints[i] / screenHeight;
            // ignore case where == 0.0, 0.0
            if (x != 0.0 || y != 0.0){
                outString.add( Double.toString(x) );
                outString.add( Double.toString(y) );
            }
        }
        return outString.toString();
    }
    /**
     * Updates the size of the line based on the size of the window
     * @param screenWidthDiffPercent % difference as a decimal between old -> new window width.
     * @param screenHeightDiffPercent % difference as a decimal between old -> new window height.
     */
    public void updateScale(double screenWidthDiffPercent, double screenHeightDiffPercent){
        for (int i = 0; i < npoints; i++) {
            xpoints[i] -= (double)xpoints[i] * screenWidthDiffPercent;
            ypoints[i] -= (double)ypoints[i] * screenHeightDiffPercent;
        }
    }
}
