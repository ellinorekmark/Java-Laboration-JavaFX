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
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.color = color;
    }
}