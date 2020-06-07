/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mokkivarausjarjestelma;

import javafx.scene.control.TextField;

/**
 * Luokkka sisaltaa validointiin vaadittavat metodit
 */
public class ValidointiLuokka {

    // Ei sallita tyhjaa syotetta
    public static boolean textFieldIsNull(TextField inputTextField, TextField outputTextField, String validationText) {
        boolean isNull = false;
        String validationString = null;

        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;
        }

        outputTextField.setText(validationString);
        return isNull;

    }

}
