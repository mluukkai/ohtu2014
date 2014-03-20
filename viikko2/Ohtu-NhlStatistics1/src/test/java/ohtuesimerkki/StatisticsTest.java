package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class for Statistics.
 * @author Juho Hautala
 */
public class StatisticsTest {

    private Player kurri = new Player("Kurri",   "EDM", 37, 53); // 90
    private Statistics stats;

    private Reader readerStub = new Reader() {
        @Override
        public List getPlayers() {
            List<Player> players = new ArrayList<Player>();
            players.add(kurri);
            players.add(new Player("Semenko", "EDM", 4, 12));  // 16
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 99
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
            return players;
        }
    };

    public StatisticsTest() {
        this.stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsFoundPlayer() {
        assertEquals(kurri, stats.search("Kurri"));
    }

    @Test
    public void searchReturnsNullWhenPlayerNotFound() {
        assertEquals(null, stats.search("Koivu"));
    }

    @Test
    public void teamReturnsSizeOfTeamPlayers() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }

    @Test
    public void teamReturnsTeamPlayers() {
        List<Player> players = stats.team("EDM");
        assertEquals("Kurri", players.get(0).getName());
        assertEquals("Semenko", players.get(1).getName());
        assertEquals("Gretzky", players.get(2).getName());
    }

    @Test
    public void teamReturnsNoPlayersWhenTeamNotExists() {
        List<Player> players = stats.team("Jokamiehet");
        assertNotNull(players);
        assertEquals(0, players.size());
    }

    @Test
    public void topScorersReturnsNoPlayersWhenNegParamGiven() {
        List<Player> players = stats.topScorers(-1);
        assertNotNull(players);
        assertEquals(0, players.size());
    }

    @Test
    public void topScorersReturnsCorrectNumberOfPlayers() {
        List<Player> players = stats.topScorers(2);
        assertEquals(2, players.size());
    }

    @Test
    public void topScorersReturnsPlayersInOrder() {
        List<Player> players = stats.topScorers(2);
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
    }

}
