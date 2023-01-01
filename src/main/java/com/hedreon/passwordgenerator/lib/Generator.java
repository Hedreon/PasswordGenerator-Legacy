package com.hedreon.passwordgenerator.lib;

// Imports
import static com.hedreon.passwordgenerator.lib.GeneratorSettings.*;
import javax.swing.JPasswordField;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;

public class Generator {
    public static void generatePassword(JPasswordField output) {
        StringBuilder password = new StringBuilder();

        for(int i = 0; i < PASSWORD_LENGTH; i++) {
            int Random = (int) (4 * Math.random());

            switch (Random) {
                case 0 -> {
                    if (INCLUDE_NUMBERS) {
                        password.append((int) (10 * Math.random()));
                    } else {
                        return;
                    }
                }

                case 1 -> {
                    if (INCLUDE_LOWERCASE_LETTERS) {
                        Random = (int) (LOWER_CASE.length() * Math.random());
                        password.append(LOWER_CASE.charAt(Random));
                    } else {
                        return;
                    }
                }

                case 2 -> {
                    if (INCLUDE_UPPERCASE_LETTERS) {
                        Random = (int) (UPPER_CASE.length() * Math.random());
                        password.append(UPPER_CASE.charAt(Random));
                    } else {
                        return;
                    }
                }

                case 3 -> {
                    if (INCLUDE_SYMBOLS) {
                        Random = (int) (SYMBOLS.length() * Math.random());
                        password.append(SYMBOLS.charAt(Random));
                    } else {
                        return;
                    }
                }
            }
        }

        output.setText(password.toString());
    }

    public static void copyPassword(JPasswordField output) {
        char[] password = output.getPassword();
        String passwordString = new String(password);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(passwordString);

        clipboard.setContents(stringSelection, stringSelection);
    }
}