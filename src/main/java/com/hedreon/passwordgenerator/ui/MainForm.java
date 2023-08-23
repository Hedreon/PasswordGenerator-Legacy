package com.hedreon.passwordgenerator.ui;

// Imports
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.hedreon.passwordgenerator.lib.Generator;
import com.hedreon.passwordgenerator.lib.GeneratorSettings;
import com.hedreon.passwordgenerator.util.FormUtil;
import com.hedreon.passwordgenerator.util.IconUtil;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.util.Properties;
import java.awt.Dimension;
import java.awt.Font;

public class MainForm extends JFrame {
     private Properties formProperties;
     public static JButton generateButton = new JButton();

     // Loader
     public MainForm() {
          loadForm();
     }

     private void loadForm() {
        // Getting Icon
        ImageIcon icon = new ImageIcon(IconUtil.loadIcon("icons/icon.png"));

        // MainForm
        this.setName("MainForm");
        this.setIconImage(icon.getImage());
        this.setTitle("Password Generator");
        this.setSize(new Dimension(500, 253));
        this.setPreferredSize(new Dimension(500, 253));
        this.setMinimumSize(new Dimension(500, 253));
        this.setMaximumSize(new Dimension(500, 253));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        // PasswordTitle
        JLabel passwordTitle = new JLabel();
        passwordTitle.setName("PasswordTitle");
        passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);
        passwordTitle.setText("Password");
        passwordTitle.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 36));
        this.add(passwordTitle);

        // PasswordField
        JPasswordField passwordField = new JPasswordField();
        passwordField.setName("PasswordField");
        passwordField.setEditable(false);
        passwordField.setFocusable(false);
        this.add(passwordField);

        // OptionsButton
        JButton optionsButton = new JButton();
        optionsButton.setName("OptionsButton");
        optionsButton.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 16));
        optionsButton.setText("Generator Options");
        optionsButton.addActionListener(e -> {
            formProperties = new Properties();
            FormUtil.showForm(OptionsForm.class, formProperties);
        });
        this.add(optionsButton);

        // CopyButton
        JButton copyButton = new JButton();
        copyButton.setName("CopyButton");
        copyButton.setEnabled(false);
        copyButton.setText("Copy Password");
        copyButton.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 16));
        copyButton.addActionListener(e -> Generator.copyPassword(passwordField));
        this.add(copyButton);

        // GenerateButton
        generateButton.setName("GenerateButton");
        generateButton.setEnabled(false);
        generateButton.setText("Generate Password");
        generateButton.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 16));
        generateButton.addActionListener(e -> {
            if (GeneratorSettings.Setting.PASSWORD_LENGTH == 0) {
                JOptionPane.showMessageDialog(rootPane, "No length provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
            } else if (!GeneratorSettings.Setting.INCLUDE_SYMBOLS && !GeneratorSettings.Setting.INCLUDE_NUMBERS && !GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS && !GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS) {
                JOptionPane.showMessageDialog(rootPane, "No options provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
            } else {
                Generator.generatePassword(passwordField);

                if (passwordField.getPassword().length > 0) {
                    copyButton.setEnabled(true);
                }
            }
        });
        this.add(generateButton);

        // Layout
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(optionsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(passwordField)
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(copyButton, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                                .addComponent(passwordTitle, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(passwordTitle)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optionsButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(copyButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap()));

        this.pack();
    }
}