package com.example.laborationtre;

import javafx.event.EventHandler;

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
    public ColorPicker colorChoice;

    public static GraphicsContext context;
    public ChoiceBox<String> toolsList;


    public EventHandler<MouseEvent> onLinesMousePressedEvent;

    public EventHandler<MouseEvent> onLinesMouseReleasedEvent;

    public EventHandler<MouseEvent> onMouseDraggedEvent;

    public Slider pixelSlider;
    public Label pixelSizeInfo;


    public void initialize() {
        context = canvas.getGraphicsContext2D();
        //sets some choices in the tools options etc. to start with
        colorChoice.setValue(Color.BLACK);
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");

        toolsList.setValue("Free Draw");


        onLinesMousePressedEvent = (MouseEvent mouseEvent) -> {
            if(toolsList.getValue().equalsIgnoreCase("Stamp - Square")){
                context.setFill(colorChoice.getValue());
                context.fillRect(mouseEvent.getX()-(pixelSlider.getValue()/2), mouseEvent.getY()-(pixelSlider.getValue()/2), pixelSlider.getValue(), pixelSlider.getValue());
            }
            else {

                context.setStroke(colorChoice.getValue());
                context.setLineWidth(pixelSlider.getValue());
                context.setLineCap(StrokeLineCap.ROUND);
                context.beginPath();

                context.moveTo(mouseEvent.getX(), mouseEvent.getY());
                context.stroke();
            }

        };
        onLinesMouseReleasedEvent = mouseEvent -> {
            context.lineTo(mouseEvent.getX(), mouseEvent.getY());
            context.stroke();
            context.closePath();
        };
        onMouseDraggedEvent = mouseEvent -> {
            context.lineTo(mouseEvent.getX(), mouseEvent.getY());
            context.stroke();
        };

    }
    @FXML
    protected void setNewPixelSize(){
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
    }



    @FXML
    protected void onCanvasPress() {


        if (toolsList.getValue().equalsIgnoreCase("lines")) {

            canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEvent);
            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, onLinesMousePressedEvent);
            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, onLinesMouseReleasedEvent);
        }
        else if (toolsList.getValue().equalsIgnoreCase("free draw")) {
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEvent);
            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, onLinesMousePressedEvent);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, onLinesMouseReleasedEvent);
        }
        else if (toolsList.getValue().equalsIgnoreCase("Stamp - Square")) {
            canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEvent);
            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, onLinesMousePressedEvent);
            canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, onLinesMouseReleasedEvent);
        }

    }

}

