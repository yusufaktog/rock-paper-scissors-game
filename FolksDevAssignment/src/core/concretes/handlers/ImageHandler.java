/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.concretes.handlers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author joseph
 */
public class ImageHandler {

    public static ImageIcon getScaledImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;

    }

    public static ImageIcon readImage(String path) {
        Image image = null;
        try {
            image = ImageIO.read(new FileImageInputStream(new File(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ImageIcon(image);
    }
    
    public static ImageIcon loadImage(ClassLoader classLoader, String path){
        URL resource = classLoader.getResource(path);
        return new ImageIcon(resource);
    }

}
