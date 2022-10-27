package com.example.laborationtre;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import static com.example.laborationtre.Line.*;
import static com.example.laborationtre.ShapesModel.*;
import static com.example.laborationtre.Square.*;


public class HelloController {

    public Canvas canvas;
    @FXML
    public Button undoButton;
    public Button redoButton;
    public Button saveButton;
    public Button clearButton;
    public ColorPicker colorChoice;

    public static GraphicsContext context;
    public ChoiceBox<String> toolsList;

    public Slider pixelSlider;

    public Tools tool;


    public Label pixelSizeInfo;
    public ToggleButton editTool;


    public void initialize() {
        context = canvas.getGraphicsContext2D();
        //sets some choices in the tools options etc. to start with
        colorChoice.setValue(Color.BLACK);
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
        toolsList.setValue("Circle");

    }



    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {


        if (editTool.isSelected()) {
            tryEditShape(mouseEvent);
        } else {
            createShape(mouseEvent);
        }
        updateCanvas();
    }













    public void updateCanvas() {
        context.clearRect(0, 0, 500, 500);
        for (Shape shape : shapeStack) {
            if (shape.getClass().equals(Circle.class)) {
                drawCircle(shape);
            } else if (shape.getClass().equals(Square.class)) {
                drawSquare(shape);
            }
            if (shape.getClass().equals(Line.class)) {
                drawLine(shape);
            }
        }
    }

    private void drawLine(Shape shape) {
        context.beginPath();
        context.setStroke(((Line) shape).color);
        context.setLineWidth(((Line) shape).width);
        context.moveTo(((Line) shape).startX, ((Line) shape).startY);
        context.lineTo(((Line) shape).endX, ((Line) shape).endY);
        context.closePath();
        context.stroke();
    }

    private void drawSquare(Shape shape) {
        Square squareShape = (Square) shape;
        context.setFill(squareShape.color);
        context.fillRect(squareShape.positionX, squareShape.positionY, squareShape.size, squareShape.size);
    }

    private void drawCircle(Shape shape) {
        Circle circleShape = (Circle) shape;
        context.setFill(shape.getFill());
        context.fillRoundRect(circleShape.getCenterX(), circleShape.getCenterY(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius());
    }
    public void onCanvasDrag(MouseEvent mouseEvent) {

    }

    public void onCanvasRelease(MouseEvent mouseEvent) {
        if (shapeTool.equals(ToolOption.LINE)) {
            finishLine(mouseEvent);
            updateCanvas();
        }
    }

    @FXML
    void undoCanvas() {
        if (!shapeStack.empty()) {
            shapeUndoStack.add(shapeStack.peek());
            shapeStack.pop();
            updateCanvas();
        }
    }

    @FXML
    void redoCanvas() {

        if (!shapeUndoStack.empty()) {
            shapeStack.add(shapeUndoStack.peek());
            shapeUndoStack.pop();
            updateCanvas();
        }
    }

    public void toolChoiceMade() {
        if (toolsList.getValue().equalsIgnoreCase("line")) {
            shapeTool = ToolOption.LINE;

        } else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            shapeTool = ToolOption.FREEDRAW;

        } else if (toolsList.getValue().equalsIgnoreCase("Circle")) {
            shapeTool = ToolOption.CIRCLE;

        } else if (toolsList.getValue().equalsIgnoreCase("Square")) {
            shapeTool = ToolOption.SQUARE;
        }
    }

    public void clearCanvas() {
        shapeStack.clear();
        updateCanvas();
    }

    public void editToolActive() {
        toolsList.setValue("Circle");
        toolsList.setDisable(editTool.isSelected());

    }

    public void updateColor() {
        shapeColor = colorChoice.getValue();
    }

    public void updatePixel() {
        shapeSize = pixelSlider.getValue();
    }


    public enum Tools {
        LINE,
        FREEDRAW,
        CIRCLE,
        SQUARE
    }

}





