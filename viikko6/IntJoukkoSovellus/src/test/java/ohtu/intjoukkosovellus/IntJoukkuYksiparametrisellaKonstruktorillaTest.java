
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(4, 2);
        joukko.lisaa(10);
        joukko.lisaa(3);
        joukko2 = new IntJoukko(4, 2);
        joukko2.lisaa(4);
        joukko2.lisaa(3);
        tyhja = new IntJoukko(4,2);
    }
    
    @Test
    public void nollaKapasiteettiOletus(){
        IntJoukko tmp = new IntJoukko(0);
        tmp.lisaa(3);
        assertEquals(0, tmp.mahtavuus());
    }
    
    // perii kaikki testit luokasta IntJoukkoTest
}
