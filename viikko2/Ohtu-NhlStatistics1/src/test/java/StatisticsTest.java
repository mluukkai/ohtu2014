
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class StatisticsTest {
    Statistics s;
    
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
        s = new Statistics(new TestReader());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void searchingPlayers(){
        Player p1 = s.search("Heimo");
        Player p2 = s.search("Aku Ankka");
        assertEquals(p1.getName(), "Heimo Vesa");
        assertEquals(p2.getName(), "Aku Ankka");
        assertEquals(p1.getGoals(), 1);
    }
    
    @Test 
    public void searchPlayerWhoDoesntExist(){
        assertEquals(s.search("Oispa kaljaa!"), null);
    }
    
    @Test
    public void findTeam(){
        List<Player> l1 = s.team("Team Fingerpori");
        List<Player> l2 = s.team("Kvaak");
        assertEquals(l1.size(), 2);
        assertEquals(l2.size(), 1);
        assertEquals(l2.get(0).getName(), "Aku Ankka");
    }
    
    @Test
    public void testTopScores(){
        List<Player> l = s.topScorers(2);
        
        assertEquals(l.get(0).getPoints(), 6);
        assertEquals(l.get(1).getPoints(), 3);
        assertEquals(l.get(2).getPoints(), 3);
    }
    
}
