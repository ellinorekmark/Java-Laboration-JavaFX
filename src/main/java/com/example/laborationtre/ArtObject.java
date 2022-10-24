package com.example.laborationtre;


import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ArtObject {

    public ArtObject(Canvas savedCanvas) {
        this.savedCanvas = savedCanvas;
    }

    Canvas savedCanvas;

}