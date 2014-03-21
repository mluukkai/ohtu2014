/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aozi
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

    // ...    
    public StatisticsTest() {
        stats = new Statistics(readerStub);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }
    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {

        Player Seme = stats.search("Semenko");
        assertEquals("Semenko" ,Seme.getName());
        assertEquals(4,Seme.getGoals());
        assertEquals(12,Seme.getAssists());
        
    }
    
    @Test
    public void searchReturnNullWhenNotFound() {
        Player result = stats.search("hhgfu");
        assertEquals(null, result);
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {        
        List<Player> tulos = stats.team("EDM");
        for(Player p : tulos) {
            assertEquals("EDM", p.getTeam());
        }
        
        
    }

    @Test
    public void testTopScorers() {
        List<Player> tulos = stats.topScorers(4);      
        assertEquals("Lemieux", tulos.get(0).getName());
        
        
    }
    
}
