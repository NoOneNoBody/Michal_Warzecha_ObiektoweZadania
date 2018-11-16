import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame(final int width, final int height, final String title) {
        super(title);
        JPanel panel = new MyRootPanel();

        add(panel);

        pack();
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
