package com.hedreon.passwordgenerator;

// Imports
import com.formdev.flatlaf.FlatDarkLaf;
import com.hedreon.passwordgenerator.ui.MainForm;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Setup look and feel
        FlatDarkLaf.setup();

        // Launch app
        EventQueue.invokeLater(() -> {
            MainForm form = new MainForm();
            form.setVisible(true);
        });
    }
}