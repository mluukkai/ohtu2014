package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
 //       komennot.put(undo, new Palaa(sovellus, tuloskentta, syotekentta));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());
        if  (komento!=null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }
        
        nollaa.setEnabled(sovellus.tulos()!=0);
        undo.setEnabled(edellinen!=null);
    }

    private static class Summa implements Komento {
        private Sovelluslogiikka sov;
        private JTextField tulos;
        private JTextField syote;

        public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
            sov = sovellus;
            tulos = tuloskentta;
            syote = syotekentta;    
        }
        
        @Override
        public void suorita() {
        int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syote.getText());
        } catch (Exception e) {
        }

        sov.plus(arvo);

        int laskunTulos = sov.tulos();
         
        syote.setText("");
        tulos.setText("" + laskunTulos);
        }
        
        @Override
        public void peru() {
        sov.palauta();
        
        int laskunTulos = sov.tulos();        
        
        syote.setText("");
        tulos.setText("" + laskunTulos);
        }
    }

    private static class Erotus implements Komento {
        private Sovelluslogiikka sov;
        private JTextField tulos;
        private JTextField syote;  
        
        public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {  
            sov = sovellus;
            tulos = tuloskentta;
            syote = syotekentta;              
        }
        
        @Override
        public void suorita() {
        int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syote.getText());
        } catch (Exception e) {
        }

        sov.miinus(arvo);

        int laskunTulos = sov.tulos();
         
        syote.setText("");
        tulos.setText("" + laskunTulos);            

        }        
        
        @Override
        public void peru() {
        sov.palauta();
        
        int laskunTulos = sov.tulos();        
        
        syote.setText("");
        tulos.setText("" + laskunTulos);
        }
    }

    private static class Nollaa implements Komento {
        private Sovelluslogiikka sov;
        private JTextField tulos;
        private JTextField syote;  

        public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
            sov = sovellus;
            tulos = tuloskentta;            
            syote = syotekentta;              
        }
        
        @Override
        public void suorita() {
             
        sov.nollaa();

        int laskunTulos = sov.tulos();
         
        syote.setText("");
        tulos.setText("" + laskunTulos);            

        }        
        

        @Override
        public void peru() {
        sov.palauta();
        
        int laskunTulos = sov.tulos();        
        
        syote.setText("");
        tulos.setText("" + laskunTulos);                
            
        } 
    }

    public interface Komento {
        void suorita();
        void peru();
    }  
    
}