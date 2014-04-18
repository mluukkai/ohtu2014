/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;

/**
 *
 * @author henrikorpela
 */
public class Test_Player_Reader implements Reader {

    @Override
    public List getPlayers() {
        List<Player> players = new ArrayList<Player>();
        
        players.add(new Player("Hawk_One", "Seahawks", 2, 4));
        players.add(new Player("Hawk_Two", "Seahawks", 4, 8));
        players.add(new Player("Hawk_Tree", "Seahawks", 6, 12));
        
        players.add(new Player("Bronco_One", "Broncos", 1, 2));
        players.add(new Player("Bronco_Two", "Broncos", 3, 6));
        players.add(new Player("Bronco_Tree", "Broncos", 8, 2));
        
        return players;
    }
    
}
