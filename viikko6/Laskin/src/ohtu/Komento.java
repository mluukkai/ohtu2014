/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;

/**
 *
 * @author henrikorpela
 */
public abstract class Komento {
    protected Sovelluslogiikka sovellus;
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected int undoArvo;
    
    public Komento(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.undoArvo = 0;
    }
    
    public void suorita() {
        this.undoArvo = sovellus.tulos();
    }
    
    public void peru() {
        this.tuloskentta.setText("" + this.undoArvo);
        this.sovellus.setTulos(undoArvo);
    }
    
    protected int getLuku() {
        try {
            return Integer.parseInt(this.syotekentta.getText());
        } catch(Exception e) {
            return 0;
        }
    }
    
    protected void paivitaKentat() {
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }
}
