package AdvancedShape;

import java.awt.*;

public interface AdvancedShape extends Shape {
    void render(Graphics2D g2d);
    void setFillColor(Color color);
    Color getFillColor();
    void setPenColor(Color color);
    Color getPenColor();
    void updateSize(int x, int y);
    boolean isTransparent();
    void setTransparent(boolean transparent);
}
