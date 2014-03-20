package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ljleppan@cs
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
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchReturnsExistingPlayer(){
        assertEquals("Semenko", stats.search("Semenko").getName());
    }
    
    @Test
    public void searchReturnsNullForNonExistantPlayer(){
        assertEquals(null, stats.search("Nope"));
    }

    @Test
    public void teamReturnsAllPlayersFromTeam(){
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
        for(Player p : players){
            assertEquals("EDM", p.getTeam());
        }
    }
    
    @Test
    public void topReturnsCorrectNumberOfPlayers(){
        assertEquals(1, stats.topScorers(0).size());
        assertEquals(2, stats.topScorers(1).size());
        assertEquals(3, stats.topScorers(2).size());
    }
    
    @Test
    public void topReturnCorrectPlayers(){
        List<Player> top = stats.topScorers(3);
        assertEquals("Gretzky", top.get(0).getName());
        assertEquals("Lemieux", top.get(1).getName());
        assertEquals("Yzerman", top.get(2).getName());
    }
}
