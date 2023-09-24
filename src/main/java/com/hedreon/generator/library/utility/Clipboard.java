package com.hedreon.generator.library.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class Clipboard {
    public static void clip(String text) {
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, stringSelection);
    }
}