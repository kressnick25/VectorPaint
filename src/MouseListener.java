import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MouseListener extends MouseInputAdapter {
    private Shape shape;
    private ShapeType type;
    private Color fill;
    private Color pen;

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }
    public void setType(ShapeType type) {
        switch(type) {
            case Ellipse:
                this.shape = new Ellipse2D.Float();
                break;
            case Line:
                this.shape = new Line2D.Float();
                break;
            case Rectangle:
                this.shape = new Rectangle2D.Float();
                break;
            case Polygon:
                this.shape = new Polygon();
                break;
        }
    }

}
