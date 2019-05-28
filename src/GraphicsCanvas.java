import AdvancedShape.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

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
    public ArrayList<AdvancedShape> getShapes() { return this.shapes; }

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
        this.shapes = shapes;
    }

    public void add(AdvancedShape shape){
        shapes.add(shape);
        //updateComboBox();

    }

    //remove last element in list
    // TODO convert use datatype that natively supports this
    public void clearLast(){
        if (shapes.size() != 0) {
            shapes.remove(shapes.size() - 1);
        }
        //updateComboBox();
    }

    public void clear(){
        shapes.clear();
        updateComboBox();
    }

    public Shape getLatest() {
        if (!shapes.isEmpty()) {
            if (shapes.size() == 0) {
                return shapes.get(shapes.size());
            }
            return shapes.get(shapes.size() - 1);
        }
        return null;
    }

    public void updateScale(int newScreenWidth, int previousScreenWidth, int newScreenHeight, int previousScreenHeight){
        double screenWidthDiffPercent = ((double)previousScreenWidth - (double)newScreenWidth) / (double)previousScreenWidth;
        double screenHeightDiffPercent = ((double)previousScreenHeight - (double)newScreenHeight) / (double)previousScreenHeight;
        for (AdvancedShape s : shapes){
            s.updateScale(screenWidthDiffPercent, screenHeightDiffPercent);
        }
    }


}
