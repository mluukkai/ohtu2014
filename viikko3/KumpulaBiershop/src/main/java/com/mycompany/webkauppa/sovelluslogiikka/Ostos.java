package com.mycompany.webkauppa.sovelluslogiikka;

public class Ostos {

    private int lkm;
    private Tuote tuote;

    public Ostos(Tuote tuote) {
        this.lkm = 1;
        this.tuote = tuote;
    }

    public int hinta() {
        return lkm * tuote.getHinta();
    }

    public int lukumaara() {
        return lkm;
    }

    public String tuotteenNimi() {
        return tuote.getNimi();
    }

    public long tuotteenId() {
        return tuote.getId();
    }

    public void muutaLukumaaraa(int muutos) {
        lkm += muutos;
        if ( lkm<0 ) lkm = 0;
    }
}
