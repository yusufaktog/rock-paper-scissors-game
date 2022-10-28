/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Player;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author joseph
 */
public class Computer extends Player{
    
    public Computer(String name) {
        super(name);
    }
    
    public int chooseRandomIndex(){
        return ThreadLocalRandom.current().nextInt(3);
    }
}
