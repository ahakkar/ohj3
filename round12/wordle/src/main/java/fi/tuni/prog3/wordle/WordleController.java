package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class WordleController implements Initializable {
    
    @FXML
    private Label labelOp1, labelOp2, labelRes, fieldRes;

    @FXML
    private TextField fieldOp1, fieldOp2;

    @FXML
    private Button btnAdd, btnSub, btnMul, btnDiv;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        // TODO
    }  
    
}
