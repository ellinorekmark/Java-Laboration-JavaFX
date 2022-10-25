package com.example.laborationtre;

import javafx.event.ActionEvent;


import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;




public class HelloController {

    public Canvas canvas;
    @FXML
    public Button undoButton;
    public Button redoButton;
    public Button saveButton;
    public ColorPicker colorChoice;

    public static GraphicsContext context;
    public ChoiceBox<String> toolsList;



    public Slider pixelSlider;
    public double px;
    public Tools tool;

    public Label pixelSizeInfo;



    public void initialize() {
        context = canvas.getGraphicsContext2D();
        //sets some choices in the tools options etc. to start with
        colorChoice.setValue(Color.BLACK);
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");

        toolsList.setValue("Free Draw");



    }
    @FXML
    protected void setNewPixelSize(){
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
        px = pixelSlider.getValue();
    }



    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {

//        artMemory.add(new ArtObject(canvas));
//        addContext(context);
        if(tool.equals(Tools.SQUARESTAMP)){
            context.setFill(colorChoice.getValue());
            context.fillRect(mouseEvent.getX()-(pixelSlider.getValue()/2), mouseEvent.getY()-(pixelSlider.getValue()/2), pixelSlider.getValue(), pixelSlider.getValue());
        }
        else {

            context.setStroke(colorChoice.getValue());
            context.setLineWidth(px);
            context.setLineCap(StrokeLineCap.ROUND);
            context.beginPath();

            context.moveTo(mouseEvent.getX(), mouseEvent.getY());
            context.stroke();
        }



    }
@FXML
    void undoCanvas(ActionEvent event) {

    }

    public void toolChoiceMade() {
        if (toolsList.getValue().equalsIgnoreCase("lines")){
            tool = Tools.LINES;
            System.out.println(tool);
        } else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            tool = Tools.FREEDRAW;
            System.out.println(tool);
        }
        else if(toolsList.getValue().equalsIgnoreCase("Stamp - Square")){
            tool = Tools.SQUARESTAMP;
            System.out.println(tool);
        }
    }

    public void onCanvasDrag(MouseEvent mouseEvent) {
        if(tool.equals(Tools.FREEDRAW)){
        context.lineTo(mouseEvent.getX(), mouseEvent.getY());
        context.stroke();
        }
    }

    public void onCanvasRelease(MouseEvent mouseEvent) {
        if(tool.equals(Tools.LINES) || tool.equals(Tools.FREEDRAW)){
            context.lineTo(mouseEvent.getX(), mouseEvent.getY());
            context.stroke();
            context.closePath();
        }


    }


    public enum Tools{
        LINES,
        FREEDRAW,
        SQUARESTAMP
    }









}

