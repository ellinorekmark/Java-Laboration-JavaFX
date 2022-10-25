package com.example.laborationtre;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FreeDrawShape extends BaseShape {

ArrayList<Position> onDrag;
Position onRelease;


    public FreeDrawShape(Position position, Color color, double size, HelloController.Tools tool, ArrayList<Position>onDrag, Position onRelease) {
        super(position, color, size, tool);
        this.onDrag = onDrag;
        this.onRelease= onRelease;
    }
}
