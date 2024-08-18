module com.example.javacourseworkcm1606 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javacourseworkcm1606 to javafx.fxml;
    exports com.example.javacourseworkcm1606;
}