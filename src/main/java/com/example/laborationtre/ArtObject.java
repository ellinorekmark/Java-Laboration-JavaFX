package com.example.laborationtre;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ArtObject {

    MouseEvent mouseEvent;
    //MouseEvent mouseDrag;
   // MouseEvent mouseRelease;
    Color colorChoice;
    int pixelSize;
    Tool toolchoice;

    public ArtObject(MouseEvent event, Color colorChoice, int pixelSize, Tool toolchoice) {
        this.mouseEvent = event;

        this.colorChoice = colorChoice;
        this.pixelSize = pixelSize;
        this.toolchoice = toolchoice;
    }


}


enum Tool{
    LINE,
    FREEDRAW,

}