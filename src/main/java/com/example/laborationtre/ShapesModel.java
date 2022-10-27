package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Stack;




public class ShapesModel {


    static public Stack<Shape> shapeStack = new Stack<>();
    static public Stack<Shape> shapeUndoStack = new Stack<>();

    static public Stack<ChangedShape> editHistoryStack = new Stack<>();
    static public Stack<ChangedShape> redoHistoryStack = new Stack<>();

    static public double startX;
    static public double startY;

    public static Color shapeColor;
    public static double shapeSize;


    public static void addToStack(Shape shape) {
        shapeStack.add(shape);
        shapeUndoStack.clear();
        redoHistoryStack.clear();

    }
    public static boolean compareCircleAndMouseEvent(Circle circleShape, MouseEvent mouseEvent){
        double startX = circleShape.getCenterX()+circleShape.getRadius();
        double endX = circleShape.getCenterX()-circleShape.getRadius();
        double startY = circleShape.getCenterY()+circleShape.getRadius();
        double endY = circleShape.getCenterY()-circleShape.getRadius();
        return mouseEvent.getX() > endX && mouseEvent.getX() < startX && mouseEvent.getY() > endY && mouseEvent.getY() < startY;

    }



    public static void editCircle(Shape shape, int i) {
        shape.setFill(shapeColor);
        Circle temp = (Circle) shape;
        temp.setRadius(shapeSize);
        shapeStack.set(i, temp);
    }





}




