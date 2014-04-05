
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Joel
 */
public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa kauppa;

    public KauppaTest() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        when(viite.uusi()).thenReturn(42, 43, 44);
        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        setWarehouse(1, "maito", 5, 2);

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        pay();

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        setWarehouse(1, "maito", 5, 2);

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        checkPayment(5);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void kahdenOstoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        setWarehouse(1, "maito", 5, 1);
        setWarehouse(2, "leip'", 2, 1);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        
        pay();

        checkPayment(7);
    }

    @Test
    public void kahdenSamanTuotteenOnnistuneenOstamisenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        setWarehouse(1, "maito", 5, 2);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        
        pay();

        checkPayment(10);
    }

    @Test
    public void TiliSiirtoKutsutaanOikeillaArvoillaYhdenOnnistuneenJaYhdenEpaonnistuneenOstonJalkeen() {
        setWarehouse(1, "maito", 5, 2);
        setWarehouse(2, "leipä", 5, 0);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        pay();
        
        checkPayment(5);
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        setWarehouse(1, "maito", 5, 10);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        pay();
        
        checkPayment(5);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        pay();
        
        verify(pankki).tilisiirto("pekka", 43, "12345", "33333-44455", 15);
    }
    
    @Test
    public void saadaanUusiViiteJokaiselleMaksuTapahtumalle() {
        setWarehouse(1, "maito", 5, 10);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        pay();
        
        verify(pankki).tilisiirto(anyString(), eq(42), anyString(), anyString(), anyInt());
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        pay();
        
        verify(pankki).tilisiirto(anyString(), eq(43), anyString(), anyString(), anyInt());
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        pay();
        
        verify(pankki).tilisiirto(anyString(), eq(44), anyString(), anyString(), anyInt());
        
    }

    private void setWarehouse(int id, String name, int price, int howMany) {
        when(varasto.saldo(id)).thenReturn(howMany);
        when(varasto.haeTuote(id)).thenReturn(new Tuote(id, name, price));
    }
    
    private void pay() {
        kauppa.tilimaksu("pekka", "12345");
    }
    
    private void checkPayment(int payment) {
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", payment);
    }

}
