package component;

public interface DrawComponent {
    enum Type{
        PLOT,
        POLYGON,
        LINE,
        RECTANGLE,
        ECLIPSE,
    }
    /**
     *
     * @return Command in format: LINE 0.0 0.1 2.4 2.7
     */
    String toString();

    /**
     *
     * @return Type as a string: RECTANGLE
     */
    Type getType();
}