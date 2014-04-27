package ohtu.kivipaperisakset;


import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly {

    private Scanner scanner;
    private Tuomari tuomari;
    private TekoalyParannettu tekoaly;
    private String pelaajanSiirto;
    private String koneenSiirto;

    KPSParempiTekoaly(Scanner scanner, Tuomari tuomari) {
        this.scanner = scanner;
        this.tuomari = tuomari;
        this.tekoaly = new TekoalyParannettu(20);
    }

    public void pelaa() {
        while (true) {
            kysySiirto();
            if(!validiSiirto(pelaajanSiirto)){
                break;
            }
            tuomari.kirjaaSiirto(pelaajanSiirto, koneenSiirto);
            tulostaTulokset();
            tekoaly.asetaSiirto(pelaajanSiirto);
        }
        System.out.println("Kiitos!");
        tulostaTulokset();

    }
    
    public void tulostaTulokset(){
        System.out.println();
        System.out.println(tuomari);
    }
    
    public void kysySiirto(){
        System.out.print("Ensimmäisen pelaajan siirto: ");
        pelaajanSiirto = scanner.nextLine();
        koneenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + koneenSiirto); 
    }

    private static boolean validiSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
