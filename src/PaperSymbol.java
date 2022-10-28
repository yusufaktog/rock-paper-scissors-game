/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import core.supers.GameSymbol;
import javax.swing.ImageIcon;

/**
 *
 * @author joseph
 */
public class PaperSymbol extends GameSymbol {

    final int priority;

    public PaperSymbol(String name, int priority, ImageIcon image) { 
        super(name, priority, image);
        this.priority = 2;
    }

    @Override
    public int getPriority() { // to keep the game algorithm safe it uses the prority value defined in this class not super's so that it can not be changed from outside
        return this.priority;
    }

}
