import component.Drawable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import component.*;

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
    public Vec(String filename, Queue<String> commands){
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
            // Print exception
            System.err.format("Error trying to read '%s'.", filename);
            e.printStackTrace();
        }

        this.commands = parseLinesToCommands(lines);
    }

    // TODO try catch
    // TODO test components against enum
    private ArrayList<Drawable> parseLinesToCommands(ArrayList<String> lines) {
        ArrayList<Drawable> commands = new ArrayList<Drawable>();
        // Parse each component in line to local vars
        for (String line : lines){
            // split line into compontents ie PLOT, 0.0, 0.1
            String[] components = line.split("\\s+"); // TODO arrayList?
            Object command; //TODO refactor colourtool and drawable under one interface
            // Initialise new object from vars
            switch(components[0]){
                case "RECTANGLE": // TODO convert to comparison to enum
                    command = new Rectangle(
                                    new VectorPoint(
                                            Double.parseDouble( components[1] ),
                                            Double.parseDouble( components[2] ) ),
                                    new VectorPoint(
                                            Double.parseDouble( components[3] ),
                                            Double.parseDouble( components[4] )
                                    ));
                case "PLOT":
                    command = new Plot(
                            new VectorPoint(
                                    Double.parseDouble( components[1] ),
                                    Double.parseDouble( components[2] ) ));
                case "LINE":
                    command = new Line(
                            new VectorPoint(
                                    Double.parseDouble( components[1] ),
                                    Double.parseDouble( components[2] ) ),
                            new VectorPoint(
                                    Double.parseDouble( components[3] ),
                                    Double.parseDouble( components[4] )
                                    ));
                case "ELLIPSE":
                    command = new Ellipse(
                            new VectorPoint(
                                    Double.parseDouble( components[1] ),
                                    Double.parseDouble( components[2] ) ),
                            new VectorPoint(
                                    Double.parseDouble( components[3] ),
                                    Double.parseDouble( components[4] )
                            ));
                case "POLYGON":
                    //TODO
                case "PEN":
                    //TODO
                case "FILL":
                    //TODO
            }

            // add object to array
            commands.add(command);

        }


        //return array
        return commands;
    }

    /**
     *
     * @return Queue of commands
     */
    public ArrayList<Drawable> get(){
        return commands;
    }
}
