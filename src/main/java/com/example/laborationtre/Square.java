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

    public static boolean compareSquareAndMouseEvent(Square squareShape, MouseEvent mouseEvent){
        double startX = squareShape.positionX+(squareShape.size/2);
        double endX = squareShape.positionX-(squareShape.size/2);
        double startY = squareShape.positionY+squareShape.size;
        double endY = squareShape.positionY-squareShape.size;
        return mouseEvent.getX() > endX && mouseEvent.getX() < startX && mouseEvent.getY() > endY && mouseEvent.getY() < startY;
    }


}
