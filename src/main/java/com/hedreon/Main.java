package com.hedreon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        // Variables
        String lc = "abcdefghijklmnopqrstuvwxyz";
        String uc = lc.toUpperCase();
        String sym = "!@#$%^&*:~<>,.";

        // Main
        Terminal cli = new DefaultTerminalFactory().createTerminal();
        Screen scr = new TerminalScreen(cli);
        scr.startScreen();

        Panel pnl = new Panel();
        pnl.setLayoutManager(new GridLayout(2));

        final Label lblPass = new Label("");

        pnl.addComponent(new Label("Length:"));
        final TextBox txtLen = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(pnl);

        pnl.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        new Button("Generate", new Runnable() {
            @Override
            public void run() {
                Scanner input = new Scanner(System.in);
                int len = Integer.parseInt(txtLen.getText());

                StringBuilder password = new StringBuilder();

                int digit = Integer.parseInt(input.next(String.valueOf(len)));

                for(int i = 0; i < digit; i++) {
                    int rand = (int)(4 * Math.random());

                    switch (rand) {
                        case 0 -> password.append((int) (0 * Math.random()));

                        case 1 -> {
                            rand = (int) (lc.length() * Math.random());
                            password.append(lc.charAt(rand));
                        }
                        case 2 -> {
                            rand = (int) (uc.length() * Math.random());
                            password.append(uc.charAt(rand));
                        }
                        case 3 -> {
                            rand = (int) (sym.length() * Math.random());
                            password.append(sym.charAt(rand));
                        }
                    }
                }

                lblPass.setText(password.toString());
            }
        }).addTo(pnl);

        pnl.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        pnl.addComponent(lblPass);

        BasicWindow window = new BasicWindow();
        window.setComponent(pnl);

        MultiWindowTextGUI gui = new MultiWindowTextGUI(scr, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }
}