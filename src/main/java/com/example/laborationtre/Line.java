package com.example.laborationtre;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Line extends MyShape {
    double startX;
    double startY;
    double endX;
    double endY;
    double width;
    Color color;

    public Line(double startX, double startY, double endX, double endY, double width, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.color = color;
    }

    public String toSVG(){
        System.out.println("<line x1=\""+startX+"\" x2=\""+endX+"\" y1=\""+startY+"\" y2=\""+endY+"\" stroke=\""+color.getRed()+","+color.getGreen()+","+color.getBlue()+"\" stroke-width=\""+width+"\"/>");
        return "<line x1=\""+startX+"\" x2=\""+endX+"\" y1=\""+startY+"\" y2=\""+endY+"\" stroke=\""+color.getRed()+color.getGreen()+color.getBlue()+"\" stroke-width=\""+width+"\"/>";
    }

    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, MouseEvent mouseEvent) {
        Line lineShape = (Line) shape;
        double rightEdge;
        double leftEdge;
        double top;
        double bottom;

        if (lineShape.startX-lineShape.endX<0){
            rightEdge = lineShape.endX;
            leftEdge = lineShape.startX;
        }
        else{
            rightEdge = lineShape.startX;
            leftEdge = lineShape.endX;
        }

        if (lineShape.startY-lineShape.endY<0){
            top = lineShape.endY;
            bottom = lineShape.startY;
        }
        else{
            top = lineShape.startY;
            bottom = lineShape.endY;
        }
        return mouseEvent.getX() > leftEdge && mouseEvent.getX() < rightEdge && mouseEvent.getY() > bottom && mouseEvent.getY() < top;
    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        Line myLine = (Line)shape;
        myLine.color = color;
        myLine.width = size;
        return myLine;
    }
}