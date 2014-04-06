
package ohtu.verkkokauppa;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

/**
 *
 * @author Santeri
 */
public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;
    
    public KauppaTest() {
    }

    @Before
    public void setUp() {
        // luodaan ensin mock-oliot ja kauppa
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void metodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunOstetaanYksiTuote() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));
    }
    
    @Test
    public void metodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunOstetaanKaksiEriTuotetta() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // määritellään, että tuote numero 2 on banaari, jonka 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(7));
    }
    
    @Test
    public void metodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunOstetaanKaksiSamaaTuotetta() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(10));
    }
    
    @Test
    public void metodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummallaKunOstetaanKaksiTuotettaJoistaToinenOnLoppu() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));
    }
    
    @Test
    public void metodinAloitaAsiointiKutsuminenNollaaEdellisenOstoksetTiedot() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));
        
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(0));
    }
    
    @Test
    public void varmistetetaanEttaSaadaanAinaUusiViitenumero() {
        // käytetään oikeaa viitegeneraattoria
        viite = new ViitegeneraattoriImpl();
        // luodaan kaupasta uusi olio em. viitegeneraattorilla
        kauppa = new Kauppa(varasto, pankki, viite);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "banaani", 2));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(2);      // ja banaania
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, viitteellä, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), anyString(),eq(5));
        
        // nollataan "sessio" ja ostetaan samat tuotteet uudestaan
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.lisaaKoriin(2);      // ja banaania
        kauppa.tilimaksu("pekka", "12345");
        
        // varmistetaan, että viitenumero on kasvanut odotetusti
        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), anyString(),eq(5));
    }
    
    @Test
    public void varmistetetaanEttaKoristaPoistettuTuotePalautuuVarastoon() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // otetaan oikea varastoluokka käyttöön
        varasto = new VarastoImpl(new KirjanpitoImpl());
        
        // luodaan kaupasta uusi olio oikealla varastolla
        kauppa = new Kauppa(varasto, pankki, viite);
        
        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan Portteria
        kauppa.lisaaKoriin(3);     // ja Nevadaa
        kauppa.poistaKorista(3);   // poistetaan Nevada korista
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, viitteellä, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(3));
    }
}
