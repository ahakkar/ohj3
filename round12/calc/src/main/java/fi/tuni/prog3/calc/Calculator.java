package fi.tuni.prog3.calc;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Nelilaskin
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


public class Calculator extends Application {
    private static Stage stage;
    private static Scene scene;
 
    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage = s;
        setRoot("primary", "Nelilaskin");
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml, stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        scene = new Scene(loadFXML(fxml));        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();         
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("/fxml/primary.fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
