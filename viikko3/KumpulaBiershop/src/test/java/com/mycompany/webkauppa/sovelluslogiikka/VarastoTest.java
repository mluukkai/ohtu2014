
package com.mycompany.webkauppa.sovelluslogiikka;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {
    Varasto varasto;

    @Before
    public void setUp() {
	Varasto.resetInstance();
	varasto = Varasto.getInstance();
    }

    @Test
    public void uudenTuotteenVoiLisata() {
        boolean ok = varasto.lisaaTuote("Koff", 3, 10);
        
        assertTrue(ok);
        assertTrue( onVarastossa("Koff") );        
    }

    @Test
    public void kahtaSamannimistaEiVoiLisata() {
        boolean ok = varasto.lisaaTuote("Koff", 3, 10);
        
        assertFalse(ok);
        assertEquals( 1, tuotteitaVarastossa("Koff") );        
    }
    
    private int tuotteitaVarastossa(String tuotteenNimi) {
        int lkm = 0;
        
        for (Tuote tuote : varasto.tuotteidenLista()) {
            if ( tuote.getNimi().equals(tuotteenNimi)) lkm++;
        }        
        
        return lkm;
    }
    
    private boolean onVarastossa(String tuotteenNimi) {
        return tuotteitaVarastossa(tuotteenNimi)>0;
    }
    
    private long tuotteenId(String tuotteenNimi) {

        for (Tuote tuote : varasto.tuotteidenLista()) {
            if ( tuote.getNimi().equals(tuotteenNimi)) 
                return tuote.getId();
        }        
        
        return -1;
    }
}
