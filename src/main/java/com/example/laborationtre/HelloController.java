package com.example.laborationtre;


import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static com.example.laborationtre.ShapesModel.*;


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
    public double px;
    public Tools tool;


    public Label pixelSizeInfo;
    public ToggleButton selectTool;


    public void initialize() {
        context = canvas.getGraphicsContext2D();
        //sets some choices in the tools options etc. to start with
        colorChoice.setValue(Color.BLACK);
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
        toolsList.setValue("Circle");
    }

    @FXML
    protected void setNewPixelSize() {
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
        px = pixelSlider.getValue();
    }


    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {

        if (selectTool.isSelected()) {
            trySelectShape(mouseEvent);
        } else {
            Shape newShape = null;
            if (tool.equals(Tools.CIRCLE)) {
                newShape = new Circle(mouseEvent.getX() - (pixelSlider.getValue() / 2), mouseEvent.getY() - (pixelSlider.getValue() / 2), pixelSlider.getValue(), colorChoice.getValue());
            }
            shapeStack.add(newShape);
            shapeUndoStack.clear();
            updateCanvas();
        }


//        if(tool.equals(Tools.SQUARESTAMP)){
//            context.setFill(colorChoice.getValue());
//            context.fillRect(mouseEvent.getX()-(pixelSlider.getValue()/2), mouseEvent.getY()-(pixelSlider.getValue()/2), pixelSlider.getValue(), pixelSlider.getValue());
//        }
//        else {
//
//            context.setStroke(colorChoice.getValue());
//            context.setLineWidth(px);
//            context.setLineCap(StrokeLineCap.ROUND);
//            context.beginPath();
//
//            context.moveTo(mouseEvent.getX(), mouseEvent.getY());
//            context.stroke();
    }

    private void trySelectShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shapeStack.size(); i++) {
            Shape shape = shapeStack.get(i);
            if (shape.getClass().equals(Circle.class)) {
                Circle circleShape = (Circle) shape;
                if (compareCircleAndMouseEvent(circleShape, mouseEvent)) {
                    editShape(shape, i);
                }
            }
        }
    }

    public void editShape(Shape shape, int i) {
        shape.setFill(colorChoice.getValue());
        if (shape.getClass().equals(Circle.class)) {
            Circle temp = (Circle) shape;
            temp.setRadius(pixelSlider.getValue());
            shapeStack.set(i, temp);
            updateCanvas();
        }

    }


    private void updateCanvas() {
        context.clearRect(0, 0, 500, 500);
        for (Shape shape : shapeStack) {
            if (shape.getClass().equals(Circle.class)) {
                addCircle(shape);
            }


        }
    }

    private void addCircle(Shape shape) {

        if(shape!=null) {
            Circle circleShape = (Circle) shape;
            context.setFill(shape.getFill());
            context.fillRoundRect(circleShape.getCenterX(), circleShape.getCenterY(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius());
        }
    }

    public void onCanvasDrag(MouseEvent mouseEvent) {
//        if(tool.equals(Tools.FREEDRAW)){
//        context.lineTo(mouseEvent.getX(), mouseEvent.getY());
//        context.stroke();
//        }
    }


    public void onCanvasRelease(MouseEvent mouseEvent) {
//        if(tool.equals(Tools.LINES) || tool.equals(Tools.FREEDRAW)){
//            context.lineTo(mouseEvent.getX(), mouseEvent.getY());
//            context.stroke();
//            context.closePath();
//        }
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

        if(!shapeUndoStack.empty()) {
            shapeStack.add(shapeUndoStack.peek());
            shapeUndoStack.pop();
            updateCanvas();
        }
    }

    public void toolChoiceMade() {
        if (toolsList.getValue().equalsIgnoreCase("lines")) {
            tool = Tools.LINES;

        } else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            tool = Tools.FREEDRAW;

        } else if (toolsList.getValue().equalsIgnoreCase("Circle")) {
            tool = Tools.CIRCLE;

        }
    }

    public void clearCanvas() {
        shapeStack.clear();
        updateCanvas();
    }


    public enum Tools {
        LINES,
        FREEDRAW,
        CIRCLE
    }

}





