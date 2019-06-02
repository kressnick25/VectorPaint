import AdvancedShape.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Vec{
    private ArrayList<AdvancedShape> shapes = new ArrayList<>();
    private String filename;
    private int WIDTH = 1000;
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
    public String getFileName(){
        return filename;
    }
    public ArrayList getShapes(){
        return shapes;
    }

    public void setShapes(ArrayList<AdvancedShape> newShapes) {
        this.shapes = newShapes;
    }

    /**
     * Saves the images in the GUI frame as a vector Point.
     * takes no parameters
     */
    public void save(){
        try {
            // Open file
            filename = filename + ".VEC";
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
    public void read() throws Exception{
        ArrayList<String> lines = readLinesFromFile();
        System.out.print(lines);

        this.shapes = parseLinesToShapes(lines);
        System.out.print(shapes);
    }

    /**
     * Reads from filename and stores all the line locations in an ArrayList.
     * @Return ArrayList
     */
    private ArrayList<String> readLinesFromFile() throws Exception{
        ArrayList<String> lines = new ArrayList<>();

        // Open file to new reader
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        // For each line, add to lines queue
        while((line = reader.readLine()) != null){
            lines.add(line);
        }
        // Close file connection
        reader.close();

        return lines;
    }

    /**
     * Takes the shape type and turns it into the corresponding string of the shape name.
     * if no shape is selected a message is produced
     * @param shape
     * @return String
     */

    public String parseShapeToString(AdvancedShape shape) {
        StringBuilder outString = new StringBuilder();
        Color penColor = shape.getPenColor();
        Color fillColor = shape.getFillColor();
        // referenced from
        // https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal-string
        // PenColor
        if (!penColor.equals(recentPenColor)){
            String penHex = String.format(
                    "PEN #%02x%02x%02x\n", penColor.getRed(),
                    penColor.getGreen(), penColor.getBlue());
            recentPenColor = fillColor;
            outString.append(penHex);
        }
        // FillColor
        if (!fillColor.equals(recentFillColor)){
            String fillHex = String.format(
                    "FILL #%02x%02x%02x\n", fillColor.getRed(),
                    fillColor.getGreen(), fillColor.getBlue());
            recentFillColor = fillColor;
            outString.append(fillHex);
        }
        //Shape
        outString.append( shape.toString(WIDTH, HEIGHT) );

        return outString.toString();
    }

    /**
     * Parses an List of VEC formmatted lines to a List of AdvancedShape(s)
     * @param lines A list of strings with VEC formatting
     * @return  A parsed List of AdvancedShapes.
     * @throws Exception if a non-valid command is present in lines.
     */
    private ArrayList<AdvancedShape> parseLinesToShapes(ArrayList<String> lines) throws Exception{
        ArrayList<AdvancedShape> shapes = new ArrayList<AdvancedShape>();
        // Parse each component in line to local vars
        for (String line : lines){
            // split line into shapes ie PLOT, 0.0, 0.1
            String[] components = line.split("\\s+");
            // Initialise new object from vars
            switch(components[0]){
                // Use brackets in switch statement to manage scope
                case "RECTANGLE": {
                    Double x1 = Double.parseDouble( components[1] ) * WIDTH;
                    Double y1 = Double.parseDouble( components[2] ) * HEIGHT;
                    Double x2 = Double.parseDouble( components[3] ) * WIDTH;
                    Double y2 = Double.parseDouble( components[4] ) * HEIGHT;
                    double width = x2 - x1;
                    double height = y2 - y1;
                    AdvancedShape nShape = new AdvancedRectangle(x1, y1, width, height );
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
                case "PLOT": {
                    Double x = Double.parseDouble( components[1] ) * WIDTH;
                    Double y = Double.parseDouble( components[2] ) * HEIGHT;
                    AdvancedShape nShape = new AdvancedPlot(x, y);
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
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
                    Double x1 = Double.parseDouble( components[1] ) * WIDTH;
                    Double y1 = Double.parseDouble( components[2] ) * HEIGHT;
                    Double x2 = Double.parseDouble( components[3] ) * WIDTH;
                    Double y2 = Double.parseDouble( components[4] ) * HEIGHT;
                    double width = x2 - x1;
                    double height = y2 - y1;
                    AdvancedShape nShape = new AdvancedEllipse(x1, y1, width, height);
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
                case "POLYGON": {
                    ArrayList<Integer> xpoints = new ArrayList<>();
                    ArrayList<Integer> ypoints = new ArrayList<>();
                    // start at 1 to avoid command in component[0], iterating +2 for each coord
                    for (int i = 1; i < components.length; i += 2) {
                        double x = Double.parseDouble( components[i]) * WIDTH;
                        double y = Double.parseDouble( components[i + 1]) * HEIGHT;
                            int xPoint = (int) Math.round(x);
                            int yPoint = (int) Math.round(Double.parseDouble(components[i + 1]) * HEIGHT);
                            xpoints.add(xPoint);
                            ypoints.add(yPoint);
                    }

                    // Convert arraylist to int[]
                    AdvancedShape nShape = new AdvancedPolygon(
                            xpoints.stream().mapToInt(i -> i).toArray(),
                            ypoints.stream().mapToInt(i -> i).toArray(),
                            xpoints.size());
                    nShape.setFillColor(this.recentFillColor);
                    nShape.setPenColor(this.recentPenColor);
                    shapes.add(nShape);
                    break;
                }
                case "PEN": {
                    Color myPenColour = hexToRgb(components[1]);
                    this.recentPenColor = myPenColour;
                    break;
                }
                case "FILL": {
                    if (components[1].equals("OFF")) {
                        Color col = new Color(0,0,0,255);
                        this.recentFillColor = col;
                    } else {
                        Color myFillColour = hexToRgb(components[1]);
                        this.recentFillColor = myFillColour;
                    }
                    break;
                }
                default:
                    System.out.println("Vec Read Error: The following command from file was not valid: \n" + line);
                    throw new Exception("Vec Read Error: The following command from file was not valid: \n" + line);
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
