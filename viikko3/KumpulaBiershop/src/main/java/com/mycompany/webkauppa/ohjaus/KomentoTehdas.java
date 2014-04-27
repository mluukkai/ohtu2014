/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;

/**
 *
 * @author joonas
 */
public class KomentoTehdas {
    
    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId){
        return new OstoksenLisaysKoriin(ostoskori, tuoteId);
    }
    
    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId){
        return new OstoksenPoistoKorista(ostoskori, tuoteId);
    }
    
    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori){
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori);
    }
    
}
