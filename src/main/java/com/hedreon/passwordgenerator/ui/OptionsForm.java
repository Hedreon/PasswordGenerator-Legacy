package com.hedreon.passwordgenerator.ui;

// Imports
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.hedreon.passwordgenerator.lib.GeneratorSettings;
import com.hedreon.passwordgenerator.util.ClipboardUtil;
import com.hedreon.passwordgenerator.util.IconUtil;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.io.IOException;

public class OptionsForm extends JFrame {
    private final Properties formProperties;

    // Loader
    public OptionsForm(Properties formProperties) {
        this.formProperties = formProperties;
        loadForm();
    }

    // pasteAction
    public class pasteAction extends DefaultEditorKit.PasteAction {
        private pasteAction() {
            super();
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
            putValue(Action.NAME, "Paste Length");
        }

        public void actionPerformed(ActionEvent e) {
            JTextComponent component = getFocusedComponent();
            String clipboardText = getClipboardText();
            String numbers = getNumbers(clipboardText);

            if (numbers.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers!", "Password Generator", JOptionPane.ERROR_MESSAGE);
                return;
            }

            component.setText(numbers);
        }
    }

//    // NumericalPasteAction
//    public class NumericalPasteAction extends DefaultEditorKit.PasteAction {
//        private NumericalPasteAction() {
//            super();
//            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
//            putValue(Action.NAME, "Paste Length");
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JTextComponent component = getFocusedComponent();
//            String clipboardText = getClipboardText();
//
//            String onlyNumbers = getOnlyNumbers(clipboardText);
//
//            if (onlyNumbers.length() == 0) {
//                JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers!", "Password Generator", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            component.setText(onlyNumbers);
//        }
//    }
//
//    // SelectAllText
//    private static class SelectAllText extends TextAction {
//        public SelectAllText() {
//            super("Select All");
//            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JTextComponent component = getFocusedComponent();
//            component.selectAll();
//            component.requestFocusInWindow();
//        }
//    }
//
//    // Clear
//    private static class Clear extends TextAction {
//        public Clear() {
//            super("Clear Length");
//            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control BACK_SPACE"));
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JTextComponent component = getFocusedComponent();
//            component.setText("");
//            component.requestFocusInWindow();
//        }
//    }
//
//    // selectAllText
//    private static class selectAllText extends TextAction {
//        public selectAllText() {
//            super("Select All");
//            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JTextComponent component = getFocusedComponent();
//            component.selectAll();
//            component.requestFocusInWindow();
//        }
//    }
//
//    // clear
//    private static class clear extends TextAction {
//        public clear() {
//            super("Clear Length");
//            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control BACK_SPACE"));
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            JTextComponent component = getFocusedComponent();
//            component.setText("");
//            component.requestFocusInWindow();
//        }
//    }
//
//    // getClipboardText
//    private static String getClipboardText() {
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        Transferable contents = clipboard.getContents(null);
//
//        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//            try {
//                return (String) contents.getTransferData(DataFlavor.stringFlavor);
//            } catch (UnsupportedFlavorException | IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        return "";
//    }
//
//    private static String getClipboardText() {
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        Transferable contents = clipboard.getContents(null);
//        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//            try {
//                return (String) contents.getTransferData(DataFlavor.stringFlavor);
//            } catch (UnsupportedFlavorException | IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return "";
//    }
//
//    private static String getOnlyNumbers(String clipboardText) {
//        StringBuilder sb = new StringBuilder();
//        for (char c : clipboardText.toCharArray()) {
//            if (Character.isDigit(c)) {
//                sb.append(c);
//            }
//        }
//        return sb.length() > 0 ? sb.toString() : "";
//    }
//
//    private void pasteNumbers(JTextComponent component) {
//        String clipboardText = getClipboardText();
//        String onlyNumbers = getOnlyNumbers(clipboardText);
//
//        if (clipboardText.length() == 0 || onlyNumbers.length() == 0) {
//            JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers!", "Password Generator", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        int number = Integer.parseInt(onlyNumbers);
//        component.setText(String.valueOf(number));
//    }

    // getNumbers
    private static String getNumbers(String clipboardText) {
        StringBuilder builder = new StringBuilder();

        for (char character : clipboardText.toCharArray()) {
            if (Character.isDigit(character)) {
                builder.append(character);
            }
        }

        return builder.length() > 0 ? builder.toString() : "";
    }

//    // pasteNumbers
//    private void pasteNumbers(JTextComponent component) {
//        String clipboard = ClipboardUtil.getString();
//        String numbers = getNumbers(clipboard);
//
//        if (clipboard.length() == 0 || numbers.length() == 0) {
//            JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers or had nothing!", "Password Generator", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        int number = Integer.parseInt(numbers);
//        component.setText(String.valueOf(number));
//    }

