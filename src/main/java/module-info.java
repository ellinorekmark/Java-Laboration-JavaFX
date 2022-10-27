module com.example.laborationtre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.laborationtre to javafx.fxml;
    exports com.example.laborationtre;
}