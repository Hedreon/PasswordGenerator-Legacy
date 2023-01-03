package com.hedreon.passwordgenerator.ui;

// Imports
import com.hedreon.passwordgenerator.lib.GeneratorSettings;
import com.hedreon.passwordgenerator.lib.ImageLoader;
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
    public static JTextField lengthField = new JTextField();
    private final Properties formProperties;

    // Loader
    public OptionsForm(Properties properties) {
        lengthField = new JTextField();
        formProperties = properties;
        LoadForm();
    }

    // NumericalPasteAction
    public class NumericalPasteAction extends DefaultEditorKit.PasteAction {
        public NumericalPasteAction() {
            super();
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
            putValue(Action.NAME, "Paste Length");
        }

        public void actionPerformed(ActionEvent e) {
            JTextComponent component = getFocusedComponent();
            String clipboardText = getClipboardText();

            String onlyNumbers = getOnlyNumbers(clipboardText);

            if (onlyNumbers.length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers!", "Password Generator", JOptionPane.ERROR_MESSAGE);
                return;
            }

            component.setText(onlyNumbers);
        }
    }

    // SelectAllText
    private static class SelectAllText extends TextAction {
        public SelectAllText() {
            super("Select All");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
        }

        public void actionPerformed(ActionEvent e) {
            JTextComponent component = getFocusedComponent();
            component.selectAll();
            component.requestFocusInWindow();
        }
    }

    // Clear
    private static class Clear extends TextAction {
        public Clear() {
            super("Clear Length");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control BACK_SPACE"));
        }

        public void actionPerformed(ActionEvent e) {
            JTextComponent component = getFocusedComponent();
            component.setText("");
            component.requestFocusInWindow();
        }
    }

    private void LoadForm() {
        // Getting Icon
        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("icons/options.png"));

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
        lengthTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.add(lengthTitle);

        // PopupMenu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setName("PopupMenu");

        // PasteLength
        JMenuItem pasteItem = new JMenuItem();
        Action numericalPasteAction = new NumericalPasteAction();
        pasteItem.setAction(numericalPasteAction);
        popupMenu.add(pasteItem);

        // ClearLength
        Action clear = new Clear();
        popupMenu.add(clear);

        // SelectAll
        Action selectAll = new SelectAllText();
        popupMenu.add(selectAll);

        // LengthField
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
        symbolCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        symbolCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_SYMBOLS", String.valueOf(GeneratorSettings.Setting.INCLUDE_SYMBOLS))));
        this.add(symbolCheck);

        // NumberCheck
        JCheckBox numberCheck = new JCheckBox();
        numberCheck.setName("NumberCheck");
        numberCheck.setText("Include Numbers");
        numberCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        numberCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_NUMBERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_NUMBERS))));
        this.add(numberCheck);

        // LowercaseCheck
        JCheckBox lowercaseCheck = new JCheckBox();
        lowercaseCheck.setName("LowercaseCheck");
        lowercaseCheck.setText("lowercase");
        lowercaseCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lowercaseCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_LOWERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_LOWERCASE_LETTERS))));
        this.add(lowercaseCheck);

        // UppercaseCheck
        JCheckBox uppercaseCheck = new JCheckBox();
        uppercaseCheck.setName("UppercaseCheck");
        uppercaseCheck.setText("UPPERCASE");
        uppercaseCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        uppercaseCheck.setSelected(Boolean.parseBoolean(formProperties.getProperty("INCLUDE_UPPERCASE_LETTERS", String.valueOf(GeneratorSettings.Setting.INCLUDE_UPPERCASE_LETTERS))));
        this.add(uppercaseCheck);

        // SaveButton
        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setText("Save Changes");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.addActionListener(e -> {
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

            this.dispose();
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

    private static String getClipboardText() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    private static String getOnlyNumbers(String clipboardText) {
        StringBuilder sb = new StringBuilder();
        for (char c : clipboardText.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return sb.length() > 0 ? sb.toString() : "";
    }

    private void pasteNumbers(JTextComponent component) {
        String clipboardText = getClipboardText();
        String onlyNumbers = getOnlyNumbers(clipboardText);

        if (clipboardText.length() == 0 || onlyNumbers.length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "What you pasted did not include numbers!", "Password Generator", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int number = Integer.parseInt(onlyNumbers);
        component.setText(String.valueOf(number));
    }
}