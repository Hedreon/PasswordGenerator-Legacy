package com.hedreon.passwordgenerator.lib;

// Imports
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;

public class ImageLoader {
    public static Image loadImage(String image) {
        URL imageURL = ImageLoader.class.getClassLoader().getResource(image);

        if (imageURL == null) {
            throw new IllegalArgumentException("Could not find image with file name: " + image);
        }

        return Toolkit.getDefaultToolkit().createImage(imageURL);
    }
}