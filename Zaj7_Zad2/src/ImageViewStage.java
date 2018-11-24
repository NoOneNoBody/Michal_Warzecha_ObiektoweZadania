import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageViewStage extends Stage {

    public ImageViewStage(Image image, String name){

        ScrollPane scrollPane = new ScrollPane();

        ImageView imageView = new ImageView(image);

        scrollPane.setContent(imageView);

        Scene scene = new Scene(scrollPane, Math.min(1600,image.getWidth() + 2), Math.min(900,image.getHeight()) + 2);
        this.setScene(scene);

        this.setTitle(name);
        //Displaying the contents of a scene
        this.show();
    }
}
