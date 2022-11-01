package com.example.laborationtre;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class ShapesModel {

    ObjectProperty<Number> size = new SimpleObjectProperty<>(50);
    ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    ObjectProperty<ToolOption> tool = new SimpleObjectProperty<>();


    ObservableList<MyShape> shapeStack = FXCollections.observableArrayList();
    ObservableList<MyShape> redoShapeStack = FXCollections.observableArrayList();

    ArrayList redoStack = new ArrayList<>();


    public double x;
    public double y;

    public double endX;
    public double endY;


    public void addToStack(MyShape shape) {
        redoStack.add(List.of(shapeStack));
        shapeStack.add(shape);
    }

    public void createShape(MouseEvent mouseEvent) {
        switch (tool.getValue()) {
            case LINE -> createLineStart(mouseEvent);
            case CIRCLE -> createCircle(mouseEvent);
            case SQUARE -> createSquare(mouseEvent);
        }
    }

    private void createSquare(MouseEvent mouseEvent) {
        addToStack(new Square(mouseEvent.getX() - (size.get().doubleValue() / 2), mouseEvent.getY() - (size.get().doubleValue() / 2), size.get().doubleValue(), color.get()));
    }

    private void createLineStart(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    public void finishLine(MouseEvent mouseEvent) {
        addToStack(new Line(x, y, mouseEvent.getX(), mouseEvent.getY(), size.get().doubleValue(), color.get()));
    }

    private void createCircle(MouseEvent mouseEvent) {
        addToStack(new MyCircle(mouseEvent.getX() - (size.get().doubleValue() / 2), mouseEvent.getY() - (size.get().doubleValue() / 2), size.get().doubleValue(), color.getValue()));
    }

    public void tryEditShape(MouseEvent mouseEvent) {
        redoStack.add(List.of(shapeStack));
        for (int i = 0; i < shapeStack.size(); i++) {
            MyShape shape = shapeStack.get(i);
            if (shape.compareShapeAndMouseEvent(shape, mouseEvent)) {
                shapeStack.set(i, shape.editShape(shape, color.get(), size.get().doubleValue()));
            }
        }
    }



    public void saveToFile(java.nio.file.Path file) {

        StringBuilder string = new StringBuilder("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        for (MyShape shape : shapeStack) {
            string.append(shape.toSVG()).append("\n");
        }
        string.append("</svg>");

        try {
            Files.writeString(file, string.toString());
        } catch (IOException e) {
            System.out.println("didn't work");
        }

    }


    public enum ToolOption {
        LINE{
            @Override
            public String toString() {
                return "Line";
            }
        },
        FREEDRAW{
            @Override
            public String toString() {
                return "Free Draw";
            }
        },
        CIRCLE{
            @Override
            public String toString() {
                return "Circle";
            }
        },
        SQUARE{
            @Override
            public String toString() {
                return "Square";
            }
        }
    }

}




