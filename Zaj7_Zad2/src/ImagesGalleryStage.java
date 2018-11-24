import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;

public class ImagesGalleryStage extends Stage {

    private final float WIDTH = 602;
    private final float HEIGHT = 602;

    public ImagesGalleryStage(File directory){
        ScrollPane scrollPane = new ScrollPane();
        FlowPane layout = new FlowPane();
        layout.setPrefWrapLength(600);
        scrollPane.setContent(layout);

        Scene scene = new Scene(scrollPane, WIDTH, HEIGHT);
        LoadImages(directory, layout);

        this.setTitle(directory.getPath());

        this.setScene(scene);
        this.show();
    }

    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp" // and other formats you need
    };

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    private void LoadImages(File directory, Pane pane){
        File[] images = directory.listFiles(IMAGE_FILTER);
        for(File file: images) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,  event -> {
                new ImageViewStage(image, file.getName());
                event.consume();
            });
            pane.getChildren().add(imageView);
        }
    }
}
