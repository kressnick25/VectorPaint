package component;

public class Rectangle extends Shape{
    private static DrawComponent.Type type = DrawComponent.Type.RECTANGLE;

    public Rectangle(float x1, float y1, float x2, float y2) {
        super (x1, y1, x2, y2);
    }

    public Type getType(){
        return type;
    }

}