    // loadForm
    private void loadForm() {
        // Getting Icon
        ImageIcon icon = new ImageIcon(IconUtil.loadIcon("icons/options.png"));

        // OptionsForm
        this.setName("OptionsForm");
        this.setIconImage(icon.getImage());
        this.setTitle("Generator Options");
        this.setSize(new Dimension(278, 200));
        this.setPreferredSize(new Dimension(278, 200));
        this.setMinimumSize(new Dimension(278, 200));
        this.setMaximumSize(new Dimension(278, 200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);

        // LengthTitle
        JLabel lengthTitle = new JLabel();
        lengthTitle.setName("LengthTitle");
        lengthTitle.setText("Password Length:");
        lengthTitle.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        this.add(lengthTitle);

        // PopupMenu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setName("PopupMenu");

        // PasteLength
        JMenuItem pasteItem = new JMenuItem();
        pasteItem.setAction(new pasteAction());
        popupMenu.add(pasteItem);

        // ClearLength
        popupMenu.add(new clear());

        // SelectAll
        popupMenu.add(new selectAllText());

        // LengthField
        JTextField lengthField = new JTextField();
        lengthField.setName("LengthField");
        lengthField.setComponentPopupMenu(popupMenu);
        lengthField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(lengthField, e.getX(), e.getY());
                }
            }
        });
        lengthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();

                if (!Character.isDigit(key)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
                    e.consume();
                    pasteNumbers(lengthField);
                }
            }
        });
        lengthField.setText(formProperties.getProperty("PASSWORD_LENGTH", String.valueOf(GeneratorSettings.Setting.PASSWORD_LENGTH)));
        this.add(lengthField);

        // SymbolCheck
        JCheckBox symbolCheck = new JCheckBox();
        symbolCheck.setName("SymbolCheck");
        symbolCheck.setText("Include Symbols");
        symbolCheck.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        symbolCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_SYMBOLS", String.valueOf(GeneratorSettings.Setting.INCLUDE_SYMBOLS))));
        this.add(symbolCheck);

        // NumberCheck
        JCheckBox numberCheck = new JCheckBox();
        numberCheck.setName("NumberCheck");
        numberCheck.setText("Include Numbers");
        numberCheck.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        numberCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_NUMBERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_NUMBERS))));
        this.add(numberCheck);

        // LowercaseCheck
        JCheckBox lowercaseCheck = new JCheckBox();
        lowercaseCheck.setName("LowercaseCheck");
        lowercaseCheck.setText("lowercase");
        lowercaseCheck.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        lowercaseCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_LOWERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS))));
        this.add(lowercaseCheck);

        // UppercaseCheck
        JCheckBox uppercaseCheck = new JCheckBox();
        uppercaseCheck.setName("UppercaseCheck");
        uppercaseCheck.setText("UPPERCASE");
        uppercaseCheck.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        uppercaseCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_UPPERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS))));
        this.add(uppercaseCheck);

        // SaveButton
        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setText("Save Changes");
        saveButton.setFont(new Font(FlatInterFont.FAMILY, Font.BOLD, 14));
        saveButton.addActionListener(e -> {
            if (lengthField.getText().isEmpty() || lengthField.getText().equals("0")) {
                JOptionPane.showMessageDialog(rootPane, "No length provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
            } else if (!symbolCheck.isSelected() && !numberCheck.isSelected() && !uppercaseCheck.isSelected() && !lowercaseCheck.isSelected()) {
                GeneratorSettings.Setting.PASSWORD_LENGTH = Integer.parseInt(lengthField.getText());
                formProperties.setProperty("PASSWORD_LENGTH", String.valueOf(GeneratorSettings.Setting.PASSWORD_LENGTH));

                JOptionPane.showMessageDialog(rootPane, "No options provided!", "Password Generator", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            } else {
                GeneratorSettings.Setting.INCLUDE_SYMBOLS = symbolCheck.isSelected();
                GeneratorSettings.Setting.INCLUDE_NUMBERS = numberCheck.isSelected();
                GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS = lowercaseCheck.isSelected();
                GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS = uppercaseCheck.isSelected();
                GeneratorSettings.Setting.PASSWORD_LENGTH = Integer.parseInt(lengthField.getText());

                formProperties.setProperty("INCLUDE_SYMBOLS", String.valueOf(GeneratorSettings.Setting.INCLUDE_SYMBOLS));
                formProperties.setProperty("INCLUDE_NUMBERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_NUMBERS));
                formProperties.setProperty("INCLUDE_LOWERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS));
                formProperties.setProperty("INCLUDE_UPPERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS));
                formProperties.setProperty("PASSWORD_LENGTH", String.valueOf(GeneratorSettings.Setting.PASSWORD_LENGTH));

                MainForm.generateButton.setEnabled(true);

                this.dispose();
            }
        });
        this.add(saveButton);

        // Layout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lengthTitle)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lengthField))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(symbolCheck)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lowercaseCheck, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addComponent(numberCheck)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(uppercaseCheck)))
                                                .addGap(0, 17, Short.MAX_VALUE)))
                                .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(saveButton)
                            .addGap(70, 70, 70)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lengthField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lengthTitle))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(symbolCheck, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lowercaseCheck, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(numberCheck, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(uppercaseCheck, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        this.pack();
    }
}