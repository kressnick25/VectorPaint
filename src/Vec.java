import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Vec {
    // PQ == ArrayList with first in last out
    // Linked list == Array list with FIFO structure
    private Queue<String> commands = new LinkedList<>();
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
    public Vec(String filename, PriorityQueue<String> commands){
        // Convert PQ to LinkedList and store in private var
        this.commands = new LinkedList<String>(commands);
    }

    public void save(){
        // TODO for each in commands, write to file with newline

    }

    /**
     * Reads from filename stream to private variable;
     */
    public void read(){
        Queue<String> lines = new LinkedList<>();

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
    public Queue<String> get(){
        return commands;
    }

}
