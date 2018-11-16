import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ListIterator;

public class MouseHandler {

    private static MouseListener listener;
    private static MouseMotionListener motionListener;

    private static Integer prevPosX;
    private static Integer prevPosY;
    private static Shape currentShape;

    public static void AddToPanel(MyPanel panel){
        listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                prevPosX = e.getX();
                prevPosY = e.getY();

                ListIterator li = panel.getShapes().listIterator(panel.getShapes().size());
                while(li.hasPrevious()) {
                    Shape shape = (Shape)li.previous();
                    if(shape.collides(e.getX(), e.getY())){
                        currentShape = shape;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                prevPosX = null;
                prevPosY = null;
                currentShape = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };

        motionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(currentShape != null && prevPosX != null && prevPosY != null){
                    currentShape.setPos(currentShape.getPosX() + (e.getX() - prevPosX),
                                 currentShape.getPosY() + (e.getY() - prevPosY));
                    prevPosX = e.getX();
                    prevPosY = e.getY();
                    panel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };

        panel.addMouseListener(listener);
        panel.addMouseMotionListener(motionListener);
    }
}
