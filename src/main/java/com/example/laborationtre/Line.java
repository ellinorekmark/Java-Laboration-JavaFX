package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Line extends MyShape {
    double endX;
    double endY;
    public Line(double x, double y, double endX, double endY, double size, Color color) {
        super(x, y, size, color);
        this.endX = endX;
        this.endY = endY;
    }


    public String toSVG(){
        return "<line x1=\""+getX()+"\" y1=\""+getY()+"\" x2=\""+getEndX()+"\" y2=\""+getEndY()+"\" stroke=\""+toHexString(getColor())+"\" stroke-width=\""+getSize()+"\"/>";

    }

    @Override
    public boolean compareShapeAndMouseEvent(MyShape shape, double x, double y) {
        Line lineShape = (Line) shape;
        double rightEdge;
        double leftEdge;
        double top;
        double bottom;

        if (lineShape.getX()-lineShape.getEndX()<0){
            rightEdge = lineShape.getEndX();
            leftEdge = lineShape.getX();
        }
        else{
            rightEdge = lineShape.getX();
            leftEdge = lineShape.getEndX();
        }

        if (lineShape.getY()-lineShape.getEndY()<0){
            top = lineShape.getEndY();
            bottom = lineShape.getY();
        }
        else{
            top = lineShape.getY();
            bottom = lineShape.getEndY();
        }
        return x > leftEdge && x < rightEdge && y > bottom && y < top;
    }

    @Override
    public void draw(GraphicsContext context, MyShape shape) {
        context.beginPath();
        context.setStroke(((Line) shape).getColor());
        context.setLineWidth(((Line) shape).getSize());
        context.moveTo(((Line) shape).getX(), ((Line) shape).getY());
        context.lineTo(((Line) shape).getEndX(), ((Line) shape).getEndY());
        context.closePath();
        context.stroke();
    }

    @Override
    public MyShape editShape(MyShape shape, Color color, double size) {
        Line myLine = (Line)shape;
        myLine.setColor(color);
        myLine.setSize(size);
        return myLine;
    }

    public double getEndX() {
        return endX;
    }



    public double getEndY() {
        return endY;
    }


}