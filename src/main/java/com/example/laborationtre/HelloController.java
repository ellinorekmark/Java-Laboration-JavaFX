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
            Shape newShape = null;
            if (tool.equals(Tools.CIRCLE)) {
                newShape = new Circle(mouseEvent.getX() - (pixelSlider.getValue() / 2), mouseEvent.getY() - (pixelSlider.getValue() / 2), pixelSlider.getValue(), colorChoice.getValue());
                addToStack(newShape);
            } else if (tool.equals(Tools.SQUARE)) {
                newShape = new Square(mouseEvent.getX() - (pixelSlider.getValue() / 2), mouseEvent.getY() - (pixelSlider.getValue() / 2), pixelSlider.getValue(), colorChoice.getValue());
                addToStack(newShape);
            } else if (tool.equals(Tools.LINE)) {
                startX = mouseEvent.getX();
                startY = mouseEvent.getY();
            }

        }
        updateCanvas();
    }



    private void tryEditShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shapeStack.size(); i++) {
            Shape shape = shapeStack.get(i);

            if (shape.getClass().equals(Circle.class)) {
                Circle circleShape = (Circle) shape;
                if (compareCircleAndMouseEvent(circleShape, mouseEvent)) {
                    editShape(shape, i);
                }
            } else if (shape.getClass().equals(Square.class)) {
                Square squareShape = (Square) shape;
                if (compareSquareAndMouseEvent(squareShape, mouseEvent)) {
                    editShape(shape, i);
                }
            } else if (shape.getClass().equals(Line.class)) {
                Line lineShape = (Line) shape;
                if (compareLineAndMouseEvent(lineShape, mouseEvent)) {
                    editShape(shape, i);
                }
            }

        }


    }

    public void editShape(Shape shape, int i) {
        if (shape.getClass().equals(Circle.class)) {
            editCircle(shape, i);
            updateCanvas();
        } else if (shape.getClass().equals(Square.class)) {

            Square temp = (Square) shape;
            temp.color = colorChoice.getValue();
            temp.size = pixelSlider.getValue();
            shapeStack.set(i, temp);
            updateCanvas();
        } else if (shape.getClass().equals(Line.class)) {
            Line temp = (Line) shape;
            temp.color = colorChoice.getValue();
            temp.width = pixelSlider.getValue();
            shapeStack.set(i, temp);
            updateCanvas();
        }

    }




    public void updateCanvas() {
        context.clearRect(0, 0, 500, 500);
        for (Shape shape : shapeStack) {
            if (shape.getClass().equals(Circle.class)) {
                addCircle(shape);
            } else if (shape.getClass().equals(Square.class)) {
                addSquare(shape);
            }
            if (shape.getClass().equals(Line.class)) {
                addLine(shape);
            }


        }
    }

    private void addLine(Shape shape) {

        context.beginPath();
        context.setStroke(((Line) shape).color);
        context.setLineWidth(((Line) shape).width);

        context.moveTo(((Line) shape).startX, ((Line) shape).startY);
        context.lineTo(((Line) shape).endX, ((Line) shape).endY);
        context.setLineCap(StrokeLineCap.ROUND);
        context.closePath();
        context.stroke();

    }

    private void addSquare(Shape shape) {
        if (shape != null) {
            Square squareShape = (Square) shape;
            context.setFill(squareShape.color);
            context.fillRect(squareShape.positionX, squareShape.positionY, squareShape.size, squareShape.size);
        }
    }

    private void addCircle(Shape shape) {

        if (shape != null) {
            Circle circleShape = (Circle) shape;
            context.setFill(shape.getFill());
            context.fillRoundRect(circleShape.getCenterX(), circleShape.getCenterY(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius());
        }
    }

    public void onCanvasDrag(MouseEvent mouseEvent) {

    }


    public void onCanvasRelease(MouseEvent mouseEvent) {
        if (tool.equals(Tools.LINE)) {
            Line lineShape = new Line(startX, startY, mouseEvent.getX(), mouseEvent.getY(), pixelSlider.getValue(), colorChoice.getValue());
            addToStack(lineShape);

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
            tool = Tools.LINE;

        } else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            tool = Tools.FREEDRAW;

        } else if (toolsList.getValue().equalsIgnoreCase("Circle")) {
            tool = Tools.CIRCLE;

        } else if (toolsList.getValue().equalsIgnoreCase("Square")) {
            tool = Tools.SQUARE;
        }
    }

    public void clearCanvas() {
        shapeStack.clear();
        updateCanvas();
    }

    public void editToolActive() {
        toolsList.setValue("Circle");
        if (editTool.isSelected()) {
            toolsList.setDisable(true);
        } else {
            toolsList.setDisable(false);
        }
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





