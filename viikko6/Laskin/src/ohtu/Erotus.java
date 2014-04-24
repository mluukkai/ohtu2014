/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author henrikorpela
 */
public class Erotus extends Komento {

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }
    
    @Override
    public void suorita() {
        super.suorita();
        super.sovellus.miinus(super.getLuku());
        super.paivitaKentat();
    }
}
