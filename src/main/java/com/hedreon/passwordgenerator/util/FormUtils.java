package com.hedreon.passwordgenerator.util;

import javax.swing.JFrame;
import java.lang.reflect.Constructor;

public class FormUtils {
    public static void showForm(Class<? extends JFrame> form, Object... args) {
        try {
            JFrame frame;

            Class<?>[] argTypes = new Class<?>[args.length];

            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }

            Constructor<? extends JFrame> constructor = form.getConstructor(argTypes);

            frame = constructor.newInstance(args);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}