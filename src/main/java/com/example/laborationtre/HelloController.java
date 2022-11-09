package com.example.laborationtre;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import static com.example.laborationtre.ShapesModel.*;


public class HelloController {

    public CheckBox networkToggle;
    ShapesModel shapesModel = new ShapesModel();

    public Canvas canvas;
    public Stage stage;
    @FXML
    public Button undoButton;
    public Button redoButton;
    public Button saveButton;
    public Button clearButton;
    public ColorPicker colorChoice;
    public static GraphicsContext context;
    public ChoiceBox<ToolOption> toolsList;
    public Slider pixelSlider;
    public ToggleButton editTool;
    public Label pixelSizeInfo;


    ObservableList<ToolOption> toolsDropDownList = FXCollections.observableArrayList(ToolOption.values());


    public void initialize() {
        context = canvas.getGraphicsContext2D();
        saveButton.disableProperty().bind(Bindings.isEmpty(shapesModel.shapeList));
        undoButton.disableProperty().bind(Bindings.isEmpty(shapesModel.shapeList));
        redoButton.disableProperty().bind(Bindings.isEmpty(shapesModel.undoRedo.reverseList));
        colorChoice.valueProperty().bindBidirectional(shapesModel.color);
        pixelSlider.valueProperty().bindBidirectional(shapesModel.size);
        toolsList.valueProperty().bindBidirectional(shapesModel.tool);
        toolsList.disableProperty().bind(editTool.selectedProperty());
        shapesModel.shapeList.addListener((ListChangeListener<MyShape>) c -> updateCanvas());
        shapesModel.undoRedo.memoryList.addListener((ListChangeListener<Command>) c -> updateCanvas());
        toolsList.setItems(toolsDropDownList);
        toolsList.setValue(ToolOption.CIRCLE);
        networkToggle.selectedProperty().bindBidirectional(shapesModel.online);
        networkToggle.disableProperty().bind(shapesModel.noNetwork);
    }


    @FXML
    protected void onCanvasPress(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (editTool.isSelected()) {

            shapesModel.tryEditShape(x, y);
        } else {
            shapesModel.createShape(x, y);
        }

    }

    public void updateCanvas() {
        context.clearRect(0, 0, 500, 500);
        for (MyShape shape : shapesModel.shapeList) {
            shape.draw(context);
        }
    }


    public void onCanvasRelease(MouseEvent mouseEvent) {
        if (shapesModel.tool.getValue().equals(ToolOption.LINE)) {
            shapesModel.finishLine(mouseEvent);
        }
    }

    @FXML
    void undoCanvas() {
        if (!shapesModel.shapeList.isEmpty()) {
            shapesModel.removeOne();
        }
    }



    @FXML
    void redoCanvas() {
            shapesModel.redoOne();

    }

    public void clearCanvas() {
        shapesModel.shapeList.clear();
        shapesModel.undoRedo.memoryList.clear();
        shapesModel.undoRedo.reverseList.clear();
    }

    public void editToolActive() {
        toolsList.setValue(ToolOption.CIRCLE);
    }

    public void updatePixelSize() {
        pixelSizeInfo.setText(Math.round(pixelSlider.getValue()) + " px");
    }


    public void onSaveAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));
        fileChooser.setInitialFileName("untitled.svg");
        File filepath = fileChooser.showSaveDialog(stage);

        if (filepath != null) {
            java.nio.file.Path path = java.nio.file.Path.of(filepath.toURI());
            shapesModel.saveToFile(path);
        }

    }
}





