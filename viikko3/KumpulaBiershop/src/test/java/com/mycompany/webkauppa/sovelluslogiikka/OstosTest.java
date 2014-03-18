package com.mycompany.webkauppa.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstosTest {

    Ostos ostos;
    Tuote tuote;

    @Before
    public void setUp() {
        tuote = new Tuote(1, "Koff", 3);
        ostos = new Ostos(tuote);
    }

    @Test
    public void luodunOstoksenAttribuutitOikein() {
        assertEquals(1, ostos.lukumaara());
        assertEquals(tuote.getHinta(), ostos.hinta());
        assertEquals(tuote.getNimi(), ostos.tuotteenNimi());
        assertEquals(tuote.getId(), ostos.tuotteenId());
    }

    @Test
    public void hintaJaLukumaaraOikeinJosOstoksessaMontaTuotetta() {
        ostos.muutaLukumaaraa(2);

        assertEquals(3, ostos.lukumaara());
        assertEquals(tuote.getHinta()*3, ostos.hinta());
    }
    
    @Test
    public void lukumaaraJaHintaVahintaanNolla() {
        ostos.muutaLukumaaraa(-2);

        assertEquals(0, ostos.lukumaara());
        assertEquals(0, ostos.hinta());
    }
}
