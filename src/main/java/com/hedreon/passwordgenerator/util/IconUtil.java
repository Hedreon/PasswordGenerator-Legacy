package com.hedreon.passwordgenerator.util;

// Imports
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;

public class IconUtil {
    public static Image loadIcon(String icon) {
        URL iconURL = IconUtil.class.getClassLoader().getResource(icon);

        if (iconURL == null) {
            throw new IllegalArgumentException("Could not find image with file name: " + icon);
        }

        return Toolkit.getDefaultToolkit().createImage(iconURL);
    }
}