package com.hedreon.passwordgenerator;

// Imports
import com.formdev.flatlaf.FlatDarkLaf;
import com.hedreon.passwordgenerator.ui.MainForm;
import com.hedreon.passwordgenerator.util.FormUtils;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Setup look and feel and launch app
        FlatDarkLaf.setup();
        EventQueue.invokeLater(() -> FormUtils.showForm(MainForm.class));
    }
}