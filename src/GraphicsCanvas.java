import component.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicsCanvas extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public GraphicsCanvas() {
        setSize(500, 500);
        setVisible(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        shapes.forEach((shape) -> g2d.draw(shape));
    }

    public ArrayList<Shape> getShapes() { return this.shapes; }

    public void load(ArrayList<Shape> shapes){
        this.shapes = shapes;
    }

    public void add(Shape shape){
        shapes.add(shape);
    }

    //remove last element in list
    // TODO convert use datatype that natively supports this
    public void clearLast(){
        shapes.remove(shapes.size() - 1);
    }

    public void clear(){
        shapes.clear();
    }

}
