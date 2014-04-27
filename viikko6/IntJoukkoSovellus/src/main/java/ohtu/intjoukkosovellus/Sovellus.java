/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.intjoukkosovellus;

import java.util.Scanner;

/**
 *
 * @author joonas
 */
public class Sovellus {
    
    private static IntJoukko A, B, C;
    private Scanner lukija;
    
    Sovellus(Scanner scanner){
        this.lukija = scanner;


    }

    private String luku() {
        String luettu = lukija.nextLine();
        return luettu;
    }

    private IntJoukko mikaJoukko() {
        while (true) {
            String luettu = luku();
            switch (luettu.toLowerCase()) {
                case "a": return A;
                case "b": return B;
                case "c": return C;
                default: printError(luettu); break;
            }
        }
    }

    private void printError(String luettu) {
        System.out.println("Virheellinen joukko! " + luettu);
        System.out.print("Yritä uudelleen!");
    }

    public void lisaa() {
        IntJoukko joukko;
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko();
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        int lisLuku = lukija.nextInt();
        joukko.lisaa(lisLuku);
    }

    public void yhdiste() {
        IntJoukko aJoukko, bJoukko;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        System.out.println("A yhdiste B = " + IntJoukko.yhdiste(aJoukko, bJoukko));
    }

    public void leikkaus() {
        IntJoukko aJoukko, bJoukko;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        System.out.println("A leikkaus B = " + IntJoukko.leikkaus(aJoukko, bJoukko));
    }

    public void erotus() {
        IntJoukko aJoukko, bJoukko;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        System.out.println("A erotus B = " + IntJoukko.erotus(aJoukko, bJoukko));
    }

    public void poista() {
        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        int poistaLuku = lukija.nextInt();
        joukko.poista(poistaLuku);
    }

    public void kuuluu() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku? ");
        int kysLuku = lukija.nextInt();
        if (joukko.kuuluu(kysLuku)) {
            System.out.println(kysLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(kysLuku + " ei kuulu joukkoon ");
        }
    }

    public void run() {
        alustaJoukot();
        tulostaInfo();
        toiminto();
    }
    
    private void toiminto(){
        while (true) { 
            String luettu = lukija.nextLine();
            if (luettu.equals("lisää") || luettu.equals("poista") || luettu.equals("yhdiste") || luettu.equals("leikkaus")|| luettu.equals("erotus") || luettu.equals("kuuluu")) {
                suoritaToiminto(luettu.toLowerCase());
            } else if (luettu.equalsIgnoreCase("A") || luettu.equalsIgnoreCase("B") || luettu.equalsIgnoreCase("C")) {
                tulostaJoukko(luettu.toLowerCase());
            } else if (luettu.equalsIgnoreCase("lopeta")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                virhe(luettu);
            }
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");      
        }
    }

    private void virhe(String luettu) {
        System.out.println("Virheellinen komento! " + luettu);
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
    }
    
    
    public void suoritaToiminto(String toiminto){
        switch (toiminto){
            case "lisaa": lisaa();
            case "poista": poista();
            case "kuuluu": kuuluu();
            case "yhdiste": yhdiste();
            case "leikkaus": leikkaus();
            case "erotus": erotus();
        }
    }
    public void tulostaJoukko(String joukko){
        switch(joukko){
            case "a": System.out.println(A);
            case "b": System.out.println(B);
            case "c": System.out.println(C);    
        }
    }
    private void alustaJoukot() {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();
    }

    private void tulostaInfo() {
        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");
    }

}
