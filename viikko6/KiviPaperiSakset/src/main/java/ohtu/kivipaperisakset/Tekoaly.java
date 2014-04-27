package ohtu.kivipaperisakset;

public class Tekoaly {

    int siirto;

    public Tekoaly() {
        siirto = 0;
    }

    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;
        switch(siirto){
            case 0: return "k";
            case 1: return "p";
            default: return "s";
        }
    }
}
