/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samsalon
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

        @Override
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

    public StatisticsTest() {

        stats = new Statistics(readerStub);

    }

    @Test
    public void searchForPlayer() {
        Player found = stats.search("Semenko");
        assertEquals(found.getName(), "Semenko");
        
        found = stats.search("qwerty");
        assertEquals(found, null);
    }

    @Test
    public void teamSearch() {
        List<Player> found = stats.team("EDM");
        assertEquals(found.size(), 3);
        
        found = stats.team("qwerty");
        assertEquals(found.size(), 0);
    }
    
    @Test
    public void topScorers() {
        List<Player> found = stats.topScorers(3);
        //assertEquals(found.size(), 3);
        assertTrue(found.get(0).getPoints() >= found.get(1).getPoints());
        assertTrue(found.get(1).getPoints() >= found.get(2).getPoints());
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
