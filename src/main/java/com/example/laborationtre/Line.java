package com.example.laborationtre;

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
}