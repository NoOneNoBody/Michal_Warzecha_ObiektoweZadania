import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);

        //Setting title to the scene
        primaryStage.setTitle("Database - books");
        Text scenetitle = new Text("Connect to your mySQL AGH database");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label loginLabel = new Label("Login :");
        grid.add(loginLabel, 0, 1);
        TextField loginTextField = new TextField();
        grid.add(loginTextField, 1, 1);


        Label passLabel = new Label("Password :");
        grid.add(passLabel, 0, 2);
        PasswordField passTextField = new PasswordField();
        grid.add(passTextField, 1, 2);

        Text errorLabel = new Text("");
        errorLabel.setFill(Color.FIREBRICK);
        grid.add(errorLabel, 1,4);

        Button commit = new Button("Connect");
        commit.setOnAction(event -> {
            String login = loginTextField.getText();
            String pass = passTextField.getText();
            Connection conn = DB.connect(login, login, pass);

            if(conn != null){
                errorLabel.setText("");
                new GUIStage(conn);
            }else{
                errorLabel.setText("Cannot connect to database");
            }
        });
        grid.add(commit, 1, 3);

        //Adding the scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of a scene
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
