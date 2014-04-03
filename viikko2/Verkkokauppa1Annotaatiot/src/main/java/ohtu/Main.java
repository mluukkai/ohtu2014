package ohtu;

import ohtu.verkkokauppa.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

// Annotaatioilla konfiguroitu.
public class Main {

    public static void main(String[] args) {
        
//        Kirjanpito kirjanpito = new Kirjanpito();
//        Varasto varasto = new Varasto(kirjanpito);
//        Pankki pankki = new Pankki(kirjanpito);
//        Viitegeneraattori viitegen = new Viitegeneraattori();
//        Kauppa kauppa = new Kauppa(varasto, pankki, viitegen);
        
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        Kirjanpito kirjanpito = (Kirjanpito) ctx.getBean("kirjanpito");
        Varasto varasto = (Varasto) ctx.getBean("varasto");
        Pankki pankki = (Pankki) ctx.getBean("pankki");
        Viitegeneraattori viitegen = (Viitegeneraattori) ctx.getBean("viitegeneraattori");
        Kauppa kauppa = (Kauppa) ctx.getBean("kauppa");
        
        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
