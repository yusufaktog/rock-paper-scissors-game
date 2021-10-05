/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.concretes.game;

import core.concretes.handlers.ImageHandler;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author joseph
 */
public class GameFrame extends JFrame {

    static GameFrame game;

    public GameFrame() {
        loadPreferences();

    }

    final void loadPreferences() {
        this.add(new GamePanel());
        this.setTitle("Rock Paper Scissors");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setIconImage(ImageHandler.getScaledImage(new ImageIcon("src\\core\\resources\\gameIcon.jpg"), 128, 128).getImage());

    }

    public static void startNewGame() {

        game = new GameFrame();
    }

    public static void main(String[] args) {
        startNewGame();
    }

}
