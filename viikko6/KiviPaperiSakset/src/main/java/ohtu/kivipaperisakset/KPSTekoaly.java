package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly {

    private Scanner scanner;
    private Tuomari tuomari;
    private Tekoaly tekoaly;
    private String pelaajanSiirto;
    private String koneenSiirto;

    KPSTekoaly(Scanner scanner, Tuomari tuomari) {
         this.scanner = scanner;
         this.tuomari = tuomari;
         this.tekoaly = new Tekoaly();
    }

    public void pelaa() {
        while (true) {
            kysySiirrot();
            if(!validiSiirto(pelaajanSiirto)){
                break;
            }
            tuomari.kirjaaSiirto(pelaajanSiirto, koneenSiirto);
            tulostaTulokset();     
        }
        System.out.println("Kiitos!");
        tulostaTulokset();
    }
    
    public void tulostaTulokset(){
        System.out.println(tuomari);
        System.out.println();
    }
    
    public void kysySiirrot(){
        System.out.print("Ensimmäisen pelaajan siirto: ");
        pelaajanSiirto = scanner.nextLine();
        koneenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + koneenSiirto);   
    }

    private static boolean validiSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}