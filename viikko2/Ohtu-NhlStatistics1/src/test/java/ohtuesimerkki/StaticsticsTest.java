/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eemi
 */
public class StaticsticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    @Test
    public void failedSearchReturnsNull() {
        Statistics s = new Statistics(readerStub);

        Player result = s.search("Granlund");

        assertTrue( "Player was found from list", result == null);
    }

    @Test
    public void searchFindsPlayer() {
        Statistics s = new Statistics(readerStub);

        Player result = s.search("Kurri");

        assertTrue( "Player was not found from list", result.getName().equals("Kurri"));
    }

    @Test
    public void emptyTeamRetursEmptyList() {
        Statistics s = new Statistics(readerStub);

        ArrayList<Player> result = (ArrayList<Player>) s.team("HIFK");

        assertTrue( "Team was not empty", result.isEmpty());
    }

    @Test
    public void teamReturnsPlayers() {
        Statistics s = new Statistics(readerStub);

        ArrayList<Player> result = (ArrayList<Player>) s.team("EDM");

        assertTrue( "Team that was on the stats returned an empty list", !result.isEmpty());
    }
    
    @Test
    public void topScorersReturnsAskedAmountOfPlayers() {
        Statistics s = new Statistics(readerStub);
        
        ArrayList<Player> result = (ArrayList<Player>) s.topScorers(3);
        
        assertTrue("Method did not return the right amout of scorers", result.size() == 3);
        
    }
    
    
    // ...
}