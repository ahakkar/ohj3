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
    private Label labelRes;

    @FXML
    private TextField fieldOp1, fieldOp2, fieldRes;

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

        if (op1Str == "" || op2Str == "") { 
            return;
        }

        double op1 = Double.parseDouble(fieldOp1.getText());
        double op2 = Double.parseDouble(fieldOp2.getText());

        if (op1 == 0 || op2 == 0) {
            fieldRes.setText("0");
            return;
        }

        switch (op) {
            case ADD:            
                fieldRes.setText(Double.toString(op1 + op2));
                break;
            case SUBTRACT:
                fieldRes.setText(Double.toString(op1 - op2));
                break;
            case MULTIPLY:
                fieldRes.setText(Double.toString(op1 * op2));
                break;
            case DIVIDE:
                // Division by zero is not allowed
                if (op2 == 0) {
                    fieldRes.setText("0");
                    return;
                }
                fieldRes.setText(Double.toString(op1 / op2));
                break;
            default:
                // do something if myColor is none of the above
                break;
        }
        }
    
    
}
