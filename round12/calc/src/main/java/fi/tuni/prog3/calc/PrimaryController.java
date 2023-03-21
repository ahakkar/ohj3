package fi.tuni.prog3.calc;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Nelilaskin
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable {

    private enum Operation {ADD, SUBTRACT, MULTIPLY, DIVIDE, NONE};
    
    @FXML
    private Label labelOp1, labelOp2, labelRes, fieldRes;

    @FXML
    private TextField fieldOp1, fieldOp2;

    @FXML
    private Button btnAdd, btnSub, btnMul, btnDiv;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        btnAdd.setOnAction(this::handleAddButton);
        btnSub.setOnAction(this::handleSubButton);
        btnMul.setOnAction(this::handleMulButton);
        btnDiv.setOnAction(this::handleDivButton);
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        calculate(Operation.ADD);
    }

    @FXML
    private void handleSubButton(ActionEvent event) {
        calculate(Operation.SUBTRACT);
    }

    @FXML
    private void handleMulButton(ActionEvent event) {
        calculate(Operation.MULTIPLY);
    }

    @FXML
    private void handleDivButton(ActionEvent event) {
        calculate(Operation.DIVIDE);
    }

    private void calculate(Operation op) {        
        String op1Str = fieldOp1.getText();
        String op2Str = fieldOp2.getText();
        double op1 = Double.parseDouble(fieldOp1.getText());
        double op2 = Double.parseDouble(fieldOp2.getText());
        Double result = 0.0;

        // check for no values
        if (op1Str == "" || op2Str == "") { 
            return;
        } 

        switch (op) {
            case ADD:            
                result = op1 + op2;
                break;
            case SUBTRACT:
                result = op1 - op2;
                break;
            case MULTIPLY:
                result = op1 * op2;
                break;
            case DIVIDE:
                // Division by zero is not allowed
                if (op2 == 0) {
                    break;
                }
                result = op1 / op2;
                break;
            default:                
                break;
        }

        // set result
        fieldRes.setText(result.toString());
    }
    
    
}
