import java.awt.*;
import java.util.LinkedList;

public class MyPanel extends Panel {

    private LinkedList<Shape> shapes;

    MyPanel(){
        shapes = new LinkedList<Shape>();
    }

    MyPanel(LinkedList<Shape> shapes){
        this.shapes = shapes;
    }

    public void setShapes(LinkedList<Shape> shapes){
        this.shapes = shapes;
    }

    public LinkedList<Shape> getShapes(){
        return shapes;
    }

    public void clearShapes(){
        shapes.clear();
    }

    public Shape getShape(int i){
        return shapes.get(i);
    }

    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public void removeShape(){
        shapes.remove();
    }

    public void removeShape(int i){
        shapes.remove(i);
    }

    @Override
    public void paint(Graphics g) {
        for(Shape shape : shapes){
            shape.draw(g);
        }
    }
}
