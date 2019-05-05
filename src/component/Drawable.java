package component;

public interface Drawable {
    enum Type{
        PLOT,
        POLYGON,
        LINE,
        RECTANGLE,
        ELLIPSE,
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