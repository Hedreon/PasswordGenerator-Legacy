package com.hedreon.passwordgenerator;

// Imports
import com.hedreon.passwordgenerator.lib.Generator;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.net.URL;

public class Init extends JFrame {
    public Init() {
        initApp();
    }

    public void initApp() {
        // Icons
        URL iconURL = Init.class.getClassLoader().getResource("icon.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);

        // Frame
        this.setName("Frame");
        this.setIconImage(icon.getImage());
        this.setTitle("Password Generator");
        this.setSize(new Dimension(400, 230));
        this.setMinimumSize(new Dimension(400, 230));
        this.setMaximumSize(new Dimension(400, 230));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        // InputTitle
        JLabel inputTitle = new JLabel();
        inputTitle.setName("InputTitle");
        inputTitle.setHorizontalAlignment(0);
        inputTitle.setVerticalAlignment(3);
        inputTitle.setText("Length:");
        inputTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        this.add(inputTitle);

        // InputTextField
        JTextField inputTextField = new JTextField();
        inputTextField.setName("InputTextField");
        inputTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();

                if (!Character.isDigit(key)) {
                    e.consume();
                }
            }
        });
        this.add(inputTextField);

        // OutputTitle
        JLabel outputTitle = new JLabel();
        outputTitle.setName("OutputTitle");
        outputTitle.setHorizontalAlignment(0);
        outputTitle.setVerticalAlignment(3);
        outputTitle.setText("Password:");
        outputTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        this.add(outputTitle);

        // OutputTextField
        JTextField outputTextField = new JTextField();
        outputTextField.setName("OutputTextField");
        outputTextField.setEditable(false);
        this.add(outputTextField);

        // CopyButton
        JButton copyButton = new JButton();
        copyButton.setName("CopyButton");
        copyButton.setEnabled(false);
        copyButton.setSize(new Dimension(91, 25));
        copyButton.setText("Copy Password");
        copyButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        copyButton.addActionListener(e -> Generator.copy(outputTextField));
        this.add(copyButton);

        // GenerateButton
        JButton generateButton = new JButton();
        generateButton.setName("GenerateButton");
        generateButton.setSize(new Dimension(91, 25));
        generateButton.setText("Generate");
        generateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        generateButton.addActionListener(e -> {
            if (inputTextField.getText().isBlank() || inputTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "No length provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
            } else {
                Generator.generate(inputTextField, outputTextField);

                if (outputTextField.getText().length() > 0) {
                    copyButton.setEnabled(true);
                }
            }
        });
        this.add(generateButton);

        // Layouts
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(inputTitle, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(outputTitle, GroupLayout.Alignment.TRAILING, -1, 400, 32767).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(generateButton, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(outputTextField, GroupLayout.Alignment.TRAILING).addComponent(inputTextField).addComponent(copyButton, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(inputTitle, -2, 50, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(inputTextField, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(outputTitle, -2, 50, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(outputTextField, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(generateButton, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(copyButton, -2, -1, -2).addContainerGap()));

        this.pack();
    }
}
