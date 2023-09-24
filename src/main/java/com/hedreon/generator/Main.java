package com.hedreon.generator;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.hedreon.generator.library.Generator;
import com.hedreon.generator.library.Options;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main extends JFrame {
    private JPanel MainPanel;
    private JLabel PasswordLabel;
    private JPasswordField PasswordField;
    private JLabel LengthLabel;
    private JSpinner LengthSpinner;
    private JCheckBox UppercaseBox;
    private JCheckBox LowercaseBox;
    private JCheckBox NumberBox;
    private JCheckBox SymbolBox;
    private JButton GenerateButton;
    private JButton CopyButton;
    private JButton RevealButton;
    private boolean PasswordShown;

    public Main() {
        URL iconURL = getClass().getResource("/icons/icon.png");
        assert iconURL != null;

        ImageIcon icon = new ImageIcon(iconURL);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Password Generator");
        setIconImage(icon.getImage());
        setContentPane(MainPanel);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(16, 1, 256, 1);
        LengthSpinner.setModel(numberModel);

        GenerateButton.addActionListener(event -> {
            if (!SymbolBox.isSelected() && !NumberBox.isSelected() && !LowercaseBox.isSelected() && !UppercaseBox.isSelected()) {
                GenerateButton.setText("Select at least one");
                GenerateButton.setEnabled(false);

                Timer delay = new Timer(2000, timer -> {
                    GenerateButton.setText("Generate Password");
                    GenerateButton.setEnabled(true);
                });

                delay.setRepeats(false);
                delay.start();
            } else {
                Options.INCLUDE_SYMBOLS = SymbolBox.isSelected();
                Options.INCLUDE_NUMBERS = NumberBox.isSelected();
                Options.INCLUDE_LOWERCASE = LowercaseBox.isSelected();
                Options.INCLUDE_UPPERCASE = UppercaseBox.isSelected();
                Options.PASSWORD_LENGTH = (int) LengthSpinner.getValue();

                Generator.generatePassword(PasswordField);

                if (PasswordField.getPassword().length > 0) {
                    CopyButton.setEnabled(true);
                    RevealButton.setEnabled(true);
                }
            }
        });

        CopyButton.addActionListener(event -> Generator.copyPassword(PasswordField));

        RevealButton.addActionListener(event -> {
            if (!PasswordShown) {
                PasswordField.setEchoChar((char) 0);
                RevealButton.setText("Hide");

                PasswordShown = true;
            } else {
                PasswordField.setEchoChar('•');
                RevealButton.setText("Show");

                PasswordShown = false;
            }
        });

        LengthSpinner.addMouseWheelListener(event -> {
            if (LengthSpinner.getModel() instanceof SpinnerNumberModel model) {
                int increment = -event.getWheelRotation();

                for(int index = 0; index < Math.abs(event.getWheelRotation()); index++) {
                    Object nextValue = increment > 0 ? model.getNextValue() : model.getPreviousValue();

                    if (nextValue != null) {
                        model.setValue(nextValue);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        FlatInterFont.install();
        FlatLaf.setPreferredFontFamily(FlatInterFont.FAMILY);
        FlatDarkLaf.setup();

        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}