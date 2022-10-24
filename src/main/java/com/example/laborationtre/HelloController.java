package com.example.laborationtre;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.StrokeLineCap;



public class HelloController {
    public Canvas canvas;
    @FXML
    public Button undoButton;
    public Button redoButton;
    public ColorPicker colorChoice;

    public static GraphicsContext context;
    public ChoiceBox<String> toolsList;
    public ChoiceBox<String> pixelSize;

    public EventHandler<MouseEvent> onLinesMousePressedEvent;

    public EventHandler<MouseEvent> onLinesMouseReleasedEvent;

    public EventHandler<MouseEvent> onMouseDraggedEvent;


    public void initialize() {
        context = canvas.getGraphicsContext2D();

        onLinesMousePressedEvent = mouseEvent -> {
            context.setStroke(colorChoice.getValue());
            context.setLineWidth(Double.parseDouble(pixelSize.getValue()));
            context.setLineCap(StrokeLineCap.ROUND);
            context.beginPath();

            context.moveTo(mouseEvent.getX(), mouseEvent.getY());
            context.stroke();

        };
        onLinesMouseReleasedEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                context.lineTo(mouseEvent.getX(), mouseEvent.getY());
                context.stroke();
                context.closePath();
                //do stuff.
            }
        };
        onMouseDraggedEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                context.lineTo(mouseEvent.getX(), mouseEvent.getY());
                context.stroke();
                //do stuff.
            }
        };



    }



    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {


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
    }

}

