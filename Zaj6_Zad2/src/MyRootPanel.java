import javax.swing.*;

public class MyRootPanel extends JPanel {

    private MyPlotPanel plotPanel;
    private MyGUIPanel guiPanel;

    public MyRootPanel(){

        plotPanel = new MyPlotPanel(500, 500);
        guiPanel = new MyGUIPanel(plotPanel, 5);

        plotPanel.setStartX(-10);
        plotPanel.setEndX(10);
        plotPanel.setStartY(0);
        plotPanel.setEndY(100);
        plotPanel.setStep(0.1f);
        float[] parameters = {0, 0, 1};
        plotPanel.setParameters(parameters);


        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(plotPanel)
                        )
                        .addComponent(guiPanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(plotPanel)
                                .addComponent(guiPanel)
                        )
        );
        setLayout(layout);
    }
}
