package com.hedreon.passwordgenerator;

// Imports
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.hedreon.passwordgenerator.ui.MainForm;
import com.hedreon.passwordgenerator.util.FormUtil;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Install font, setup look and feel and launch app
        FlatInterFont.install();
        FlatLaf.setPreferredFontFamily(FlatInterFont.FAMILY);
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> FormUtil.showForm(MainForm.class));
    }
}