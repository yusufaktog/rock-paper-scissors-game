/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.concretes.handlers;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author joseph
 */
public class ButtonHandler {

    public static void loadButton(JButton button, int width, int height, int posX, int posY, ImageIcon icon, JPanel parentComponent, ActionListener actionListener) {
        
        button.setBounds(posX, posY, width, height);
        button.setIcon(ImageHandler.getScaledImage(icon, 100, 100));

        //This part is for round shaped buttons
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        //

        button.addActionListener(actionListener);
        parentComponent.add(button);
    }

}
