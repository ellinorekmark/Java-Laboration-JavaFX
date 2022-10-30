package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MyCircle extends MyShape{

    double centerX;
    double centerY;
    double radius;
    Color color;

    public MyCircle(double centerX, double centerY, double radius, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }





    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent) {
        MyCircle circleShape = (MyCircle) shape;
        double startX = circleShape.getCenterX()+circleShape.getRadius();
        double endX = circleShape.getCenterX()-circleShape.getRadius();
        double startY = circleShape.getCenterY()+circleShape.getRadius();
        double endY = circleShape.getCenterY()-circleShape.getRadius();
        return mouseEvent.getX() > endX && mouseEvent.getX() < startX && mouseEvent.getY() > endY && mouseEvent.getY() < startY;
    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        MyCircle myCircle = (MyCircle)shape;
        myCircle.setFill(color);
        myCircle.setRadius(size);
        return myCircle;

    }

    @Override
    public String toSVG() {
        return "<circle cx=\""+centerX+"\" cy=\""+centerY+"\" r=\""+radius+"\" fill=\""+color.getRed()+","+color.getGreen()+","+color.getBlue()+"\"/>";
    }

}
