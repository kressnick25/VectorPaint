import AdvancedShape.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;

public class Vec{
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();
    private String filename;
    private int WIDTH = 1250;
    private int HEIGHT = 1000;

    private Color recentPenColor = Color.black;
    private Color recentFillColor = Color.white;

    /**
     * Construct new Vec class to read data from file.
     * @param filename String file
     */
    public Vec(String filename){
        this.filename = filename;
    }

    /**
     * Construct new Vec class to write current data to file.
     * @param filename
     * @param shapes
     */
    public Vec(String filename, ArrayList<AdvancedShape> shapes){
        this.filename = filename;
        this.shapes = new ArrayList<AdvancedShape>(shapes);
    }

    /**
     * Saves the images in the GUI frame as a vector Point.
     * takes no parameters
     */
    public void save(){
        try {
            // Open file
            FileWriter file = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(file);

            // Write each command, print adds newline
            shapes.forEach((a) -> writer.printf(this.parseShapeToString(a) + "\n"));

            //close file connection
            writer.close();

            //error catch
        } catch (Exception e){
            // Print exception
            System.err.format("Error trying to write to '%s'.", filename);
            e.printStackTrace();
        }
    }

    /**
     * Reads from filename stream to private variable;
     */
    public void read(){
        ArrayList<String> lines = readLinesFromFile();
        System.out.print(lines);
        try {
            this.shapes = parseLinesToShapes(lines);
            System.out.print(shapes);
        } catch(Exception e) {
            // TODO popup window here
        }
    }

    /**
     * Reads from filename and stores all the line locations in an ArrayList.
     * @Return ArrayList
     */
    private ArrayList<String> readLinesFromFile() {
        ArrayList<String> lines = new ArrayList<>();

        try {
            // Open file to new reader
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            // For each line, add to lines queue
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            // Close file connection
            reader.close();
        }
        catch (Exception e){
            // TODO popup window here
            // Print exception
            System.err.format("Error trying to read '%s'.", filename);
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Takes the shape type and turns it into the corresponding string of the shape name.
     * if no shape is selected a message is produced
     * @param shape
     * @return String
     */

    private String parseShapeToString(AdvancedShape shape) {
        Color penColor = shape.getPenColor();
        Color fillColor = shape.getFillColor();
        // referenced from
        // https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal-string
        String penHex = String.format("#%02x%02x%02x", penColor.getRed(), penColor.getGreen(), penColor.getBlue());
        String fillHex = String.format("#%02x%02x%02x", fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());

        //rectangle
        String prefixShapeString = String.format("PEN %s\nFILL %s\n", penHex, fillHex);
        if (shape instanceof Rectangle2D.Double) {
            Rectangle2D.Double rectangle = (Rectangle2D.Double) shape;
            return String.format("%sRECTANGLE %f %f %f %f",prefixShapeString, rectangle.x / WIDTH, rectangle.y / HEIGHT, rectangle.width / WIDTH, rectangle.height / HEIGHT);
        }
        //line
        if (shape instanceof Line2D.Double) {
            Line2D.Double line = (Line2D.Double) shape;
            return String.format("%sLINE %f %f %f %f", prefixShapeString, line.x1 / WIDTH, line.y1 / HEIGHT, line.x2 / WIDTH, line.y2 / HEIGHT);
        }
        // ellipse
        if (shape instanceof Ellipse2D.Double) {
            Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
            return String.format("%sELLIPSE %f %f %f %f", prefixShapeString, ellipse.x / WIDTH, ellipse.y / HEIGHT, ellipse.width / WIDTH, ellipse.height / HEIGHT);
        }
        //error, none of the above
        System.out.println(String.format("Unsupported type: %s", shape.getClass().toString()));

        return null;
    }

    // TODO more specific exception
    // TODO test components against Type enum
    // TODO add javadoc comment above method once complete
    private ArrayList<AdvancedShape> parseLinesToShapes(ArrayList<String> lines) throws Exception{
        ArrayList<AdvancedShape> shapes = new ArrayList<AdvancedShape>();
        // Parse each component in line to local vars
        for (String line : lines){
            // split line into shapes ie PLOT, 0.0, 0.1
            String[] components = line.split("\\s+"); // TODO arrayList?
            // Initialise new object from vars
            switch(components[0]){
                // Use brackets in switch statement to manage scope
                case "RECTANGLE": {// TODO convert to comparison to enum
                    Double xOne = Double.parseDouble( components[1] ) * WIDTH;
                    Double yOne = Double.parseDouble( components[2] ) * HEIGHT;
                    Double xTwo = Double.parseDouble( components[3] ) * WIDTH;
                    Double yTwo = Double.parseDouble( components[4] ) * HEIGHT;
                    AdvancedShape nShape = new AdvancedRectangle(xOne, yOne, xTwo, yTwo );
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
//                case "PLOT": {
//                    VectorPoint point1 = parsePoint(components[1], components[2]);
//                    shapes.add( new Plot(point1) );
//                    break;
//                }
                case "LINE": {
                    Double xOne = Double.parseDouble( components[1] ) * WIDTH;
                    Double yOne = Double.parseDouble( components[2] ) * HEIGHT;
                    Double xTwo = Double.parseDouble( components[3] ) * WIDTH;
                    Double yTwo = Double.parseDouble( components[4] ) * HEIGHT;

                    AdvancedShape nShape = new AdvancedLine(xOne, yOne, xTwo, yTwo);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
                case "ELLIPSE": {
                    Double xOne = Double.parseDouble( components[1] ) * WIDTH;
                    Double yOne = Double.parseDouble( components[2] ) * HEIGHT;
                    Double xTwo = Double.parseDouble( components[3] ) * WIDTH;
                    Double yTwo = Double.parseDouble( components[4] ) * HEIGHT;

                    AdvancedShape nShape = new AdvancedEllipse(xOne, yOne, xTwo, yTwo);
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
//                case "POLYGON": {
//                    ArrayList<VectorPoint> points = new ArrayList<>();
//                    // start at 1 to avoid command in component[0], iterating +2 for each coord
//                    for (int i = 1; i < components.length; i += 2) {
//                        VectorPoint point = parsePoint(components[i], components[i + 1]);
//                        points.add(point);
//                    }
//
//                    shapes.add( new Polygon(points) );
//                    break;
//                }
                case "PEN": {
                    Color myPenColour = hexToRgb(components[1]);
                    this.recentPenColor = myPenColour;

                    break;
                }
                case "FILL": {
                    Color myFillColour = hexToRgb(components[1]);
                    this.recentFillColor = myFillColour;
                    break;
                }
                default:
                    throw new Exception("Command was not valid: " + line);
            }
        }

        return shapes;
    }

    /**
     * Converts a hexidecimal code to a new Color
     * @param colorStr e.g. "#FFFFFF"
     * @return new Color
     */
    public static Color hexToRgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    /**
     *
     * @return Queue of commands
     */
    public ArrayList<AdvancedShape> get(){
        return shapes;
    }


}
