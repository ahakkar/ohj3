module fi.tuni.prog3.wordle {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
   
    opens fi.tuni.prog3.wordle to javafx.fxml;
    exports fi.tuni.prog3.wordle;
}
