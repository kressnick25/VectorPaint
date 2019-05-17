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
        shapes.add(new Rectangle2D.Double(1, 1, 100, 100));
        shapes.add(new Rectangle2D.Double(100, 100, 100, 100));
        shapes.add(new Rectangle2D.Double(200, 200, 100, 100));
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
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
    public void undo(){
        shapes.remove(shapes.size() - 1);
    }

    public void clear(){
        shapes.clear();
    }

}
