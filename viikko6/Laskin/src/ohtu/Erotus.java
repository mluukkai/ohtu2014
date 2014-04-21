/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author joonas
 */
class Erotus implements Komento {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta; 
    private int undo;

    Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        int arvo = Integer.parseInt(syotekentta.getText());
        this.undo = arvo;
        sovellus.miinus(arvo);
        int tulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }

    @Override
    public void peru() {
        sovellus.plus(undo);
        int tulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }
    
}
