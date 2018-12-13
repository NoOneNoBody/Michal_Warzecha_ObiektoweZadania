import com.sun.javafx.geom.Vec2d;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AppClient extends Application {

    static Vec2d lastPos = null;
    static final int WIDTH = 500, HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client();
        client.connect();

        GridPane gridPane = new GridPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gridPane.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);

        canvas.setOnMouseDragged((event) -> {
            graphicsContext.setFill(Color.BLACK);
            if(lastPos != null) {
                //graphicsContext.fillRect(event.getX(), event.getY(), 1, 1);
                graphicsContext.setLineWidth(1);
                graphicsContext.moveTo(lastPos.x, lastPos.y);
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
                client.sendObject(new LineObject(lastPos.x, lastPos.y, event.getX(), event.getY()));
                lastPos = new Vec2d(event.getX(), event.getY());
            }
        });

        canvas.setOnMousePressed( (event) -> {
            lastPos = new Vec2d(event.getX(), event.getY());
        });

        canvas.setOnMouseReleased( (event) -> {
            lastPos = null;
        });

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
            listeningThread.interrupt();
            client.disconnect();
        });
    }

    public static void main(String[] args){
        launch(args);
    }
}