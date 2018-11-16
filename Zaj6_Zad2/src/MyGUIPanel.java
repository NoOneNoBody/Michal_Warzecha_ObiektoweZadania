import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUIPanel extends JPanel implements ActionListener {

    private JButton drawButton;
    private JButton countButton;
    private JTextField countTextField;
    private JTextField[] settingsTextFields = new JTextField[5];
    private final String[] settingsLabels = {"Start x:", "End x:", "Start y:", "End y:", "Step:"};
    private JTextField[] textFields;
    private MyPlotPanel plotPanel;
    private SpringLayout layout;

    public MyGUIPanel(MyPlotPanel plotPanel, int count) {
        this.plotPanel = plotPanel;

        layout = new SpringLayout();
        setLayout(layout);

        makeSettings();
        if(count > 0) {
            makeContent(count);
        }
    }

    private void makeSettings(){
        countButton = new JButton("Zatwierdź");
        countTextField = new JTextField(10);
        JLabel countLabel = new JLabel("Ilość jednomianów: ");

        countButton.addActionListener(this);
        add(countButton);
        add(countTextField);
        add(countLabel);

        JLabel[] labels = new JLabel[settingsTextFields.length];
        for(int i=0;i<settingsTextFields.length;++i){
            labels[i] = new JLabel(settingsLabels[i]);
            settingsTextFields[i] = new JTextField(10);
            add(labels[i]);
            add(settingsTextFields[i]);

            layout.putConstraint(SpringLayout.WEST, this,
                    5, SpringLayout.WEST, labels[i]);
            layout.putConstraint(SpringLayout.WEST, settingsTextFields[i],
                    5, SpringLayout.EAST, labels[i]);
            layout.putConstraint(SpringLayout.NORTH, settingsTextFields[i],
                    5, SpringLayout.NORTH, labels[i]);
            if(i>0){
                layout.putConstraint(SpringLayout.NORTH, labels[i],
                        5, SpringLayout.SOUTH, labels[i-1]);
            }else {
                layout.putConstraint(SpringLayout.NORTH, labels[i],
                        5, SpringLayout.SOUTH, countButton);
            }
        }


        layout.putConstraint(SpringLayout.WEST, this,
                5, SpringLayout.WEST, countLabel);
        layout.putConstraint(SpringLayout.NORTH, this,
                5, SpringLayout.NORTH, countLabel);
        layout.putConstraint(SpringLayout.WEST, countTextField,
                5, SpringLayout.EAST, countLabel);
        layout.putConstraint(SpringLayout.NORTH, countTextField,
                5, SpringLayout.NORTH, countLabel);
        layout.putConstraint(SpringLayout.WEST, countButton,
                5, SpringLayout.EAST, countTextField);
        layout.putConstraint(SpringLayout.NORTH, countButton,
                5, SpringLayout.NORTH, countLabel);
    }

    private void makeContent(int count){
        if(count <= 0) return;

        JLabel[] labels = new JLabel[count];
        textFields = new JTextField[count];
        for (int i = 0; i < count; i++) {
            labels[i] = new JLabel("<html>&nbsp;&nbsp;x<sup>"+i+"</sup>:</html>", JLabel.TRAILING);
            add(labels[i]);
            textFields[i] = new JTextField(10);
            labels[i].setLabelFor(textFields[i]);
            add(textFields[i]);
        }
        for(int i=0; i<count; ++i){
            layout.putConstraint(SpringLayout.WEST, this,
                    5, SpringLayout.WEST, labels[i]);
            layout.putConstraint(SpringLayout.WEST, textFields[i],
                    5, SpringLayout.EAST, labels[i]);
            layout.putConstraint(SpringLayout.NORTH, textFields[i],
                    5, SpringLayout.NORTH, labels[i]);
            if(i>0){
                layout.putConstraint(SpringLayout.NORTH, labels[i],
                        5, SpringLayout.SOUTH, labels[i-1]);
            }else {
                layout.putConstraint(SpringLayout.NORTH, labels[i],
                        5, SpringLayout.SOUTH, settingsTextFields[settingsTextFields.length - 1]);
            }
        }

        drawButton = new JButton("Rysuj");
        add(drawButton);
        drawButton.addActionListener(this);
        layout.putConstraint(SpringLayout.NORTH, drawButton,
                5, SpringLayout.SOUTH, labels[labels.length-1]);
        layout.putConstraint(SpringLayout.EAST, drawButton,
                5, SpringLayout.EAST, textFields[labels.length-1]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == drawButton){
            float[] parameters = new float[textFields.length];
            for(int i=0;i<textFields.length;++i){
                String text = textFields[i].getText();
                if(text.equals("")){
                    parameters[i] = 0;
                } else {
                    parameters[i] = Float.parseFloat(text);
                }
            }
            plotPanel.setParameters(parameters);
            plotPanel.setStartX(Float.parseFloat(settingsTextFields[0].getText()));
            plotPanel.setEndX(Float.parseFloat(settingsTextFields[1].getText()));
            plotPanel.setStartY(Float.parseFloat(settingsTextFields[2].getText()));
            plotPanel.setEndY(Float.parseFloat(settingsTextFields[3].getText()));
            plotPanel.setStep(Float.parseFloat(settingsTextFields[4].getText()));
            plotPanel.repaint();
        }
        else if(source == countButton){
            int count = Integer.parseInt(countTextField.getText());
            removeAll();
            makeSettings();
            makeContent(count);
            revalidate();
            repaint();
        }
    }
}
