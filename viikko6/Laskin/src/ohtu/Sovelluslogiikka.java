package ohtu;

public class Sovelluslogiikka {
 
    private int tulos;
    private int edtulos;
 
    public void plus(int luku) {
        edtulos=tulos;
        tulos += luku;
    }
     
    public void miinus(int luku) {
        edtulos=tulos;
        tulos -= luku;
    }
 
    public void nollaa() {
        edtulos=tulos;        
        tulos = 0;
    }
    
    public void palauta() {
        tulos=edtulos;
    }
 
    public int tulos() {
        return tulos;
    }
}