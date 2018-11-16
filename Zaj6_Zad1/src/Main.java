import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main (String[] args){
        Frame frame = new Frame();
        MyPanel panel = new MyPanel();

        Rectangle rectangle = new Rectangle(100, 100, 200, 40, Color.blue, true);
        panel.addShape(rectangle);
        panel.addShape(new Circle(200, 10, 20, Color.red,true));
        panel.addShape(new Circle(20, 10, 30));

        MouseHandler.AddToPanel(panel);

        frame.setSize(450, 450);
        frame.setVisible(true);
        frame.add(panel);

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }

}
