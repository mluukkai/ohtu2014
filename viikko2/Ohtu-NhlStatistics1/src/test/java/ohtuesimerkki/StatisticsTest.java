/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtuesimerkki;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maef
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
        this.stats = new Statistics(this.readerStub);
    }

    @Test
    public void testSearch() {
        Player nimi = stats.search("Kurri");
        Player eiOle = stats.search("MLuukai");
        
        assertEquals(nimi.getName(), "Kurri");
        assertEquals(null, eiOle);
        
    }

    @Test
    public void testTeam() {
        List<Player> team = stats.team("EDM");
        
        assertEquals("Semenko", team.get(0).getName());
        assertEquals("Kurri", team.get(1).getName());
        assertEquals("Gretzky", team.get(2).getName());
        assertEquals(3, team.size());
    }

    @Test
    public void testTopScorers() {
        List<Player> top = stats.topScorers(3);
        
        assertEquals(3, top.size());
        assertEquals("Gretzky", top.get(0).getName());
        assertEquals("Lemieux", top.get(1).getName());
        assertEquals("Yzerman", top.get(2).getName());
    }
    
}
