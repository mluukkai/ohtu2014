
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5; // luotava uusi taulukko on 
                                                // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        lukuJono=alustaJoukko(lukuJono, kapasiteetti);
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    private int[] alustaJoukko(int[] Jono, int kapasiteetti){
        Jono = new int[kapasiteetti];
        for (int i = 0; i < Jono.length; i++) {
            Jono[i] = 0;
        }    
        return Jono;    
    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            lukuJono[0] = luku;
            alkioidenLkm++;
            return true;
        }      
        if (!kuuluu(luku)) {
            lukuJono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukuJono.length == 0) {
                lukuJono=kasvata(lukuJono);
            }
            return true;
        }
        return false;
    }
    
    private int[] kasvata(int[] jono){
        int[] taulukkoOld = new int[jono.length];
        taulukkoOld = lukuJono;
        kopioiTaulukko(lukuJono, taulukkoOld);
        lukuJono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukuJono); 
        return lukuJono;        
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJono[i]) {
                return true;
            } 
        }
        return false;                      
    }

    public boolean poista(int luku) {
        boolean loytyi = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJono[i]) {
                loytyi=true;
            } else {
                if (loytyi == true){            
                lukuJono[i-1] = lukuJono[i];                  
                }
            }
        }    
        if (loytyi==true){
            lukuJono[alkioidenLkm]=0;
            alkioidenLkm--;
            return true;            
        }
        return false;        
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukuJono[i];
                if (i<alkioidenLkm - 1) tuotos += ", ";
            }
            tuotos += lukuJono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus ( IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(i);
        }
        return erotusJoukko;
    }        
}