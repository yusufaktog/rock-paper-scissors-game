/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.concretes.symbols;

import core.supers.GameSymbol;
import javax.swing.ImageIcon;

/**
 *
 * @author joseph
 */
public class RockSymbol extends GameSymbol {

    final int priority;

    public RockSymbol(String name, int priority, ImageIcon image) {
        super(name, priority, image);
        this.priority = 1;
    }

    @Override
    public int getPriority() {
        return this.priority; 
    }

}
