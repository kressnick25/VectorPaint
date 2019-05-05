import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Vec {
    // PQ == ArrayList with first in last out
    private ArrayList<String> commands = new ArrayList<>();
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
            // Assign lines to private var
            this.commands = lines;
        }
        catch (Exception e){
            // Print exception
            System.err.format("Error trying to read '%s'.", filename);
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Queue of commands
     */
    public ArrayList<String> get(){
        return commands;
    }
}
