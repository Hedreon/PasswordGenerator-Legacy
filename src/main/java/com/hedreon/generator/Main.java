package com.hedreon.generator;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hedreon.generator.library.Generator;
import com.hedreon.generator.library.Options;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.MouseEvent;

public class Main {
	private static Text PasswordText;
	private static Button CopyButton;
		
	private static boolean PasswordShown;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		
		Shell GeneratorShell = new Shell();
		
		GeneratorShell.setImage(SWTResourceManager.getImage(Main.class, "/icon.ico"));
		GeneratorShell.setMinimumSize(new Point(500, 400));
		GeneratorShell.setSize(500, 400);
		GeneratorShell.setText("Password Generator");
		
		FillLayout fl_GeneratorShell = new FillLayout(SWT.VERTICAL);
		fl_GeneratorShell.marginHeight = 40;
		fl_GeneratorShell.marginWidth = 40;
		
		GeneratorShell.setLayout(fl_GeneratorShell);
		
		Label PasswordLabel = new Label(GeneratorShell, SWT.NONE);
		PasswordLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		PasswordLabel.setText("Password:");
		
		PasswordText = new Text(GeneratorShell, SWT.BORDER | SWT.PASSWORD);
		PasswordText.setEnabled(false);
		PasswordText.setEditable(false);
		
		Button RevealButton = new Button(GeneratorShell, SWT.NONE);
		RevealButton.setEnabled(false);
		
		RevealButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!PasswordShown) {
	                PasswordText.setEchoChar((char) 0);
	                RevealButton.setText("Hide Password");

	                PasswordShown = true;
	            } else {
	            	PasswordText.setEchoChar('\u2022');
	                RevealButton.setText("Reveal Password");

	                PasswordShown = false;
	            }
			}
		});
		
		RevealButton.setText("Reveal Password");
		
		Label LengthLabel = new Label(GeneratorShell, SWT.NONE);
		LengthLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		LengthLabel.setText("Length:");
		
		Spinner LengthSpinner = new Spinner(GeneratorShell, SWT.BORDER);
		
		LengthSpinner.addMouseWheelListener(new MouseWheelListener() {
			public void mouseScrolled(MouseEvent event) {
				LengthSpinner.setSelection(LengthSpinner.getSelection() + (event.count < 0 ? -2 : 2));
			}
		});
		
		LengthSpinner.setPageIncrement(4);
		LengthSpinner.setMaximum(256);
		LengthSpinner.setMinimum(2);
		LengthSpinner.setSelection(16);
		
		Button UppercaseCheck = new Button(GeneratorShell, SWT.CHECK);
		UppercaseCheck.setSelection(true);
		UppercaseCheck.setText("Include UPPERCASE Letters");
		
		Button LowercaseCheck = new Button(GeneratorShell, SWT.CHECK);
		LowercaseCheck.setSelection(true);
		LowercaseCheck.setText("Include lowercase Letters");
		
		Button NumberCheck = new Button(GeneratorShell, SWT.CHECK);
		NumberCheck.setSelection(true);
		NumberCheck.setText("Include Numbers");
		
		Button SymbolCheck = new Button(GeneratorShell, SWT.CHECK);
		SymbolCheck.setText("Include Symbols");
		
		Button GenerateButton = new Button(GeneratorShell, SWT.NONE);
		
		GenerateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!SymbolCheck.getSelection() && !NumberCheck.getSelection() && !LowercaseCheck.getSelection() && !UppercaseCheck.getSelection()) {
					GenerateButton.setText("Select at least one");
	            } else {
	            	GenerateButton.setText("Generate Password");
	            	
	                Options.INCLUDE_SYMBOLS = SymbolCheck.getSelection();
	                Options.INCLUDE_NUMBERS = NumberCheck.getSelection();
	                Options.INCLUDE_LOWERCASE = LowercaseCheck.getSelection();
	                Options.INCLUDE_UPPERCASE = UppercaseCheck.getSelection();
	                Options.PASSWORD_LENGTH = LengthSpinner.getSelection();

	                Generator.generatePassword(PasswordText);

	                if (PasswordText.getText().length() > 0) {
	                    CopyButton.setEnabled(true);
	                    RevealButton.setEnabled(true);
	                }
	            }
			}
		});
		
		GenerateButton.setText("Generate Password");
		
		CopyButton = new Button(GeneratorShell, SWT.NONE);
		
		CopyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Generator.copyPassword(PasswordText);
			}
		});
		
		CopyButton.setEnabled(false);
		CopyButton.setText("Copy Password");

		GeneratorShell.open();
		GeneratorShell.layout();
		
		while (!GeneratorShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
