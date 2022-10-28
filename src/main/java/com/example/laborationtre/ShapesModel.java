package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Stack;




public class ShapesModel {


     public Stack<Shape> shapeStack = new Stack<>();
     public Stack<Shape> shapeUndoStack = new Stack<>();



     public double lineStartX;
     public double lineStartY;

    public Color shapeColor;
    public double shapeSize;
    public ToolOption shapeTool;

    public void addToStack(Shape shape) {
        shapeStack.add(shape);
        shapeUndoStack.clear();


    }
    public void createShape(MouseEvent mouseEvent, Color color, double size){
        switch(shapeTool){
            case LINE -> createLineStart(mouseEvent);
            case CIRCLE -> createCircle(mouseEvent, color, size);
            case SQUARE -> createSquare(mouseEvent, color, size);
        }

    }
    private void createSquare(MouseEvent mouseEvent, Color color, double size) {
        addToStack(new Square(mouseEvent.getX() - (shapeSize / 2), mouseEvent.getY() - (shapeSize / 2), size, color));
    }

    private void createLineStart(MouseEvent mouseEvent) {
        lineStartX = mouseEvent.getX();
        lineStartY = mouseEvent.getY();
    }

    public void finishLine(MouseEvent mouseEvent){
        addToStack(new Line(lineStartX, lineStartY, mouseEvent.getX(), mouseEvent.getY(), shapeSize, shapeColor));
    }


    private void createCircle(MouseEvent mouseEvent, Color color, double size) {
        addToStack(new Circle(mouseEvent.getX() - (size / 2), mouseEvent.getY() - (size / 2), size, color));
    }

    public void tryEditShape(MouseEvent mouseEvent, Color color, double size) {

        for (int i = 0; i < shapeStack.size(); i++) {

            if (shapeStack.get(i).getClass().equals(Circle.class)) {
                if (compareCircleAndMouseEvent((Circle)shapeStack.get(i), mouseEvent)) {
                    editCircle((Circle)shapeStack.get(i), i, color, size);
                }
            } else if (shapeStack.get(i).getClass().equals(Square.class)) {
                if (compareSquareAndMouseEvent((Square)shapeStack.get(i), mouseEvent)) {
                    editSquare((Square) shapeStack.get(i), i, color, size);
                }
            } else if (shapeStack.get(i).getClass().equals(Line.class)) {
                if (compareLineAndMouseEvent((Line)shapeStack.get(i), mouseEvent)) {
                    editLine((Line) shapeStack.get(i), i, color, size);
                }
            }

        }
    }

    public boolean compareCircleAndMouseEvent(Circle circleShape, MouseEvent mouseEvent){
        double startX = circleShape.getCenterX()+circleShape.getRadius();
        double endX = circleShape.getCenterX()-circleShape.getRadius();
        double startY = circleShape.getCenterY()+circleShape.getRadius();
        double endY = circleShape.getCenterY()-circleShape.getRadius();
        return mouseEvent.getX() > endX && mouseEvent.getX() < startX && mouseEvent.getY() > endY && mouseEvent.getY() < startY;

    }



    public void editCircle(Shape shape, int i, Color color, double size) {
        shape.setFill(color);
        Circle temp = (Circle) shape;
        temp.setRadius(size);
        shapeStack.set(i, temp);
    }


    public boolean compareSquareAndMouseEvent(Square shape, MouseEvent mouseEvent){
        double startX = shape.positionX;
        double endX = shape.positionX+shape.size;
        double startY = shape.positionY;
        double endY = shape.positionY+shape.size;
        return mouseEvent.getX() < endX && mouseEvent.getX() > startX && mouseEvent.getY() < endY && mouseEvent.getY() > startY;
    }

    public boolean compareLineAndMouseEvent(Line shape, MouseEvent mouseEvent) { //basically checks a rectangle around the line, close enough.
        double rightEdge;
        double leftEdge;
        double top;
        double bottom;

        if (shape.startX-shape.endX<0){
            rightEdge = shape.endX;
            leftEdge = shape.startX;
        }
        else{
            rightEdge = shape.startX;
            leftEdge = shape.endX;
        }

        if (shape.startY-shape.endY<0){
            top = shape.endY;
            bottom = shape.startY;
        }
        else{
            top = shape.startY;
            bottom = shape.endY;
        }
        return mouseEvent.getX() > leftEdge && mouseEvent.getX() < rightEdge && mouseEvent.getY() > bottom && mouseEvent.getY() < top;
    }


    public void editSquare(Square shape, int i, Color color, double size) {
        shape.color = color;
        shape.size = size;
        shapeStack.set(i,shape);
    }
    public void editLine(Line shape, int i, Color color, double size) {
        shape.color = color;
        shape.width = size;
        shapeStack.set(i, shape);
    }


    public enum ToolOption {
        LINE,
        FREEDRAW,
        CIRCLE,
        SQUARE
    }



}




