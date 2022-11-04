package com.example.laborationtre;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapesModelTest {
    ShapesModel shapesModel = new ShapesModel();

    MyCircle newCircle = new MyCircle(30, 50, 20, Color.AQUA);

    @Test
    void testCreateShape() {
        shapesModel.color.set(Color.AQUA);
        shapesModel.size.set(30);
        shapesModel.tool.set(ShapesModel.ToolOption.SQUARE);
        shapesModel.createShape(30, 45);
        assertNotNull(shapesModel.shapeList);
        assert (shapesModel.shapeList.get(0).getClass().equals(Square.class));

    }

    @Test
    void testAddToStack() {
        shapesModel.addToStack(newCircle);
        assertNotNull(shapesModel.shapeList);

    }

    @Test
    void testEdit() {
        shapesModel.shapeList.add(newCircle);
        shapesModel.color.set(Color.BLACK);
        assertFalse (shapesModel.shapeList.isEmpty());
        assert (shapesModel.shapeList.get(0).getColor().equals(Color.AQUA));
        shapesModel.tryEditShape(32, 60);
        assert (shapesModel.shapeList.get(0).getColor().equals(Color.BLACK));
    }

}