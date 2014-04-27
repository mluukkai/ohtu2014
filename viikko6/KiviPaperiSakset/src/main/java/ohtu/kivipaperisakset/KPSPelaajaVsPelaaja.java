package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja {

    private Scanner scanner;
    private Tuomari tuomari;
    private String pelaajan1Siirto;
    private String pelaajan2Siirto;
    
    KPSPelaajaVsPelaaja(Scanner scanner, Tuomari tuomari){
        this.scanner = scanner;
        this.tuomari = tuomari;
    }

    public void pelaa() {
        while (true) {
            kysySiirrot();
            if(!validiSiirto(pelaajan1Siirto)|| !validiSiirto(pelaajan2Siirto)){
                break;
            }
            tuomari.kirjaaSiirto(pelaajan1Siirto, pelaajan2Siirto);
            tulostaTulokset();
        }
        System.out.println("Kiitos!");
        tulostaTulokset();
    }
    
    public void tulostaTulokset(){
        System.out.println("");
        System.out.println(tuomari);
    }
    
    public void kysySiirrot(){
        System.out.print("Ensimmäisen pelaajan siirto: ");
        pelaajan1Siirto = scanner.nextLine();
        System.out.print("Toisen pelaajan siirto: ");
        pelaajan2Siirto = scanner.nextLine();
    }

    private static boolean validiSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}