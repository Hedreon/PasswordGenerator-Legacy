package com.hedreon.passwordgenerator.lib;

// Imports
import javax.swing.JPasswordField;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void generatePassword(JPasswordField output) {
        // Initialize a list of characters to choose from
        List<Character> availableChars = new ArrayList<>();

        // Add numbers to the list if INCLUDE_NUMBERS is true
        if (GeneratorSettings.Setting.INCLUDE_NUMBERS) {
            for (char c = '0'; c <= '9'; c++) {
                availableChars.add(c);
            }
        }

        // Add symbols to the list if INCLUDE_SYMBOLS is true
        if (GeneratorSettings.Setting.INCLUDE_SYMBOLS) {
            for (char c : GeneratorSettings.Constant.SYMBOLS.toCharArray()) {
                availableChars.add(c);
            }
        }

        // Add lowercase letters to the list if INCLUDE_LOWERCASE_LETTERS is true
        if (GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS) {
            for (char c : GeneratorSettings.Constant.LOWER_CASE.toCharArray()) {
                availableChars.add(c);
            }
        }

        // Add uppercase letters to the list if INCLUDE_UPPERCASE_LETTERS is true
        if (GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS) {
            for (char c : GeneratorSettings.Constant.UPPER_CASE.toCharArray()) {
                availableChars.add(c);
            }
        }

        // Initialize a SecureRandom instance for generating random numbers
        SecureRandom random = new SecureRandom();

        // Initialize a StringBuilder for building the password
        StringBuilder password = new StringBuilder();

        // Select a random character from the list and append it to the password the specified number of times
        for (int i = 0; i < GeneratorSettings.Setting.PASSWORD_LENGTH; i++) {
            char c = availableChars.get(random.nextInt(availableChars.size()));
            password.append(c);
        }

        // Set the password in the output JPasswordField
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