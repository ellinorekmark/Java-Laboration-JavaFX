package com.example.laborationtre;


import javafx.scene.paint.Color;
import com.example.laborationtre.HelloController.*;

public class BaseShape {
Position position;
Color color;
double size;
Tools tool;

    public BaseShape(Position position, Color color, double size, Tools tool) {
        this.position = position;
        this.color = color;
        this.size = size;
        this.tool = tool;
    }
}