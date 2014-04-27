package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;

public class OstoksenLisaysKoriin implements Komento{

    private Ostoskori ostoskori;
    private long tuoteId;
    private Varasto varasto;

    public OstoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        this.ostoskori = ostoskori;
        this.tuoteId = tuoteId;
        this.varasto = Varasto.getInstance();
    }

    @Override
    public boolean suorita() {
        boolean saatiinTuote = varasto.otaVarastosta(tuoteId);
        
        if (!saatiinTuote) {
            return false;
        }
       
        Tuote tuote = varasto.etsiTuote(tuoteId);                      
        ostoskori.lisaaTuote(tuote);                
        return true;

    }
}
