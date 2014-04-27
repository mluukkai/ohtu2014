
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
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
    public void nollaParameillaOletus(){
        IntJoukko nollaJoukko = new IntJoukko(0,0);
        nollaJoukko.lisaa(2);
        nollaJoukko.lisaa(3);
        assertEquals(0, nollaJoukko.mahtavuus());
    }
}
