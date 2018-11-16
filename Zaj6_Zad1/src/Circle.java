import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Shape {

    private int posX;
    private int posY;
    private int radius;
    private boolean fill;
    private Color color;

    public Circle(int posX, int posY, int radius) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = Color.black;
        this.fill = false;
    }

    public Circle(int posX, int posY, int radius, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = color;
        this.fill = false;
    }

    public Circle(int posX, int posY, int radius, Color color, boolean fill) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = color;
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        Ellipse2D ellipse = new Ellipse2D.Double(posX,posY,radius*2,radius*2);
        g.setColor(color);
        ((Graphics2D)g).draw(ellipse);
        if(fill){
            ((Graphics2D)g).fill(ellipse);
        }
    }

    @Override
    public void setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public boolean collides(int x, int y) {
        int distX = this.posX + this.radius - x;
        int distY = this.posY + this.radius - y;
        double distance = Math.sqrt(distX*distX + distY*distY);
        return distance <= this.radius;
    }
}
