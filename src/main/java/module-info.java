module com.example.laborationtre {
    requires javafx.controls;
    requires javafx.fxml;




    opens com.example.laborationtre to javafx.fxml;
    exports com.example.laborationtre;
}