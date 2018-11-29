import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorStage extends Stage {

    public ErrorStage(){

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 500, 275);
        this.setScene(scene);

        //Setting title to the scene
        this.setTitle("Database - books");
        Text scenetitle = new Text("Lost connection with database");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.FIREBRICK);
        grid.add(scenetitle, 0, 0, 2, 1);

        //Adding the scene to the stage
        this.setScene(scene);

        //Displaying the contents of a scene
        this.show();
    }
}
