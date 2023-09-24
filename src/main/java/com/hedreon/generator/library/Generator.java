package com.hedreon.generator.library;

import com.hedreon.generator.library.utility.Clipboard;

import javax.swing.text.JTextComponent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void generatePassword(JTextComponent component) {
        if (Options.INCLUDE_NUMBERS || Options.INCLUDE_SYMBOLS || Options.INCLUDE_LOWERCASE || Options.INCLUDE_UPPERCASE) {
            List<Character> availableCharacters = new ArrayList<>();

            if (Options.INCLUDE_NUMBERS) {
                for (char character = '0'; character <= '9'; character++) {
                    availableCharacters.add(character);
                }
            }

            if (Options.INCLUDE_SYMBOLS) {
                for (char character : Options.SYMBOLS.toCharArray()) {
                    availableCharacters.add(character);
                }
            }

            if (Options.INCLUDE_LOWERCASE) {
                for (char character : Options.LOWERCASE.toCharArray()) {
                    availableCharacters.add(character);
                }
            }

            if (Options.INCLUDE_UPPERCASE) {
                for (char character : Options.UPPERCASE.toCharArray()) {
                    availableCharacters.add(character);
                }
            }

            SecureRandom random = new SecureRandom();
            StringBuilder builder = new StringBuilder();

            for (int index = 0; index < Options.PASSWORD_LENGTH; index++) {
                char character = availableCharacters.get(random.nextInt(availableCharacters.size()));
                builder.append(character);
            }

            component.setText(builder.toString());
        }
    }

    public static void copyPassword(JTextComponent component) {
        Clipboard.clip(component.getText());
    }
}