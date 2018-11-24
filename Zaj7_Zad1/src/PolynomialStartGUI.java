import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolynomialStartGUI extends Stage{

    public PolynomialStartGUI(){

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 500, 275);
        this.setScene(scene);

        //Setting title to the scene
        this.setTitle("Polynomial graph - set count");
        Text scenetitle = new Text("Welcome, please insert number of monomials");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label countLabel = new Label("Number of monomials :");
        grid.add(countLabel, 0, 1);

        TextField countTextField = new TextField();
        grid.add(countTextField, 1, 1);

        Text errorLabel = new Text("");
        errorLabel.setFill(Color.FIREBRICK);
        grid.add(errorLabel, 1,3);

        Button commit = new Button("Commit");
        commit.setOnAction(event -> {
            try {
                int count = Integer.parseInt(countTextField.getText());
                if (count > 0) {
                    errorLabel.setText("");
                    new PolynomialGUI(count);
                } else {
                    errorLabel.setText("Enter integer greater than 0");
                }
            }catch (Exception e){
                errorLabel.setText("Enter integer greater than 0");
            }
        });
        grid.add(commit, 1, 2);

        //Adding the scene to the stage
        this.setScene(scene);

        //Displaying the contents of a scene
        this.show();
    }
}
