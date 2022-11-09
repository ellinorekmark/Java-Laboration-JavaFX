package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

public class MyCircle extends MyShape{
    public MyCircle(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public MyShape copy(MyShape shape) {
        return new MyCircle(shape.getX(), shape.getY(), shape.getSize(), shape.getColor());
    }

    @Override
    public boolean compare(double x, double y) {
        double centerX = getX()+(getSize()/2);
        double centerY = getY()+(getSize()/2);
        double radius = getSize()/2;

        return !((((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY))>(radius*radius)));
    }

    @Override
    public void draw(GraphicsContext context) {

        context.setFill(getColor());
        context.fillRoundRect(getX(), getY(), getSize(), getSize(), getSize(), getSize());
    }

    @Override
    public String toSVG() {
        System.out.println(toHexString(getColor()));
        return "<circle cx=\""+getX()+"\" cy=\""+getY()+"\" r=\""+getSize()/2+"\" fill=\""+toHexString(getColor())+"\"/>";

    }

    @Override
    public String networkString() {
        return ""+getClass().getSimpleName()+"\""+getX()+"\""+getY()+"\""+getSize()+"\""+getColor().toString();
    }

}
