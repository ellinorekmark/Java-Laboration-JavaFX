package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends MyShape {
    double endX;
    double endY;

    public Line(double x, double y, double endX, double endY, double size, Color color) {
        super(x, y, size, color);
        this.endX = endX;
        this.endY = endY;
    }


    public String toSVG() {
        return "<line x1=\"" + getX() + "\" y1=\"" + getY() + "\" x2=\"" + getEndX() + "\" y2=\"" + getEndY() + "\" stroke=\"" + toHexString(getColor()) + "\" stroke-width=\"" + getSize() + "\"/>";

    }

    public MyShape copy(MyShape shape) {
        Line line = (Line) shape;
        return new Line(shape.getX(), shape.getY(), line.getEndX(), line.getEndY(), shape.getSize(), shape.getColor());
    }

    @Override
    public String networkString() {

        return "" + getClass().getSimpleName() + "\"" + getX() + "\"" + getY() + "\"" + getSize() + "\"" + getColor().getRed() + "\"" + getColor().getGreen() + "\"" + getColor().getBlue()+"\""+getEndX()+"\""+getEndY()+"\"";
    }

    @Override
    public boolean compare(double x, double y) {

        double rightEdge;
        double leftEdge;
        double top;
        double bottom;

        if (getX() - getEndX() < 0) {
            rightEdge = getEndX();
            leftEdge = getX();
        } else {
            rightEdge = getX();
            leftEdge = getEndX();
        }

        if (getY() - getEndY() < 0) {
            top = getEndY();
            bottom = getY();
        } else {
            top = getY();
            bottom = getEndY();
        }

        return x > leftEdge && x < rightEdge && y > bottom && y < top;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.beginPath();
        context.setStroke(getColor());
        context.setLineWidth(getSize());
        context.moveTo(getX(), getY());
        context.lineTo(getEndX(), getEndY());
        context.closePath();
        context.stroke();
    }

    public double getEndX() {
        return endX;
    }


    public double getEndY() {
        return endY;
    }


}