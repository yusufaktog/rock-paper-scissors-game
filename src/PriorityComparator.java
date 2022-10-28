/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joseph
 */

import core.supers.GameSymbol;
import java.util.Comparator;

public class PriorityComparator implements Comparator<GameSymbol> {
    
    /*
    rock: 1, paper: 2 , scissors: 3  // if any of these proiorities are changed then compare method must be rearranged as well
    3 cases:
        sum of id's 3 -> rock-paper -> paper wraps rock
        sum of id's 4 -> rock-scissors -> rock blunts scissors
        sum of id's 5 -> paper-scissors -> scissors cuts paper
    */

    @Override
    public int compare(GameSymbol g1, GameSymbol g2) {
        int flag = 0;
        switch (g1.getPriority() + g2.getPriority()) {
            case 3:
                flag = g1.getPriority() - g2.getPriority();
                break;
            case 4:
                flag = g2.getPriority() - g1.getPriority();
                break;
            case 5:
                flag = g1.getPriority() - g2.getPriority();
                break;
        }
        return flag;
    }

    public GameSymbol determineWinner(GameSymbol g1, GameSymbol g2) {
        int comparisionResult = new PriorityComparator().compare(g1, g2);
        if (comparisionResult > 0) {
            return g1;
        } else {
            return g2;
        }
    }

}
