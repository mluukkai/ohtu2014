package ohtuesimerkki;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrikorpela
 */
public class StatiscticTest {
    private Statistics stat;
    
    public StatiscticTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.stat = new Statistics(new Test_Player_Reader());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void search_test() 
    {
        assertEquals(this.stat.search("Hawk_One").getName(), "Hawk_One");
        assertEquals(this.stat.search("Bronco_One").getName(), "Bronco_One");
        assertEquals(this.stat.search("No player"), null);
    }
    
    @Test
    public void team_test()
    {
        assertEquals(this.contains_all_seahawks(this.stat.team("Seahawks")), true);
        assertEquals(this.contains_all_broncos(this.stat.team("Broncos")), true);
        assertEquals(this.stat.team("No team").isEmpty(), true);
    }
    
    @Test
    public void top_scores_test()
    {
        List<Player> top_0 = this.stat.topScorers(-1);
        List<Player> top_1 = this.stat.topScorers(1);
        List<Player> top_2 = this.stat.topScorers(2);
        
        assertEquals(top_0.isEmpty(), true);
        assertEquals(top_1.get(0).getName(), "Hawk_Tree");
        assertEquals(top_2.get(0).getName(), "Hawk_Tree");
        assertEquals(top_2.get(1).getName(), "Hawk_Two");
    }
    
    private boolean contains_all_seahawks(List<Player> team)
    {
        Player one = this.stat.search("Hawk_One");
        Player two = this.stat.search("Hawk_Two");
        Player tree = this.stat.search("Hawk_Tree");
        
        if(team.contains(one) && team.contains(two) && team.contains(tree))
        {
            return true;
        }
        
        return false;
    }
    
    private boolean contains_all_broncos(List<Player> team)
    {
        Player one = this.stat.search("Bronco_One");
        Player two = this.stat.search("Bronco_Two");
        Player tree = this.stat.search("Bronco_Tree");
        
        if(team.contains(one) && team.contains(two) && team.contains(tree))
        {
            return true;
        }
        
        return false;
    }
}
