package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Square extends MyShape {
    public Square(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }



    public String toSVG(){
        return "<path d=\"M "+getX()+" "+getY()+" h "+getSize()+" v "+getSize()+" h "+(-getSize())+" Z\" fill=\""+toHexString(getColor())+"\" stroke=\"transparent\"/>";
    }

    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent) {
        Square squareShape = (Square) shape;
            double startX = squareShape.getX();
            double endX = squareShape.getX()+squareShape.getSize();
            double startY = squareShape.getY();
            double endY = squareShape.getY()+squareShape.getSize();
            return mouseEvent.getX() < endX && mouseEvent.getX() > startX && mouseEvent.getY() < endY && mouseEvent.getY() > startY;

    }

    @Override
    public void draw(GraphicsContext context, MyShape shape) {
        Square squareShape = (Square) shape;
        context.setFill(squareShape.getColor());
        context.fillRect(squareShape.getX(), squareShape.getY(), squareShape.getSize(), squareShape.getSize());
    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        Square mySquare = (Square)shape;
        mySquare.setColor(color);
        mySquare.setSize(size);
        return mySquare;
    }


}
