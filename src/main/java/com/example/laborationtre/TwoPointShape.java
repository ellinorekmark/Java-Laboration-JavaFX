package com.example.laborationtre;

import javafx.scene.paint.Color;

public class TwoPointShape extends BaseShape {
    Position onRelease;



    public TwoPointShape(Position position, Color color, double size, HelloController.Tools tool, Position onRelease) {
        super(position, color, size, tool);
        this.onRelease = onRelease;
    }
}
