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
        assertEquals(Square.class,shapesModel.shapeList.get(0).getClass());

    }

    @Test
    void testAddToStack() {
        shapesModel.addToLocalList(newCircle);
        assertNotNull(shapesModel.shapeList);

    }

    @Test
    void testEdit() {
        shapesModel.shapeList.add(newCircle);
        shapesModel.color.set(Color.BLACK);
        assertFalse (shapesModel.shapeList.isEmpty());
        assertEquals(Color.AQUA,shapesModel.shapeList.get(0).getColor());
        shapesModel.tryEditShape(32, 60);
        assertEquals(Color.BLACK, shapesModel.shapeList.get(0).getColor());
    }

}