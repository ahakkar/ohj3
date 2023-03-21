module fi.tuni.prog3.calc {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.media;
    opens fi.tuni.prog3.calc to javafx.fxml;
    exports fi.tuni.prog3.calc;
}