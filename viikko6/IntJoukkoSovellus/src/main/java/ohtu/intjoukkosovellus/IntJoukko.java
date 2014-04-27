
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti tai kasvatuskoko ei saa olla negatiivinen");
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) { ljono[i] = 0;}
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti tai kasvatuskoko ei saa olla negatiivinen");//mäkin heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) { ljono[i] = 0;}
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if(!kuuluu(luku) && alkioidenLkm < ljono.length){
            lisaaOnnistuu(luku);
            return true;
        }
        return false;
    }

    private void lisaaOnnistuu(int luku) {
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm % ljono.length == 0) {
            alustaUusiTaulukko();
        }
    }

    private void alustaUusiTaulukko() {
        int[] taulukkoOld = ljono;
        kopioiTaulukko(ljono, taulukkoOld);
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, ljono);
    }

    public boolean kuuluu(int luku) {
        boolean kuuluu = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kuuluu = true;
            }
        }
        return kuuluu;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = ljono[alkioidenLkm-1];
                alkioidenLkm--;
                return true;
            }
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
    
    public String getArray() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i] + ", ";
        }
        tuotos += ljono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }  else {
            return getArray();
        }
               
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }
        
}