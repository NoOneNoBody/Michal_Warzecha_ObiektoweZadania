
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolynomialPlot extends Stage {

    private float[] parameters;
    private float startX;
    private float endX;
    private float startY;
    private float endY;
    private float step;

    private final int offset = 70;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    public PolynomialPlot(float[] parameters, float startX, float endX, float startY, float endY, float step) {

        this.parameters = parameters;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.step = step;

        Group root = new Group();
        drawAxes(root);
        drawPolynomial(root);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        this.setScene(scene);
        this.show();
    }

    private Point2D toPx(float x, float y){
        float XtoPx = (WIDTH-2*offset)/(endX - startX);
        float YtoPx = (HEIGHT-2*offset)/(endY - startY);

        int xPx = (int)((x - startX)*XtoPx) + offset;
        int yPx = (int)((endY - y)*YtoPx) + offset;

        return new Point2D(xPx,yPx);
    }

    private void drawLabel(Group g, Point2D pos, float value, boolean isX){
        final int pointSize = 5;
        final int stringOffsetX = 40;
        final int stringOffsetY = 20;
        Line line;
        Point2D stringPos;
        if(isX){
            line = new Line(pos.getX(), pos.getY() - pointSize, pos.getX(), pos.getY() + pointSize);
            stringPos = new Point2D(pos.getX(), pos.getY() + pointSize + stringOffsetY);
        } else {
            line = new Line(pos.getX() - pointSize, pos.getY(), pos.getX() + pointSize, pos.getY());
            stringPos = new Point2D(pos.getX() - pointSize - stringOffsetX, pos.getY());
        }
        g.getChildren().add(line);
        Text text = new Text(String.format("%.2f",value));
        text.setX(stringPos.getX());
        text.setY(stringPos.getY());
        g.getChildren().add(text);
    }

    private void drawAxes(Group group){
        final int arrowSize = 5;
        final int pointCount = 6;

        Point2D startPoint = new Point2D(offset, HEIGHT - offset);
        Point2D endPointX = new Point2D(WIDTH - offset, HEIGHT - offset);
        Point2D endPointY = new Point2D(offset, offset);
        Line xLine = new Line(startPoint.getX(), startPoint.getY(), endPointX.getX(), endPointX.getY());
        Line xArrow1 = new Line(endPointX.getX(), endPointX.getY(),
                endPointX.getX() - arrowSize, endPointX.getY() + arrowSize);
        Line xArrow2 = new Line(endPointX.getX(), endPointX.getY(),
                endPointX.getX() - arrowSize, endPointX.getY() - arrowSize);
        Line yLine = new Line(startPoint.getX(), startPoint.getY(), endPointY.getX(), endPointY.getY());
        Line yArrow1 = new Line(endPointY.getX(), endPointY.getY(),
                endPointY.getX() - arrowSize, endPointY.getY() + arrowSize);
        Line yArrow2 = new Line(endPointY.getX(), endPointY.getY(),
                endPointY.getX() + arrowSize, endPointY.getY() + arrowSize);

        group.getChildren().add(xLine);
        group.getChildren().add(yLine);
        group.getChildren().add(xArrow1);
        group.getChildren().add(xArrow2);
        group.getChildren().add(yArrow1);
        group.getChildren().add(yArrow2);

        for(int i=0; i < pointCount; ++i){
            drawLabel(group,new Point2D(startPoint.getX() + ((float)i/(float)pointCount)* (float)(WIDTH - 2*offset),
                            startPoint.getY()),
                    startX + ((float)i/(float)pointCount) * (endX - startX), true);

            drawLabel(group,new Point2D(startPoint.getX(),
                            startPoint.getY() - ((float)i/(float)pointCount) * (float)(HEIGHT - 2*offset)),
                    startY + ((float)i/(float)pointCount) * (endY - startY), false);
        }
    }

    private void drawPolynomial(Group group){
        if(parameters != null){
            float prevX = startX;
            float prevY = CalculatePolynomial.calculate(startX, parameters);
            float currentX = startX + step;

            while(currentX < endX){
                float currentY = CalculatePolynomial.calculate(currentX, parameters);

                Point2D beginPoint = toPx(prevX, prevY);
                Point2D endPoint = toPx(currentX, currentY);
                Line line = new Line(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
                line.setStroke(Color.RED);

                group.getChildren().add(line);

                prevX = currentX;
                prevY = currentY;
                currentX += step;
            }

            float currentY = CalculatePolynomial.calculate(endX, parameters);
            Point2D beginPoint = toPx(prevX, prevY);
            Point2D endPoint = toPx(endX, currentY);
            Line line = new Line(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
            line.setStroke(Color.RED);

            group.getChildren().add(line);
        }
    }
}
