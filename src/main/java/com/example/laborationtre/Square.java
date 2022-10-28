package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Square extends MyShape {
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

    public String circleToSVG(){
        return "<circle cx=\""+positionX+"\" cy=\""+positionY+"\" r=\""+size+"\" fill=\""+color+"\"/>";
    }
    public String toSVG(){
        return "<path d=\"M "+positionX+" "+positionY+" h "+size+" v "+size+" h "+(-size)+" Z\" fill=\""+color+"\" stroke=\"transparent\"/>";
    }

    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent) {
        Square squareShape = (Square) shape;
            double startX = squareShape.positionX;
            double endX = squareShape.positionX+squareShape.size;
            double startY = squareShape.positionY;
            double endY = squareShape.positionY+squareShape.size;
            return mouseEvent.getX() < endX && mouseEvent.getX() > startX && mouseEvent.getY() < endY && mouseEvent.getY() > startY;

    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        Square mySquare = (Square)shape;
        mySquare.color = color;
        mySquare.size = size;
        return mySquare;
    }
}
