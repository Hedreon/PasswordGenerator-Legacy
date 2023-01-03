package com.hedreon.passwordgenerator.ui;

// Imports
import static com.hedreon.passwordgenerator.ui.OptionsForm.lengthField;
import com.hedreon.passwordgenerator.lib.Generator;
import com.hedreon.passwordgenerator.lib.ImageLoader;
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

     // Loader
     public MainForm() {
          LoadForm();
     }

     private void LoadForm() {
        // Getting Icon
        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("icons/icon.png"));

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
        passwordTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
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
        optionsButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        optionsButton.setText("Generator Options");
        optionsButton.addActionListener(e -> {
            formProperties = new Properties();
            OptionsForm optionsForm = new OptionsForm(formProperties);
            optionsForm.setVisible(true);
        });
        this.add(optionsButton);

        // CopyButton
        JButton copyButton = new JButton();
        copyButton.setName("CopyButton");
        copyButton.setEnabled(false);
        copyButton.setText("Copy Password");
        copyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        copyButton.addActionListener(e -> Generator.copyPassword(passwordField));
        this.add(copyButton);

        // GenerateButton
        JButton generateButton = new JButton();
        generateButton.setName("GenerateButton");
        generateButton.setText("Generate Password");
        generateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        generateButton.addActionListener(e -> {
            if (lengthField.getText().isEmpty() || lengthField.getText().equals("0")) {
                JOptionPane.showMessageDialog(rootPane, "No length provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
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