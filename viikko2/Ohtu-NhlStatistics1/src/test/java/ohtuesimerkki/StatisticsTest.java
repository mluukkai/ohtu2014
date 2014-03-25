/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vesihiisi
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {
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
    
    @Before
    public void setUp() {
        stats=new Statistics(readerStub);
    }
    
    /**
     * Testaataan search metodi ensin onnistuneella searchilla ja sitten epäonnistuneella
     * 
     */
    @Test
    public void testSearchLoytyy() {
        Player p=stats.search("Kurri");
        assertEquals(p.compareTo(readerStub.getPlayers().get(2)),0);
    }
    
    @Test
    public void testSearchEiLoydy() {
        assertTrue(stats.search("asdasd")==null);
    }

    /**
     * Testataan team -metodi ensin kolmella jäsenellä ja sitten siten että jäseniä on nolla
     */
    @Test
    public void testTeam3Jasenta() {
        List<Player> lista=stats.team("EDM");
        assertEquals(lista.size(), 3);
    }
    
    @Test
    public void testTeam0Jasenta(){
        List<Player> lista=stats.team("asd");
        assertEquals(lista.size(), 0);
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> lista=stats.topScorers(3);
        assertEquals(lista.size(), 3);
    }    
    
    @Test
    public void testTopScorersTyhja() {
        List<Player> lista=stats.topScorers(0);
        assertEquals(lista.size(), 0);
    }    
//     @Test
//    public void topPlayerMethodReturnEmptyList() {
//        assertTrue(stats.topScorers(0).isEmpty());
//    }
//    
//    @Test
//    public void topThreeReturnsListOfSizeThree() {
//        assertEquals(3, stats.topScorers(3).size());
//        assertEquals("Gretzky", stats.topScorers(1).get(0).getName()); // Gretzky should have the best score
//    }

}
