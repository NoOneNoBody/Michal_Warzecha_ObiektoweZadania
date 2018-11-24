import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageViewerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        Button selectFolderButton = new Button("Select Folder");
        selectFolderButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if(selectedDirectory != null){
                new ImagesGalleryStage(selectedDirectory);
            }
        });
        grid.getChildren().add(selectFolderButton);

        Scene scene = new Scene( grid,400, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image viewer");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
