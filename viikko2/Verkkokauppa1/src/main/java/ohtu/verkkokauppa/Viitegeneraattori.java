package ohtu.verkkokauppa;

public class Viitegeneraattori implements Viitegeneraattori_interface {

    
    public int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
