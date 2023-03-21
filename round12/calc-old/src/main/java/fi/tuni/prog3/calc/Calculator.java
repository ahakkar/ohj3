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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("/fxml/" + fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("FXML file not found: /fxml/" + fxml + ".fxml");
            throw e;
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException when trying to load FXML file: /fxml/" + fxml + ".fxml");
            throw e;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
