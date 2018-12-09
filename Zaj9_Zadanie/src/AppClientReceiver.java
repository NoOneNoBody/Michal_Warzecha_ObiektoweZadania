import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppClientReceiver extends Application {

    static final int WIDTH = 500, HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientReceiver client = new ClientReceiver();
        client.connect();

        GridPane gridPane = new GridPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gridPane.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();

        Thread listeningThread = new Thread(){
            public void run(){
                client.listenForObjects( (object) -> {
                    LineObject line = (LineObject) object;
                    graphicsContext.setFill(Color.BLACK);
                    graphicsContext.setLineWidth(1);
                    graphicsContext.moveTo(line.getStartX(), line.getStartY());
                    graphicsContext.lineTo(line.getEndX(), line.getEndY());
                    graphicsContext.stroke();
                });
                client.disconnect();
            }
        };

        listeningThread.start();

        primaryStage.setOnCloseRequest( (event) -> {
            client.disconnect();
        });
    }

    public static void main(String[] args){
        launch(args);
    }
}
