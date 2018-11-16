import java.awt.*;

public interface Shape {
    void draw(Graphics g);
    void setPos(int x, int y);
    int getPosX();
    int getPosY();
    boolean collides(int x, int y);
}
