package mocktest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author henrikorpela
 */
public class KauppaTest {
    private Kauppa kauppa;
    private Pankki pankki;
    private Varasto varasto;
    private Viitegeneraattori viitegenraattori;
    
    public KauppaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pankki = mock(Pankki.class);
        this.varasto = mock(Varasto.class);
        this.viitegenraattori = mock(Viitegeneraattori.class);
        this.kauppa = new Kauppa(this.varasto, this.pankki,this.viitegenraattori);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void LisaaYksiTuoteKoriinJolloinOikeaTilisiirto() 
    {
        this.resetKauppa();
        
        when(varasto.saldo(1)).thenReturn(1);
        when(this.viitegenraattori.uusi()).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 0, "12345", "33333-44455", 2);
    }
    
    @Test
    public void LisaaKoriinKaksiEriJolloinOikeaTilisiirto()
    {
        this.resetKauppa();
        
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(2);
        
        when(this.viitegenraattori.uusi()).thenReturn(1);
        
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "Tuote B", 8));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 10);
    }
    
    @Test
    public void LisaaKoriinKaksiSamaaJolloinOikeaTilisiirto()
    {
        this.resetKauppa();
        
        when(varasto.saldo(1)).thenReturn(4);
        
        when(this.viitegenraattori.uusi()).thenReturn(1);
        
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 4);
    }
    
    @Test
    public void LisaaKoriinTuoteJokaOnLopuuJaTuoteJotaOnOikeaTilisiirto()
    {
        this.resetKauppa();
        
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(0);
        
        when(this.viitegenraattori.uusi()).thenReturn(1);
        
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "Tuote B", 8));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 2);
    }
    
    @Test
    public void aloitaAsiontiNollaaEdeliisenOstonTiedot()
    {
        this.resetKauppa();
        
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(2);
        
        when(this.viitegenraattori.uusi()).thenReturn(1);
        
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "Tuote B", 8));
        
        kauppa.aloitaAsiointi();
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        
        kauppa.aloitaAsiointi();
        
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 0);
    }
    
    @Test
    public void omaViitenumeroJokaMaksutapahtumalle()
    {
        when(this.viitegenraattori.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
        
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(2);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Tuote A", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "Tuote B", 8));
        
        kauppa.aloitaAsiointi();
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        
        kauppa.aloitaAsiointi();
        
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 0);
        
        kauppa.aloitaAsiointi();
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        
        kauppa.aloitaAsiointi();
        
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 2, "12345", "33333-44455", 0);
        
        kauppa.aloitaAsiointi();
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        
        kauppa.aloitaAsiointi();
        
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 3, "12345", "33333-44455", 0);
    }
    
    @Test
    public void poistaOstoskorista()
    {
        when(varasto.saldo(1)).thenReturn(1);
        Tuote a = new Tuote(1, "Tuote A", 2);
        when(varasto.haeTuote(1)).thenReturn(a);
        
        this.kauppa.aloitaAsiointi();
        this.kauppa.lisaaKoriin(1);
        this.kauppa.poistaKorista(1);
        
        verify(this.varasto).palautaVarastoon(a);
    }
    
    private void resetKauppa()
    {
        this.kauppa = new Kauppa(this.varasto, this.pankki,this.viitegenraattori);
    }
}
