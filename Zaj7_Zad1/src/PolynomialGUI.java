
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolynomialGUI extends Stage {

    private float[] parameters;
    private TextField startXTextField;
    private TextField endXTextField;
    private TextField startYTextField;
    private TextField endYTextField;
    private TextField stepTextField;
    private TextField[] xTextFields;

    private void createInputField(GridPane pane, TextField textField, String labelText, int columnindex, int rowindex){
        Label xLabel = new Label(labelText);
        pane.add(xLabel, columnindex, rowindex);
        pane.add(textField, columnindex + 1, rowindex);
    }

    public PolynomialGUI(int count){

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        xTextFields = new TextField[count];
        for(int i=0;i<count;++i){
            createInputField(grid, xTextFields[i] = new TextField(), "x^"+i+": ", 0, i);
        }

        createInputField(grid, startXTextField = new TextField(), "Start x", 0, count + 1);
        createInputField(grid, endXTextField = new TextField(), "End x", 0, count + 2);
        createInputField(grid, startYTextField = new TextField(), "Start y", 0, count + 3);
        createInputField(grid, endYTextField = new TextField(), "End y", 0, count + 4);
        createInputField(grid, stepTextField = new TextField(), "Step", 0, count + 5);

        Text errorLabel = new Text("");
        errorLabel.setFill(Color.FIREBRICK);
        grid.add(errorLabel, 1,count + 6);

        Button drawButton = new Button("Draw");
        drawButton.setOnAction(event -> {
            try {
                parameters = new float[count];
                for (int i = 0; i < count; ++i) {
                    parameters[i] = Float.parseFloat(xTextFields[i].getText());
                }
                float startX = Float.parseFloat(startXTextField.getText());
                float endX = Float.parseFloat(endXTextField.getText());
                float startY = Float.parseFloat(startYTextField.getText());
                float endY = Float.parseFloat(endYTextField.getText());
                float step = Float.parseFloat(stepTextField.getText());
                if(startX < endX && startY < endY){
                    errorLabel.setText("");
                    new PolynomialPlot(parameters, startX, endX, startY, endY, step);
                }
                else{
                    errorLabel.setText("\"End x\" and \"End y\" have to be greater \nthan \"Start x\" and \"Start y\"");
                }
            }catch (Exception e){
                errorLabel.setText("Set all parameters properly");
            }
        });
        grid.add(drawButton, 1, count + 7);

        Scene secondScene = new Scene(grid, 450, 330 + (count * 40));

        // New window (Stage)
        this.setTitle("Polynomial graph - set values");
        this.setScene(secondScene);

        this.show();
    }

}
