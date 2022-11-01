package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MyCircle extends MyShape{
    public MyCircle(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }


    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent) {
        MyCircle circleShape = (MyCircle) shape;
        double startX = circleShape.getX()+circleShape.getSize();
        double endX = circleShape.getX()-circleShape.getY();
        double startY = circleShape.getY()+circleShape.getSize();
        double endY = circleShape.getY()-circleShape.getSize();
        return mouseEvent.getX() > endX && mouseEvent.getX() < startX && mouseEvent.getY() > endY && mouseEvent.getY() < startY;
    }

    @Override
    public void draw(GraphicsContext context, MyShape shape) {
        MyCircle circleShape = (MyCircle) shape;
        System.out.println(((MyCircle) shape).getColor());
        context.setFill(circleShape.getColor());
        context.fillRoundRect(circleShape.getX(), circleShape.getY(), circleShape.getSize(), circleShape.getSize(), circleShape.getSize(), circleShape.getSize());
    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        MyCircle myCircle = (MyCircle)shape;
        myCircle.setColor(color);
        myCircle.setSize(size);
        return myCircle;

    }

    @Override
    public String toSVG() {
        return "<circle cx=\""+getX()+"\" cy=\""+getY()+"\" r=\""+getSize()/2+"\" fill=\""+toHexString(getColor())+"\"/>";
    }

}
