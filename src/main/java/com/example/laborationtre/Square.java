package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Square extends Shape {
    double positionX;
    double positionY;
    double size;
    Color color;

    public Square(double positionX, double positionY, double size, Color color) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
        this.color = color;
    }

}
