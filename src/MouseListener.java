import AdvancedShape.*;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.round;

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
        int width = display.getSize().width;
        int height = display.getSize().height;
        double intervalX =  width * (0.5*10)/100;
        double intervalY =  height * (0.5*10)/100;
        double roundOffX = intervalX*(Math.round(x/intervalX));
        double roundOffY = intervalY*(Math.round(y/intervalY));
        try {
            switch (type) {
                case Plot:
                    shape = new AdvancedPlot(roundOffX, roundOffY);
                    break;
                case Rectangle:
                    shape = new AdvancedRectangle(roundOffX, roundOffY, 0, 0);
                    break;
                case Ellipse:
                    shape = new AdvancedEllipse(roundOffX, roundOffY, 0, 0);
                    break;
                case Line:
                    // initialise line with no length. current pos to current pos
                    shape = new AdvancedLine(roundOffX, roundOffY, roundOffX, roundOffY);
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
            else if(x < 0 && y > height){
                shape.updateSize(5, height-5);
                display.clearLast();
                display.add(shape);
            }
            else if(x > width && y < 0){
                shape.updateSize(width-5, 5);
                display.clearLast();
                display.add(shape);
            }
            else if(x < 0 && y < 0) {
                shape.updateSize(2,2);
                display.clearLast();
                display.add(shape);
            }
            else if(x < 0){
                shape.updateSize(2, y);
                display.clearLast();
                display.add(shape);

            }
            else if(y < 0){
                shape.updateSize(x, 2);
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
        double intervalX =  width * (0.5*10)/100;
        double intervalY =  height * (0.5*10)/100;
        double roundOffX = intervalX*(Math.round(x/intervalX));
        double roundOffY = intervalY*(Math.round(y/intervalY));
        shape.updateSize((int)roundOffX , (int)roundOffY);
        display.clearLast();
        display.add(shape);

        if(x > width && y > height) {
            shape.updateSize(width - 5 , height - 5);
            display.clearLast();
            display.add(shape);
        }
        else if(x < 0 && y < 0) {
            shape.updateSize(2,2);
            display.clearLast();
            display.add(shape);
        }
        else if(x > width && y < 0){
            shape.updateSize(width-5, 5);
            display.clearLast();
            display.add(shape);
        }
        else if(x < 0 && y > height){
            shape.updateSize(5, height-5);
            display.clearLast();
            display.add(shape);
        }
        else if(x < 0){
            shape.updateSize(2, (int)roundOffY);
            display.clearLast();
            display.add(shape);

        }
        else if(y < 0){
            shape.updateSize((int)roundOffX, 2);
            display.clearLast();
            display.add(shape);
        }
        else if(x > width) {
            shape.updateSize(width - 5 , (int)roundOffY);
            display.clearLast();
            display.add(shape);
        }
        else if(y > height) {
            shape.updateSize((int)roundOffX, height - 5);
            display.clearLast();
            display.add(shape);
        }
        else{
            shape.updateSize((int)roundOffX , (int)roundOffY);
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
