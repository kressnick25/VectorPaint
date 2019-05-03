import java.util.PriorityQueue;

public class Vec {

    // TODO might need to be different type. a type that command can be altered mid array.
    // PQ == ArrayList with first in last out
    private PriorityQueue<String> commands;


    /**
     * Construct new Vec class to read data from file.
     * @param file String file
     */
    public Vec(String file){
        // TODO read from filesystem here
    }

    /**
     * Construct new Vec class to write curent data to file.
     * @param commands
     */
    public Vec(PriorityQueue<String> commands){
        this.commands = commands;
    }

    public void save(){
        // TODO for each in commands, write to file with newline
    }

    public void read(){
        // TODO read from file. Dump into commands.
    }

    public PriorityQueue<String> get(){
        return commands;
    }

}
