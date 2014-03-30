package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.*;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.*;

public class OstoksenSuoritus {

    private PankkiFasaadi pankki;
    private ToimitusjarjestelmaFasaadi toimitusjarjestelma;
    private String asiakkaanNimi;
    private String postitusosoite;
    private String luottokortti;
    private Ostoskori ostoskori;
    private Varasto varasto;

    public OstoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        this.varasto = Varasto.getInstance();
        this.pankki = PankkiFasaadi.getInstance();
        this.toimitusjarjestelma = ToimitusjarjestelmaFasaadi.getInstance();
        this.asiakkaanNimi = nimi;
        this.postitusosoite = osoite;
        this.luottokortti = luottokorttinumero;
        this.ostoskori = kori;
    }

    public boolean suorita() {
        if ( asiakkaanNimi.length()==0 || postitusosoite.length()==0 || ostoskori.tuotteitaKorissa()==0 )
            return false;
        
        if (!pankki.maksa(asiakkaanNimi, luottokortti, ostoskori.hinta())) {
            return false;
        }

        toimitusjarjestelma.kirjaatoimitus(asiakkaanNimi, postitusosoite, ostoskori.ostokset());
        ostoskori.tyhjenna();
        
        return true;
    }

    public void setPankki(PankkiFasaadi pankki) {
        this.pankki = pankki;
    }        
}
