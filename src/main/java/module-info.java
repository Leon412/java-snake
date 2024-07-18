module com.safjnest {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.safjnest to javafx.fxml;
    exports com.safjnest;
}
