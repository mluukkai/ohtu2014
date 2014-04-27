package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Pelialusta {
    
    private Scanner scanner;
    private KPSPelaajaVsPelaaja kaksinpeli;
    private KPSTekoaly yksinpeli;
    private KPSParempiTekoaly pahaYksinpeli;
    private Tuomari tuomari;

    Pelialusta(Scanner scanner) {
        this.scanner = scanner;
        this.tuomari = new Tuomari();
        this.kaksinpeli = new KPSPelaajaVsPelaaja(scanner, tuomari);
        this.yksinpeli = new KPSTekoaly(scanner, tuomari);
        this.pahaYksinpeli = new KPSParempiTekoaly(scanner, tuomari);
        
    }

    void suorita() {
        boolean jatkuu = true;
        while (jatkuu) {
            String vastaus = kysyToiminto();
            switch (vastaus) {
                case "a": kaksinpeli.pelaa(); break;
                case "b": yksinpeli.pelaa(); break;
                case "c": pahaYksinpeli.pelaa(); break;
                default: jatkuu = false; break;
            }
        }
    }

    private String kysyToiminto() {
        tulostaOhjeet();
        String vastaus = scanner.nextLine();
        tulostaSaannot();
        return vastaus;
    }

    private void tulostaSaannot() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
    }

    private void tulostaOhjeet() {
        System.out.println("\nValitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetataan");
    }
    
}
