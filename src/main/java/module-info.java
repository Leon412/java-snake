module com.safjnest {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.safjnest to javafx.fxml;
    exports com.safjnest;
}
