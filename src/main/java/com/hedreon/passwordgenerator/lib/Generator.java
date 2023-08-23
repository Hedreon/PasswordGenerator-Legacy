package com.hedreon.passwordgenerator.lib;

// Imports
import com.hedreon.passwordgenerator.util.ClipboardUtil;
import javax.swing.text.JTextComponent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void generatePassword(JTextComponent component) {
        List<Character> availableChars = new ArrayList<>();

        if (GeneratorSettings.Setting.INCLUDE_NUMBERS) {
            for (char c = '0'; c <= '9'; c++) {
                availableChars.add(c);
            }
        }

        if (GeneratorSettings.Setting.INCLUDE_SYMBOLS) {
            for (char c : GeneratorSettings.Constant.SYMBOLS.toCharArray()) {
                availableChars.add(c);
            }
        }

        if (GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS) {
            for (char c : GeneratorSettings.Constant.LOWER_CASE.toCharArray()) {
                availableChars.add(c);
            }
        }

        if (GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS) {
            for (char c : GeneratorSettings.Constant.UPPER_CASE.toCharArray()) {
                availableChars.add(c);
            }
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < GeneratorSettings.Setting.PASSWORD_LENGTH; i++) {
            char c = availableChars.get(random.nextInt(availableChars.size()));
            password.append(c);
        }

        component.setText(password.toString());
    }

    public static void copyPassword(JTextComponent component) {
        ClipboardUtil.setString(component.getText());
    }
}