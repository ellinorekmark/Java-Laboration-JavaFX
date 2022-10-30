package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

abstract public class MyShape extends Shape {


    abstract public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent);


    abstract public MyShape editShape(MyShape shape, Color color,double size);
    abstract public String toSVG();
}
