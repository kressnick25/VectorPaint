import AdvancedShape.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicsCanvas extends JPanel {
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();

    public GraphicsCanvas() {
        setSize(500, 500);
        setVisible(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        shapes.forEach((shape) -> {
                    shape.render(g2d);
                }
        );
    }

    public ArrayList<AdvancedShape> getShapes() { return this.shapes; }

    public void load(ArrayList<AdvancedShape> shapes){
        this.shapes = shapes;
    }

    public void add(AdvancedShape shape){
       shapes.add(shape);
    }

    //remove last element in list
    // TODO convert use datatype that natively supports this
    public void clearLast(){
        if (shapes.size() != 0) {
            shapes.remove(shapes.size() - 1);
        }
    }

    public void clear(){
        shapes.clear();
    }

    public Shape getLatest() {
        if(shapes.size() == 0) return null;
        return shapes.get(shapes.size() - 1);
    }


}
