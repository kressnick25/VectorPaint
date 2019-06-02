import AdvancedShape.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
/**
 * GraphicsCanvas is an extended JPanel that draws an array of AdvancedShapes to the Panel.
 */
public class GraphicsCanvas extends JPanel {
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();
    private JComboBox comboBox;

    public void updateComboBox(){
        ArrayList<String> reversedList = this.toStringArray();
        Collections.reverse(reversedList);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new Vector<>(reversedList));
        comboBox.setModel(model);
    }
    public GraphicsCanvas() {
        setSize(500, 500);
        setVisible(true);
    }
    /**
     * Re-render the current AdvancedShape array to the canvas
     * @param g a Graphics object
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        // Anti-Aliasing Setting, turn off for better performance
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
        shapes.forEach((shape) -> {
                    shape.render(g2d);
                }
        );
    }
    public void setComboBox(JComboBox comboBox){
        this.comboBox = comboBox;
    }
    /**
     *
     * @return the current AdvancedShapes in the canvas
     */
    public ArrayList<AdvancedShape> getShapes() { return this.shapes; }
    /**
     * Outputs an String List formatting every shape in the current array as [INDEX SHAPETYPE].
     * Used for formatting to the UndoHistory ComboBox
     * @return ArrayList of Strings
     */
    public ArrayList<String> toStringArray() {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<AdvancedShape> sh = this.getShapes();
        for (AdvancedShape s: sh){
            // split string into just shape type
            String[] shapeParts = s.toString(1, 1).split(" ");
            // Format shape with leading index number
            list.add(shapes.indexOf(s)+ " " + shapeParts[0]);
        }
        //String[] stringArray = list.toArray(new String[0]);
        return list;
    }

    public void load(ArrayList<AdvancedShape> shapes){
        for (AdvancedShape s : shapes){
            this.shapes.add(s);
        }
    }

    public void add(AdvancedShape shape){
        shapes.add(shape);
        System.out.println(shapes);
        //updateComboBox();

    }
    /**
     * Removes all shapes from canvas with index greater than provided index.
     * Then updates the comboBox to reflect changes.
     * @param index the index of the array to trim to
     */
    public void trimToIndex(int index){
        shapes.subList(index, shapes.size()).clear();
        updateComboBox();

    }
    /**
     * Remove the last/ most recently added shape from the canvas.
     * Update the comboBox to reflect changes.
     */    public void clearLast(){
        if (shapes.size() != 0) {
            shapes.remove(shapes.size() - 1);
        }
        updateComboBox();
    }
    /**
     * Clear the canvas of all shapes.
     */
    public void clear(){
        shapes.clear();
        updateComboBox();
    }
    /**
     *
     * @return the latest/ most recently added AdvancedShape
     */
    public Shape getLatest() {
        if (!shapes.isEmpty()) {
            if (shapes.size() == 0) {
                return shapes.get(shapes.size());
            }
            return shapes.get(shapes.size() - 1);
        }
        return null;
    }
    /**
     * Used to scale shapes with window on resize.
     * Calculates the percentage scaling difference between and applies scale to all shapes in canvas.
     */
    public void updateScale(int newScreenWidth, int previousScreenWidth, int newScreenHeight, int previousScreenHeight){
        double screenWidthDiffPercent = ((double)previousScreenWidth - (double)newScreenWidth) / (double)previousScreenWidth;
        double screenHeightDiffPercent = ((double)previousScreenHeight - (double)newScreenHeight) / (double)previousScreenHeight;
        for (AdvancedShape s : shapes){
            s.updateScale(screenWidthDiffPercent, screenHeightDiffPercent);
        }
    }


}
