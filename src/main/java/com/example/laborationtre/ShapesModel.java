package com.example.laborationtre;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


import java.util.Stack;




public class ShapesModel {

    ObjectProperty<Number> size = new SimpleObjectProperty<>(50);
    ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    ObjectProperty<String> tool = new SimpleObjectProperty<>("Circle");
    ObjectProperty<Double> mousePressX = new SimpleObjectProperty<>();
    ObjectProperty<Double> mousePressY = new SimpleObjectProperty<>();



     ObservableList<Shape> shapeStack = FXCollections.observableArrayList();
    ObservableList<Shape> redoShapeStack = FXCollections.observableArrayList();



     public double lineStartX;
     public double lineStartY;

    public ToolOption shapeTool;

    public void addToStack(Shape shape) {
        shapeStack.add(shape);

    }
    public void createShape(MouseEvent mouseEvent){
        switch(shapeTool){
            case LINE -> createLineStart(mouseEvent);
            case CIRCLE -> createCircle(mouseEvent);
            case SQUARE -> createSquare(mouseEvent);
        }
    }
    private void createSquare(MouseEvent mouseEvent) {
        addToStack(new Square(mouseEvent.getX() - (size.get().doubleValue() / 2), mouseEvent.getY() - (size.get().doubleValue() / 2), size.get().doubleValue(), color.get()));
    }

    private void createLineStart(MouseEvent mouseEvent) {
        lineStartX = mouseEvent.getX();
        lineStartY = mouseEvent.getY();
    }

    public void finishLine(MouseEvent mouseEvent){
        addToStack(new Line(lineStartX, lineStartY, mouseEvent.getX(), mouseEvent.getY(), size.get().doubleValue(), color.get()));
    }

    private void createCircle(MouseEvent mouseEvent) {
        addToStack(new Circle(mouseEvent.getX() - (size.get().doubleValue() / 2), mouseEvent.getY() - (size.get().doubleValue() / 2), size.get().doubleValue(), color.getValue()));
    }

    public void tryEditShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shapeStack.size(); i++) {
            if (shapeStack.get(i).getClass().equals(Circle.class)) {
                if (compareCircleAndMouseEvent((Circle)shapeStack.get(i), mouseEvent)) {
                    editCircle((Circle)shapeStack.get(i), i, color.get(), size.get().doubleValue());
                }
            } else if (shapeStack.get(i).getClass().equals(Square.class)) {
                if (compareSquareAndMouseEvent((Square)shapeStack.get(i), mouseEvent)) {
                    editSquare((Square) shapeStack.get(i), i, color.get(), size.get().doubleValue());
                }
            } else if (shapeStack.get(i).getClass().equals(Line.class)) {
                if (compareLineAndMouseEvent((Line)shapeStack.get(i), mouseEvent)) {
                    editLine((Line) shapeStack.get(i), i, color.get(), size.get().doubleValue());
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

    public void editCircle(Circle shape, int i, Color color, double size) {
        shape.setFill(color);
        shape.setRadius(size);
        shapeStack.set(i, shape);
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

    public void saveToFile(java.nio.file.Path file) {
    }


    public enum ToolOption {
        LINE,
        FREEDRAW,
        CIRCLE,
        SQUARE

    }
}




