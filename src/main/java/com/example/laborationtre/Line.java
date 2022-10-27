package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Line extends Shape {
    double startX;
    double startY;
    double endX;
    double endY;
    double width;
    Color color;

    public Line(double startX, double startY, double endX, double endY, double width, Color color) {
        this.startX = startX;
        System.out.println("start X "+ startX);
        this.startY = startY;
        System.out.println("start Y "+ startY);
        this.endX = endX;
        System.out.println("end X "+ endX);
        this.endY = endY;
        System.out.println("end Y "+ endY);
        this.width = width;
        this.color = color;
    }

    public static boolean compareLineAndMouseEvent(Line lineShape, MouseEvent mouseEvent) { //basically checks a rectangle around the line, close enough.

        double startX = lineShape.startX;
        double startY = lineShape.startY;
        double endX = lineShape.endX;
        double endY = lineShape.endY;
        double rightEdge;
        double leftEdge;
        double top;
        double bottom;

        if (startX-endX<0){
            rightEdge = endX;
            leftEdge = startX;
        }
        else{
            rightEdge = startX;
            leftEdge = endX;
        }

        if (startY-endY<0){
            top = endY;
            bottom = startY;
        }
        else{
            top = startY;
            bottom = endY;
        }
        return mouseEvent.getX() > leftEdge && mouseEvent.getX() < rightEdge && mouseEvent.getY() > bottom && mouseEvent.getY() < top;
    }


}