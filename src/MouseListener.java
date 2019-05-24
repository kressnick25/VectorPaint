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
    private Color fillColor = new Color(255, 255, 255);
    private Color penColor = new Color(0,0,0);

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
                    shape = new AdvancedPlot(x, y);
                    break;
                case Rectangle:
                    shape = new AdvancedRectangle(x, y, 0, 0);
                    break;
                case Ellipse:
                    shape = new AdvancedEllipse(x, y, 0, 0);
                    break;
                case Line:
                    // initialise line with no length. current pos to current pos
                    shape = new AdvancedLine(x, y, x, y);
                    break;
                case Polygon:
                    if (!(shape instanceof AdvancedPolygon)) {
                        shape = new AdvancedPolygon();
                        display.add(shape);
                    }
                    break;
                default:
                    throw new Exception("Invalid shape type.");
            }
            shape.setFillColor(fillColor);
            shape.setPenColor(penColor);
            if (!(shape instanceof AdvancedPolygon)){
                display.add(shape);
            }
        } catch (Exception e1){
            // TODO popup
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (!(shape instanceof AdvancedPolygon) && !(shape instanceof AdvancedPlot)) {
            int x = e.getX();
            int y = e.getY();
            int width = display.getSize().width;
            int height = display.getSize().height;

            if(x > width && y > height) {
                shape.updateSize(width - 5 , height - 5);
                display.clearLast();
                display.add(shape);
            }
            else if(x > width) {
                shape.updateSize(width - 5 , y);
                display.clearLast();
                display.add(shape);
            }
            else if(y > height) {
                shape.updateSize(x, height - 5);
                display.clearLast();
                display.add(shape);
            }
            else{
                shape.updateSize(x , y);
                display.clearLast();
                display.add(shape);
            }



        }
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = display.getSize().width;
        int height = display.getSize().height;
        if(x > width && y > height) {
            shape.updateSize(width - 5 , height - 5);
            display.clearLast();
            display.add(shape);
        }
        else if(x > width) {
            shape.updateSize(width - 5 , y);
            display.clearLast();
            display.add(shape);
        }
        else if(y > height) {
            shape.updateSize(x, height - 5);
            display.clearLast();
            display.add(shape);
        }
        else{
            shape.updateSize(x , y);
            display.clearLast();
            display.add(shape);
        }
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

}
