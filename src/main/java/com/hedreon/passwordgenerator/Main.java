package com.hedreon.passwordgenerator;

// Imports
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Setup look and feel
        FlatDarkLaf.setup();

        // Launch app
        EventQueue.invokeLater(() -> {
            Init app = new Init();
            app.setVisible(true);
        });
    }
}