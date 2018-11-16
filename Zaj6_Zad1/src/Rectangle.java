import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements Shape{

    private int posX,posY;
    private int width,height;
    private boolean fill;
    private Color color;

    public Rectangle(int posX, int poxY, int width, int height) {
        this.posX = posX;
        this.posY = poxY;
        this.width = width;
        this.height = height;
        this.fill = false;
        this.color = Color.black;
    }

    public Rectangle(int posX, int poxY, int width, int height, Color color) {
        this.posX = posX;
        this.posY = poxY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.fill = false;
    }

    public Rectangle(int posX, int poxY, int width, int height, Color color, boolean fill) {
        this.posX = posX;
        this.posY = poxY;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        Rectangle2D rectangle = new Rectangle2D.Double(posX, posY, width, height);
        g.setColor(color);
        ((Graphics2D)g).draw(rectangle);
        if(fill){
            ((Graphics2D)g).fill(rectangle);
        }
    }

    @Override
    public void setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public boolean collides(int x, int y) {
        int distX = x - this.posX;
        int distY = y - this.posY;
        return (distX >= 0 && distX <= this.width && distY >= 0 && distY <= this.height);
    }
}
