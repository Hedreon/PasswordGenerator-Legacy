package com.hedreon.passwordgenerator;

// Imports
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jthemedetecor.OsThemeDetector;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Setup look and feel
        final OsThemeDetector themeDetector = OsThemeDetector.getDetector();
        final boolean usesDarkTheme = themeDetector.isDark();

        if (usesDarkTheme) {
            FlatDarkLaf.setup();
        } else {
            FlatLightLaf.setup();
        }

        // Launch app
        EventQueue.invokeLater(() -> {
            Init app = new Init();
            app.setVisible(true);
        });
    }
}
