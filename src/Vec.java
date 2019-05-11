import component.Drawable;

import java.awt.*;
import java.io.*;
import java.util.*;

import component.*;
import component.Polygon;
import component.Rectangle;

public class Vec {
    // PQ == ArrayList with first in last out
    private ArrayList<Drawable> commands = new ArrayList<>();
    private String filename;

    /**
     * Construct new Vec class to read data from file.
     * @param filename String file
     */
    public Vec(String filename){
        this.filename = filename;
    }

    /**
     * Construct new Vec class to write curent data to file.
     * @param filename
     * @param commands
     */
    public Vec(String filename, Queue<Drawable> commands){
        this.filename = filename;
        // Convert PQ to LinkedList and store in private var
        this.commands = new ArrayList<>(commands);
    }

    public void save(){
        try {
            // Open file
            FileWriter file = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(file);

            // Write each command, print adds newline
            commands.forEach((a) -> writer.printf(a + "\n"));

            //close file connection
            writer.close();

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

        try {
            this.commands = parseLinesToCommands(lines);
        } catch(Exception e){
            // TODO popup window here
        }
    }

    // TODO more specific exception
    // TODO test components against Type enum
    private ArrayList<Drawable> parseLinesToCommands(ArrayList<String> lines) throws Exception{
        ArrayList<Drawable> commands = new ArrayList<Drawable>();
        // Parse each component in line to local vars
        for (String line : lines){
            // split line into compontents ie PLOT, 0.0, 0.1
            String[] components = line.split("\\s+"); // TODO arrayList?
            // Initialise new object from vars
            switch(components[0]){
                // Use brackets in switch statement to manage scope
                case "RECTANGLE": {// TODO convert to comparison to enum
                    VectorPoint point1 = parsePoint(components[1], components[2]);
                    VectorPoint point2 = parsePoint(components[3], components[4]);
                    commands.add( new Rectangle(point1, point2) );
                }
                case "PLOT": {
                    VectorPoint point1 = parsePoint(components[1], components[2]);
                    commands.add( new Plot(point1) );
                }
                case "LINE": {
                    VectorPoint point1 = parsePoint(components[1], components[2]);
                    VectorPoint point2 = parsePoint(components[3], components[4]);
                    commands.add( new Line(point1, point2) );
                }
                case "ELLIPSE": {
                    VectorPoint point1 = parsePoint(components[1], components[2]);
                    VectorPoint point2 = parsePoint(components[3], components[4]);
                    commands.add( new Ellipse(point1, point2) );
                }
                case "POLYGON": {
                    ArrayList<VectorPoint> points = new ArrayList<>();
                    // start at 1 to avoid command in component[0], iterating +2 for each coord
                    for (int i = 1; i < components.length; i += 2) {
                        VectorPoint point = parsePoint(components[i], components[i + 2]);
                        points.add(point);
                    }

                    commands.add( new Polygon(points) );
                }
                case "PEN": {
                    Color myPenColour = hexToRgb(components[1]);
                    commands.add( new PenColour(myPenColour) );
                }
                case "FILL": {
                    Color myFillColour = hexToRgb(components[1]);
                    commands.add( new PenColour(myFillColour) );
                }
                default:
                    throw new Exception("Command was not valid: " + line);
            }
        }

        return commands;
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
     * Parses String coords to Doubles and constructs a new VectorPoint
     * @param coord1 1st decimal coord as string
     * @param coord2 2nd decimal coord as string
     * @return VectorPoint with prescribed coords
     */
    private VectorPoint parsePoint(String coord1, String coord2){
        // Parse Strings to double
        double x1 = Double.parseDouble( coord1 );
        double y1 = Double.parseDouble( coord2 );

        return new VectorPoint(x1, y1);
    }
    /**
     *
     * @return Queue of commands
     */
    public ArrayList<Drawable> get(){
        return commands;
    }
}
