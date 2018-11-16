import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyPlotPanel extends JPanel {

    private float[] parameters;
    private float startX,endX,startY,endY, step;

    private final int offset = 50;

    int width, height;

    public  MyPlotPanel(int w, int h){
        this.width = w;
        this.height = h;
        setMinimumSize(new Dimension(w,h));
        setMaximumSize(new Dimension(w,h));
        setBackground(Color.white);
    }

    private Point2D.Float toPx(float x, float y){
        float XtoPx = (width-2*offset)/(endX - startX);
        float YtoPx = (height-2*offset)/(endY - startY);

        int xPx = (int)((x - startX)*XtoPx) + offset;
        int yPx = (int)((endY - y)*YtoPx) + offset;

        return new Point2D.Float(xPx,yPx);
    }

    private void drawPoint(Graphics2D g2,Point2D.Float pos, float value, boolean isX){
        final int pointSize = 5;
        final int stringOffsetX = 40;
        final int stringOffsetY = 20;
        Line2D.Float line;
        Point2D.Float stringPos;
        if(isX){
            line = new Line2D.Float(pos.x, pos.y - pointSize, pos.x, pos.y + pointSize);
            stringPos = new Point2D.Float(pos.x, pos.y + pointSize + stringOffsetY);
        } else {
            line = new Line2D.Float(pos.x - pointSize, pos.y, pos.x + pointSize, pos.y);
            stringPos = new Point2D.Float(pos.x - pointSize - stringOffsetX, pos.y);
        }
        g2.draw(line);
        g2.drawString(String.format("%.2f",value), stringPos.x, stringPos.y);
    }

    private void drawAxes(Graphics2D g2){
        final int arrowSize = 5;
        final int pointCount = 6;

        Point2D.Float startPoint = new Point2D.Float(offset, height - offset);
        Point2D.Float endPointX = new Point2D.Float(width - offset, height - offset);
        Point2D.Float endPointY = new Point2D.Float(offset, offset);
        Line2D xLine = new Line2D.Float(startPoint, endPointX);
        Line2D xArrow1 = new Line2D.Float(endPointX,
                new Point2D.Float(endPointX.x - arrowSize, endPointX.y + arrowSize));
        Line2D xArrow2 = new Line2D.Float(endPointX,
                new Point2D.Float(endPointX.x - arrowSize, endPointX.y - arrowSize));
        Line2D yLine = new Line2D.Float(startPoint, endPointY);
        Line2D yArrow1 = new Line2D.Float(endPointY,
                new Point2D.Float(endPointY.x - arrowSize, endPointY.y + arrowSize));
        Line2D yArrow2 = new Line2D.Float(endPointY,
                new Point2D.Float(endPointY.x + arrowSize, endPointY.y + arrowSize));
        g2.draw(xLine);
        g2.draw(yLine);
        g2.draw(xArrow1);
        g2.draw(xArrow2);
        g2.draw(yArrow1);
        g2.draw(yArrow2);

        for(int i=0; i < pointCount; ++i){
            drawPoint(g2,new Point2D.Float(startPoint.x + ((float)i/(float)pointCount)* (float)(width - 2*offset),
                            startPoint.y),
                    startX + ((float)i/(float)pointCount) * (endX - startX), true);

            drawPoint(g2,new Point2D.Float(startPoint.x,
                            startPoint.y - ((float)i/(float)pointCount) * (float)(height - 2*offset)),
                    startY + ((float)i/(float)pointCount) * (endY - startY), false);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(parameters != null){
            float prevX = startX;
            float prevY = CalculatePolynomial.calculate(startX, parameters);
            float currentX = startX + step;

            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.black);
            drawAxes(g2);

            g2.setColor(Color.red);
            while(currentX < endX){
                float currentY = CalculatePolynomial.calculate(currentX, parameters);

                Line2D line = new Line2D.Float(toPx(prevX, prevY), toPx(currentX, currentY));

                g2.draw(line);

                prevX = currentX;
                prevY = currentY;
                currentX += step;
            }
        }
    }

    public float[] getParameters() {
        return parameters;
    }

    public void setParameters(float[] parameters) {
        this.parameters = parameters;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }
}
