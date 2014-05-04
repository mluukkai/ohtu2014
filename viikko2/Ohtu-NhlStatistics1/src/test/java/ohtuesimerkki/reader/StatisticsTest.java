package ohtuesimerkki.reader;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
    
    Reader readerStub2 = new Reader() {

    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        return players;
    }
    };

   @Test
   public void searchPlayerTest() {
        String pelaaja = "Kurri";
        Player player = new Statistics(readerStub).search(pelaaja);
        assertEquals(pelaaja,player.getName());
	assertEquals("EDM",player.getTeam());
	assertEquals(90,player.getPoints());
    }
	
   @Test
   public void searchEmptyPlayerTest() {
        String pelaaja = "Kurri";
        Player player = new Statistics(readerStub2).search(pelaaja);
        assertEquals(null,player);
    }
   
   @Test
   public void teamTest() {
        String team = "PIT";
        List<Player> players = new Statistics(readerStub).team(team);
        assertEquals("Lemieux",players.get(0).getName());
    }

   @Test
   public void topScorersTest() {
        List<Player> players = new Statistics(readerStub).topScorers(3);
        assertEquals("Gretzky",players.get(0).getName());
        assertEquals("Lemieux",players.get(1).getName());
    }
   
   
}


