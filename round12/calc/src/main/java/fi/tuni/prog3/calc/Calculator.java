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

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = null;

        try {
            fxmlLoader = new FXMLLoader(Calculator.class.getResource(fxml + ".fxml"));                
        } 
        catch (IllegalStateException e) {
            System.out.println("IllegalStateException when trying to load FXML file: /fxml/" + fxml + ".fxml");
            e.printStackTrace();;
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            System.exit(1);
        }

        if (fxmlLoader == null) {
            System.out.println("FXMLLoader is null, failed to load FXML file.");
            System.exit(1);
        }

        return fxmlLoader.load();    
    }

    public static void main(String[] args) {
        launch();
    }

}