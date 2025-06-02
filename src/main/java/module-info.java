module org.example.sae202 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.sae202 to javafx.fxml;
    exports org.example.sae202;
    exports Vue;
}