package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

public class MyCircle extends MyShape{
    public MyCircle(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }


    @Override
    public boolean compare(double x, double y) {
        double startX = getX();
        double endX = getX()+getSize();
        double startY = getY();
        double endY = getY()+getSize();

        double centerX = getX()+(getSize()/2);
        double centerY = getY()+(getSize()/2);
        double radius = getSize()/2;


        return x > startX && x < endX && y > startY && y < endY;
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
        return ""+getClass().getSimpleName()+"\""+getX()+"\""+getY()+"\""+getSize()+"\""+getColor().getRed()+"\""+getColor().getGreen()+"\""+getColor().getBlue();
    }

}
