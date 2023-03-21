package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Pane;

public class Wordle extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();

        WordleController wc = new WordleController(root, scene);
    }


    public static void main(String[] args) {
        launch();
    }

}