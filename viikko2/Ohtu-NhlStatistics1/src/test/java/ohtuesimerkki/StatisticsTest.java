
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
 * @author Santeri
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
    
    Reader emptyReader = new Reader() {
        
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
            return players;
        }
    };
    
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {
        Player pelaaja = stats.search("Kurri");
        assertEquals("Kurri", "Kurri");
    }
    
    @Test
    public void testSearchWithEmptyPlayerList() {
        this.stats = new Statistics(emptyReader);
        Player pelaaja = stats.search("Kurri");
        assertTrue(pelaaja == null);
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        List<Player> pelaajat = stats.team("EDM");
        assertEquals(pelaajat.get(0).getName(), "Semenko");
        assertEquals(pelaajat.get(1).getName(), "Kurri");
        assertEquals(pelaajat.get(2).getName(), "Gretzky");
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> pistenikkarit = stats.topScorers(3);
        assertEquals(pistenikkarit.get(0).getName(), "Gretzky");
        assertEquals(pistenikkarit.get(1).getName(), "Lemieux");
        assertEquals(pistenikkarit.get(2).getName(), "Yzerman");
    }
    
}
