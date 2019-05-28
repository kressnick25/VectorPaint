import AdvancedShape.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MouseListener extends MouseInputAdapter {
    private AdvancedShape shape;
    // default as plot
    private ShapeType type = ShapeType.Plot;
    private GraphicsCanvas display;
    private Color fillColor = new Color(255, 255, 255);
    private Color penColor = new Color(0,0,0);
    //selected grid interval
    private double intervalUserX = 0;
    private double intervalUserY = 0;

    public void setType(ShapeType type) {
        //type of shape
        this.type = type;
    }

    public void setCanvas(GraphicsCanvas gc){
        //sets canvas
        this.display = gc;
    }

    public void mousePressed(MouseEvent e) {
        //Gets x and y coords of mouse
        int x = e.getX();
        int y = e.getY();
        //gets width and height of display
        int width = display.getSize().width;
        int height = display.getSize().height;
        //selected user interval
        double intervalX;
        double intervalY;
        //checks if user inputted grid interval
        if(intervalUserX != 0){
            //converts interval to pixels
            intervalX =  width * (intervalUserX*10)/100;
            intervalY =  height * (intervalUserY*10)/100;
        }
        else{
            intervalX = x;
            intervalY = y;
        }
        //rounds x and y to nearest interval factor

        double roundOffX = intervalX*(Math.round(x/intervalX));
        double roundOffY = intervalY*(Math.round(y/intervalY));
        //type of shape being draw, creates
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
            //sets fill and pen colors
            shape.setFillColor(fillColor);
            shape.setPenColor(penColor);
            if (!(shape instanceof AdvancedPolygon)){
                display.add(shape);
            }
        } catch (Exception e1){
            // TODO popup
        }
    }
    public void setInterval(double interval){
        //sets grid interval
        this.intervalUserX = interval*10;
        this.intervalUserY = interval*10;
    }
    public void mouseDragged(MouseEvent e) {
        //check if shape is not polygon or plot
        if (!(shape instanceof AdvancedPolygon) && !(shape instanceof AdvancedPlot)) {
            int x = e.getX();
            int y = e.getY();
            int width = display.getSize().width;
            int height = display.getSize().height;
            //cannot draw off screen
            //Checks all possible scenarios of mouse off screen and sets details to stay inside screen
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
        double intervalX;
        double intervalY;
        double roundOffX;
        double roundOffY;
        //checks user wants grid
        if(intervalUserX != 0){
            //sets interval and rounds x and y to nearest interval
            intervalX =  width * (intervalUserX*10)/100;
            intervalY =  height * (intervalUserY*10)/100;
            roundOffX = intervalX*(Math.ceil(x/intervalX));
            roundOffY = intervalY*(Math.ceil(y/intervalY));
        }
        else{
            //if no grid selected sets x and y to mouse location
            roundOffX = x;
            roundOffY = y;
        }
        //checks all possible variations of mouse being off screen and sets x, y, width and height of shape
        //to stay on screen

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
        System.out.println("Before combo:" + display.getSize().width + " , " + display.getSize().height);
        display.updateComboBox();
        System.out.println("After combo:" + display.getSize().width + " , " + display.getSize().height);

    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

}
