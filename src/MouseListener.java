import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MouseListener extends MouseInputAdapter {
    private Shape shape;
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
                    // TODO
                    break;
                case Rectangle:
                    shape = new Rectangle2D.Double(x, y, 0, 0);
                    break;
                case Ellipse:
                    shape = new Ellipse2D.Double(x, y, 0, 0);
                    break;
                case Line:
                    // initalise line with no length. current pos to current pos
                    shape = new Line2D.Double(x, x, y, y);
                    break;
                case Polygon:
                    shape = new Polygon();
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
        mouseUpdateSelector(x, y);
        display.clearLast();
        display.add(shape);
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        mouseUpdateSelector(x, y);
        display.add(shape);
        shape = null;
    }
    void mouseUpdateSelector(int x, int y){
        switch (type){
            case Rectangle:
                updateRectangleSize(x, y);
                break;
            case Ellipse:
                updateEllipseSize(x ,y);
                break;
            case Line:
                updateLine(x, y);
                break;
            case Polygon:
                updatePolygon(x, y);
                break;
            case Plot:
                // TODO;
                break;
            // TODO default exception
        }
    }
    void updateRectangleSize(int x, int y){
        Rectangle2D.Double rect = (Rectangle2D.Double) shape;
        double width = x - rect.getX();
        double height = y - rect.getY();
        rect.setRect(rect.getX(), rect.getY(), width, height );

        shape = rect;
    }

    void updateEllipseSize(int x, int y){
        Ellipse2D.Double ell = (Ellipse2D.Double) shape;
        double width = x - ell.getX();
        double height = y - ell.getY();
        ell.setFrame(ell.getX(), ell.getY(), width, height);

        shape = ell;
    }

    void updateLine(int x, int y){
        Line2D.Double line = (Line2D.Double) shape;
        line.setLine(line.getX1(), x, line.getY1(), y);

        shape = line;
    }

    void updatePolygon(int x, int y){
        Polygon poly = (Polygon) shape;
        poly.addPoint(x, y);

        shape = poly;
    }


}
