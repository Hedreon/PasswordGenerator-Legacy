package com.hedreon.passwordgenerator.lib;

import static com.hedreon.passwordgenerator.lib.GeneratorConstants.LOWER_CASE;
import static com.hedreon.passwordgenerator.lib.GeneratorConstants.UPPER_CASE;
import static com.hedreon.passwordgenerator.lib.GeneratorConstants.SYMBOLS;

import javax.swing.JTextField;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;

public class Generator {
    public static void generate(JTextField inputTextField, JTextField outputTextField) {
        int PasswordLength = Integer.parseInt(inputTextField.getText());

        StringBuilder Password = new StringBuilder();

        for(int i = 0; i < PasswordLength; i++) {
            int Random = (int)(4 * Math.random());

            switch (Random) {
                case 0 -> Password.append((int) (10 * Math.random()));

                case 1 -> {
                    Random = (int) (LOWER_CASE.length() * Math.random());
                    Password.append(LOWER_CASE.charAt(Random));
                }

                case 2 -> {
                    Random = (int) (UPPER_CASE.length() * Math.random());
                    Password.append(UPPER_CASE.charAt(Random));
                }

                case 3 -> {
                    Random = (int) (SYMBOLS.length() * Math.random());
                    Password.append(SYMBOLS.charAt(Random));
                }
            }
        }

        outputTextField.setText(Password.toString());
    }

    public static void copy(JTextField outputTextField) {
        String password = outputTextField.getText();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(password);

        clipboard.setContents(stringSelection, stringSelection);
    }
}
