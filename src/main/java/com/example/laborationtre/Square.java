package com.example.laborationtre;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends MyShape {
    public Square(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }



    public String toSVG(){
        return "<path d=\"M "+getX()+" "+getY()+" h "+getSize()+" v "+getSize()+" h "+(-getSize())+" Z\" fill=\""+toHexString(getColor())+"\" stroke=\"transparent\"/>";
    }

    @Override
    public boolean compare(double x, double y) {

            double startX = getX();
            double endX = getX()+getSize();
            double startY = getY();
            double endY = getY()+getSize();
            return x < endX && x > startX && y < endY && y > startY;

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getX(), getY(), getSize(), getSize());
    }

    @Override
    public String networkString() {
        return ""+getClass().getSimpleName()+"\""+getX()+"\""+getY()+"\""+getSize()+"\""+getColor().getRed()+"\""+getColor().getGreen()+"\""+getColor().getBlue();
    }


}
