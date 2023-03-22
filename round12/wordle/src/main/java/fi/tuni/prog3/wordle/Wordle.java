package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.VBox;

public class Wordle extends Application {    
    public static void main(String[] args) {
        launch();
    }

    // @SuppressWarnings("exports") 
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Wordle v. 1.0 by Antti Hakkarainen");
        stage.show();

        WordleCore wc = new WordleCore(root, scene);
        wc.setCoreInstance(wc);
        wc.startNewGame();
    }
}