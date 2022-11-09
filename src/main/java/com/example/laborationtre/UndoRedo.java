package com.example.laborationtre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UndoRedo {


    ObservableList<Command> memoryList = FXCollections.observableArrayList();
    ObservableList<Command> reverseList = FXCollections.observableArrayList();
}
