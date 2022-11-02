package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

abstract public class MyShape extends Shape {

    private double x;
    private double y;
    private double size;
    private Color color;

    public MyShape(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }


    abstract public boolean compareShapeAndMouseEvent(MyShape shape, double x, double y);
    abstract public void draw(GraphicsContext context, MyShape shape);


    abstract public MyShape editShape(MyShape shape, Color color,double size);
    abstract public String toSVG();

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }


    @Override
    public Object clone() {
        try {
            return (MyShape) super.clone();
        } catch (CloneNotSupportedException ignored) {

        }
        return null;
    }

abstract public String networkString();


}
