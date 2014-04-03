/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author maef
 */
public class VerkkokauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaTiedoilla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    }
    
    @Test
    public void kunKorissaKaksiEriTuotettaTilisiirtoKutsutaanOikeillaTiedoilla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sweetroll", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(15));   
    }
    
    @Test
    public void kunKorissaKaksiSamaaTuotettaTilisiirtoKutsutaanOikeillaTiedoilla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));   
    }
    
    @Test
    public void kunKoriinLisataanKaksiEriOstostaJoistaToinenOnLoppuVeloitetaanOikeaSumma() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sweetroll", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    }
    
    @Test
    public void kunAloitetaanAsiointiEdellinenOstosEiNäyUudenHinnassa() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sweetroll", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));   
    }
    
    @Test
    public void jokaisellaMaksutapahtumallaOmaViitenumero() {
        when(viite.uusi()).thenCallRealMethod();
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sweetroll", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(0), anyString(), anyString(), anyInt());

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
    }
    @Test
    public void kunKoristaPoistetaanTuoteSitaEiVeloiteta() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sweetroll", 10));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
    }
}
