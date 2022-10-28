package com.example.laborationtre;



import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

import static com.example.laborationtre.ShapesModel.*;



public class HelloController {
    ShapesModel shapesModel = new ShapesModel();

    public Canvas canvas;
    public Stage stage;
    @FXML
    public Button undoButton;
    public Button redoButton;
    public Button saveButton;
    public Button clearButton;
    public ColorPicker colorChoice;
    public static GraphicsContext context;
    public ChoiceBox<String> toolsList;
    public Slider pixelSlider;
    public ToggleButton editTool;
    public Label pixelSizeInfo;






    public void initialize() {
        context = canvas.getGraphicsContext2D();
        saveButton.disableProperty().bind(Bindings.isEmpty(shapesModel.shapeStack));
        colorChoice.valueProperty().bindBidirectional(shapesModel.color);
        pixelSlider.valueProperty().bindBidirectional(shapesModel.size);
        toolsList.valueProperty().bindBidirectional(shapesModel.tool);
        toolsList.disableProperty().bind(editTool.selectedProperty());
    }

    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {

        if (editTool.isSelected()) {
            shapesModel.tryEditShape(mouseEvent);
        } else {
            shapesModel.createShape(mouseEvent);
        }
        updateCanvas();
    }
    public void updateCanvas() {
        context.clearRect(0, 0, 500, 500);
        for (Shape shape : shapesModel.shapeStack) {
            if (shape.getClass().equals(MyCircle.class)) {
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
        MyCircle circleShape = (MyCircle) shape;
        context.setFill(shape.getFill());
        context.fillRoundRect(circleShape.getCenterX(), circleShape.getCenterY(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius(), circleShape.getRadius());
    }
    public void onCanvasDrag(MouseEvent mouseEvent) {

    }

    public void onCanvasRelease(MouseEvent mouseEvent) {
        if (shapesModel.shapeTool.equals(ToolOption.LINE)) {
            shapesModel.finishLine(mouseEvent);
            updateCanvas();
        }
    }

    @FXML
    void undoCanvas() {
        if (!shapesModel.shapeStack.isEmpty()) {
            shapesModel.redoShapeStack.add(shapesModel.shapeStack.get(shapesModel.shapeStack.size()-1));
            shapesModel.shapeStack.remove(shapesModel.shapeStack.size()-1);
            updateCanvas();
        }
    }
    @FXML
    void redoCanvas() {

        if (!shapesModel.redoShapeStack.isEmpty()) {
            shapesModel.shapeStack.add(shapesModel.redoShapeStack.get(shapesModel.redoShapeStack.size()-1));
            shapesModel.redoShapeStack.remove(shapesModel.redoShapeStack.size()-1);
            updateCanvas();
        }
    }

    public void toolChoiceMade() {
        if (toolsList.getValue().equalsIgnoreCase("line")) {
            shapesModel.shapeTool = ToolOption.LINE;

        } else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            shapesModel.shapeTool = ToolOption.FREEDRAW;

        } else if (toolsList.getValue().equalsIgnoreCase("Circle")) {
            shapesModel.shapeTool = ToolOption.CIRCLE;

        } else if (toolsList.getValue().equalsIgnoreCase("Square")) {
            shapesModel.shapeTool = ToolOption.SQUARE;
        }
    }

    public void clearCanvas() {
        shapesModel.shapeStack.clear();
        updateCanvas();
    }

    public void editToolActive() {
        toolsList.setValue("Circle");
    }

    public void updatePixelSize() {
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
    }


    public void onSaveAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG","*.svg"));
        File filepath = fileChooser.showSaveDialog(stage);

        if(filepath!=null) {
            java.nio.file.Path path = java.nio.file.Path.of(filepath.toURI());
            shapesModel.saveToFile(path);
        }

    }
}





