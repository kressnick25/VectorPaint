package component;

public interface ColourTool {
    enum ToolType{
        PEN,
        FILL,
    }

    String toString();

    String Code();

    ToolType getType();

}
