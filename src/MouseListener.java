import AdvancedShape.*;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MouseListener extends MouseInputAdapter {
    private AdvancedShape shape;
    // default as plot
    private ShapeType type = ShapeType.Plot;
    private GraphicsCanvas display;
    private Color fill;
    private Color pen;

    public void setType(ShapeType type) {
        this.type = type;
    }

    public void setCanvas(GraphicsCanvas gc){
        this.display = gc;
    }
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        try {
            switch (type) {
                case Plot:
                    shape = new AdvancedEllipse(x, y, 5, 5);
                    display.add(shape);
                    shape = null;
                    break;
                case Rectangle:
                    shape = new AdvancedRectangle(x, y, 0, 0);
                    break;
                case Ellipse:
                    shape = new AdvancedEllipse(x, y, 0, 0);
                    break;
                case Line:
                    // initalise line with no length. current pos to current pos
                    shape = new AdvancedLine(x, y, x, y);
                    break;
                case Polygon:
                    shape = new AdvancedPolygon();
                    break;
                default:
                    throw new Exception("Invalid shape type.");
            }
        } catch (Exception e1){
            // TODO popup
        }
    }

    public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            shape.updateSize(x, y);
            display.clearLast();
            display.add(shape);
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        shape.updateSize(x ,y);
        display.add(shape);
        shape = null;
    }
}
