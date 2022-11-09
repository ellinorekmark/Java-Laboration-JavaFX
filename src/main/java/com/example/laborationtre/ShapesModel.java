package com.example.laborationtre;


import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;



public class ShapesModel {

    ObjectProperty<Number> size = new SimpleObjectProperty<>(50);
    ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    ObjectProperty<ToolOption> tool = new SimpleObjectProperty<>();

    ObservableList<MyShape> shapeList = FXCollections.observableArrayList();

    ObjectProperty<Boolean> online = new SimpleObjectProperty<>(false);
    ObjectProperty<Boolean> noNetwork = new SimpleObjectProperty<>(false);
    UndoRedo undoRedo = new UndoRedo();




    public double x;
    public double y;


    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;



    public ShapesModel(){
        try {
            socket = new Socket("localhost", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            var thread = new Thread(() -> {
                try {
                    while (true) {
                        String line = reader.readLine();
                        Platform.runLater(()-> addFromNetwork(line));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            noNetwork.set(true);
        }
    }


    public void addFromNetwork(String line){

        if(line.contains("Circle") || line.contains("Line")||line.contains("Square")) {

            String[] strings = line.split("\"");

            double x = Double.parseDouble(strings[1]);
            double y = Double.parseDouble(strings[2]);
            double size = Double.parseDouble(strings[3]);

            Color color = Color.web(strings[4]);


            if (strings[0].contains("Circle")) {
                addToLocalList(new MyCircle(x, y, size, color));
            } else if (strings[0].contains("Line")) {
                double endX = Double.parseDouble(strings[5]);
                double endY = Double.parseDouble(strings[6]);
                addToLocalList(new Line(x, y, endX, endY, size, color));
            } else if (strings[0].contains("Square")) {
                addToLocalList(new Square(x, y, size, color));
            }
        }
    }



    public void sendShape(String shapeString) {
        writer.println(shapeString);
    }



    public void addShape(MyShape shape) {
        if(online.get()){
            sendShape(shape.networkString());
        }
        else{
            addToLocalList(shape);
        }
    }



    private void addToLocalList(MyShape shape) {
        shapeList.add(shape);
        undoRedo.memoryList.add(()-> shapeList.add(shape));
        undoRedo.reverseList.clear();
    }

    public void createShape(double x, double y) {
        switch (tool.getValue()) {
            case LINE -> createLineStart(x, y);
            case CIRCLE -> createCircle(x, y);
            case SQUARE -> createSquare(x, y);
        }
    }

    private void createSquare(double x, double y) {
        addShape(new Square(x - (size.get().doubleValue() / 2), y - (size.get().doubleValue() / 2), size.get().doubleValue(), color.get()));
    }

    private void createLineStart(double posX, double posY) {
        x = posX;
        y = posY;
    }

    public void finishLine(MouseEvent mouseEvent) {
        addShape(new Line(x, y, mouseEvent.getX(), mouseEvent.getY(), size.get().doubleValue(), color.get()));
    }

    private void createCircle(double x, double y) {
        addShape(new MyCircle(x - (size.get().doubleValue() / 2), y - (size.get().doubleValue() / 2), size.get().doubleValue(), color.getValue()));
    }

    public void tryEditShape(double x, double y) {
        for (int i = 0; i < shapeList.size(); i++) {
            MyShape shape = shapeList.get(i);
            if (shape.compare(x, y)) {
                MyShape newShape = shape.copy(shape);
                newShape.setSize(size.get().doubleValue());
                newShape.setColor(color.get());
                shapeList.set(i, newShape);
                int finalI = i;
                undoRedo.memoryList.add(() -> shapeList.set(finalI, newShape));
                undoRedo.reverseList.clear();
            }
        }
    }




    public void saveToFile(java.nio.file.Path file) {

        StringBuilder string = new StringBuilder("""
                <?xml version="1.0" standalone="no"?>
                <svg width="500" height="500" version="1.1" xmlns="http://www.w3.org/2000/svg">
                """);
        for (MyShape shape : shapeList) {
            string.append(shape.toSVG()).append("\n");
        }
        string.append("</svg>");

        try {
            Files.writeString(file, string.toString());
        } catch (IOException e) {
            System.out.println("didn't work");
        }
    }

    public void removeOne() {
        undoRedo.reverseList.add(undoRedo.memoryList.get(undoRedo.memoryList.size()-1));
        undoRedo.memoryList.remove(undoRedo.memoryList.get(undoRedo.memoryList.size()-1));
        updateShapeList();
    }
    private void updateShapeList() {
        shapeList.clear();
        for (Command c : undoRedo.memoryList
        ) {
            c.execute();
        }
    }

    public void redoOne() {
        undoRedo.memoryList.add(undoRedo.reverseList.get(undoRedo.reverseList.size()-1));
        undoRedo.reverseList.remove(undoRedo.reverseList.get(undoRedo.reverseList.size()-1));
        updateShapeList();
    }


    public enum ToolOption {
        LINE {
            @Override
            public String toString() {
                return "Line";
            }
        },

        CIRCLE {
            @Override
            public String toString() {
                return "Circle";
            }
        },
        SQUARE {
            @Override
            public String toString() {
                return "Square";
            }
        }
    }

}




