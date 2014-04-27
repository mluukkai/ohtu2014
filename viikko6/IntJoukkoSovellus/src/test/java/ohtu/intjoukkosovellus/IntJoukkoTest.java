package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;
    IntJoukko joukko2;
    IntJoukko tyhja;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaa(10);
        joukko.lisaa(3);
        joukko2 = new IntJoukko();
        joukko2.lisaa(4);
        joukko.lisaa(3);
        tyhja = new IntJoukko();
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaa(4);
        assertEquals(3, joukko.mahtavuus());
    }
    
    @Test
    public void tyhjanTulostus() {
        assertEquals(0, tyhja.mahtavuus());
        assertEquals("{}", tyhja.toString());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaa(10);
        joukko.lisaa(3);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluu(10));
        assertFalse(joukko.kuuluu(5));
        assertTrue(joukko.kuuluu(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.kuuluu(3));
        assertEquals(1, joukko.mahtavuus());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaa(55);
        joukko.poista(10);
        joukko.lisaa(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaa(luku);
        }
        assertEquals(14, joukko.mahtavuus());
        assertTrue(joukko.kuuluu(11));
        joukko.poista(11);
        assertFalse(joukko.kuuluu(11));
        assertEquals(13, joukko.mahtavuus());
    }
    
    @Test 
    public void leikkausToimii(){
        IntJoukko leikkausJoukko;
        leikkausJoukko = IntJoukko.leikkaus(joukko, joukko2);
        assertEquals(1, leikkausJoukko.mahtavuus());
    }
    
    @Test
    public void erotusToimii(){
        IntJoukko erotusJoukko;
        erotusJoukko = IntJoukko.erotus(joukko, joukko2);
        assertTrue(erotusJoukko.kuuluu(10));
        assertEquals(2, erotusJoukko.mahtavuus());
    }
    
    @Test
    public void yhdisteToimii(){
        IntJoukko yhdisteJoukko;
        yhdisteJoukko = IntJoukko.yhdiste(joukko, joukko2);
        assertEquals(3, yhdisteJoukko.mahtavuus());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
}
