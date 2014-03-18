package com.mycompany.webkauppa.sovelluslogiikka;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;
    Tuote tuote1;
    Tuote tuote2;
    
    @Before
    public void setUp() {
        kori = new Ostoskori();
        tuote1 = new Tuote("Lapin kulta", 2);  
        tuote1.setId(1);
        tuote2 = new Tuote("Karhu", 3); 
        tuote2.setId(2);
    }

    @Test
    public void luotuKoriOnTyhja() {
        assertTyhja(kori); 
    }
    
    // yksi tuote
    
    @Test
    public void tuotteenLisaysKasvattaaKorinHintaaJaTuotemaaraa(){
        kori.lisaaTuote(tuote1);
        
        assertEquals(tuote1.getHinta(), kori.hinta());
        assertEquals(1, kori.tuotteitaKorissa());
    }
    
    @Test
    public void lisattyTuoteOnOstostenListalla(){
        kori.lisaaTuote(tuote1);
        
        ArrayList<Ostos> ostokset = kori.ostokset();
        
        assertEquals(tuote1.getNimi(), ostokset.get(0).tuotteenNimi() );
    }
    
    @Test
    public void tyhjennetynOstoskorinHintaOnNolla(){
        kori.lisaaTuote(tuote1);
        
        kori.tyhjenna();
        
        assertTyhja(kori); 
    }
    
    @Test
    public void tuoteVoidaanPoistaaOstoskorista(){
        kori.lisaaTuote(tuote1);
        
        kori.poista(tuote1);
        
        assertTyhja(kori);      
    }

    // kaksi eri tuotetta
    
    @Test
    public void kaksiEriTuotettaSisaltavanKorinHintaJaLukumaaraOikein() {
        kori.lisaaTuote(tuote1); 
        kori.lisaaTuote(tuote2);
        
        assertEquals(2, kori.ostokset().size());
        assertEquals(tuote1.getHinta()+tuote2.getHinta(), kori.hinta());
    }
    
    @Test
    public void molemmatLisatytTuotteetListalla() {
        kori.lisaaTuote(tuote1); 
        kori.lisaaTuote(tuote2);
        
        assertEquals(2, kori.ostokset().size());
        
        ArrayList<String> ostetutTuotteet = new ArrayList<String>();
        for (Ostos ostos : kori.ostokset()) {
            ostetutTuotteet.add(ostos.tuotteenNimi());
        }
        
        assertTrue( ostetutTuotteet.contains(tuote1.getNimi()) );
        assertTrue( ostetutTuotteet.contains(tuote2.getNimi()) );
    }
    
    @Test
    public void kaksiTuotettaSisaltavastaKoristaPoistoToimii() {
        kori.lisaaTuote(tuote1); 
        kori.lisaaTuote(tuote2);
        
        kori.poista(tuote1);
        
        assertEquals(1, kori.ostokset().size());
        assertEquals(tuote2.getHinta(), kori.hinta());
        assertEquals(tuote2.getNimi(), kori.ostokset().get(0).tuotteenNimi() );
    }

    // kaksi samaa tuotetta       
    
    @Test
    public void kaksiSamaaTuotettaNakyvatYhtenaOstoksena() {
        kori.lisaaTuote(tuote1); 
        kori.lisaaTuote(tuote1);
        
        assertEquals(1, kori.ostokset().size());
        assertEquals(2, kori.tuotteitaKorissa());
        assertEquals(tuote1.getHinta()*2, kori.hinta() );
    }

    @Test
    public void kahdenTuotteenOstoksestaVoiPoistaaToisen() {
        kori.lisaaTuote(tuote1); 
        kori.lisaaTuote(tuote1);
        
        kori.poista(tuote1);
        
        assertEquals(1, kori.ostokset().size());
        assertEquals(1, kori.tuotteitaKorissa());
        assertEquals(tuote1.getHinta(), kori.hinta() );
    }
    
    //
    
    private void assertTyhja(Ostoskori kori) {
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tuotteitaKorissa());
        assertEquals(0, kori.ostokset().size());
    }
    
}
