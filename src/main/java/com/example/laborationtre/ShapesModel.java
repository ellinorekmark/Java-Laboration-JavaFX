package com.example.laborationtre;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Shape;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;




public class ShapesModel {

    ObjectProperty<Number> size = new SimpleObjectProperty<>(50);
    ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    ObjectProperty<String> tool = new SimpleObjectProperty<>("Circle");
    ObjectProperty<Double> mousePressX = new SimpleObjectProperty<>();
    ObjectProperty<Double> mousePressY = new SimpleObjectProperty<>();



     ObservableList<MyShape> shapeStack = FXCollections.observableArrayList();
    ObservableList<MyShape> redoShapeStack = FXCollections.observableArrayList();



     public double lineStartX;
     public double lineStartY;

    public ToolOption shapeTool;

    public void addToStack(MyShape shape) {
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
        addToStack(new MyCircle(mouseEvent.getX() - (size.get().doubleValue() / 2), mouseEvent.getY() - (size.get().doubleValue() / 2), size.get().doubleValue(), color.getValue()));
    }

    public void tryEditShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shapeStack.size(); i++) {
            MyShape shape = (MyShape) shapeStack.get(i);
            if(shape.compareShapeAndMouseEvent(shape,mouseEvent)){
                shapeStack.set(i,shape.editShape(shape, color.get(), size.get().doubleValue()));
            }
        }
    }

    public void saveToFile(java.nio.file.Path file) {

        String string="<?xml version=\"1.0\" standalone=\"no\"?>\n"+
                "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
        for (MyShape shape:shapeStack) {
            string=string+shape.toSVG()+"\n";
        }

        try {
            Files.writeString(file, string);
        } catch (IOException e) {
            System.out.println("didn't work");
        }

    }


    public enum ToolOption {
        LINE,
        FREEDRAW,
        CIRCLE,
        SQUARE

    }
}




