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
            String myClass, x1, y1, x2, y2;

            // split line into compontents ie PLOT, 0.0, 0.1
            String[] components = line.split("\\s+");
            // parse componenents to local vars
            myClass = components[0];
            x1 = components[1];
            y1 = components[2];
            x2 = components[3];
            y2 = components[4];

            // Initialise new object from vars
            try {
                Class cls = Class.forName("component" + myClass);


            } catch (Exception e){
                // TODO error message here
            }
            // add object to array

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
