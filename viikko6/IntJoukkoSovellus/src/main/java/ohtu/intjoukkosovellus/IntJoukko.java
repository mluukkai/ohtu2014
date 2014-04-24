
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this.TarkistaParametrit(kapasiteetti, kasvatuskoko);
        this.setUp(kapasiteetti, kasvatuskoko);
    }

    public boolean lisaa(int luku) {
        if(!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            tarkistaTaulukonKoko();
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for(int i = 0;i < this.alkioidenLkm;i ++) {
            if(this.lukujono[i] == luku) {
                poistaAlkio(i);
                this.alkioidenLkm --;
                return true;
            }
        }
        return false;
    }

    public int alkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {    
        String merkkijonoEsitys = "{";
        for (int i = 0; i <= alkioidenLkm - 2; i++) {
            merkkijonoEsitys += lukujono[i];
            merkkijonoEsitys += ", ";
        }
        merkkijonoEsitys += lukujono[alkioidenLkm - 1];
        merkkijonoEsitys += "}";
        return merkkijonoEsitys;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
    
    private void TarkistaParametrit(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatus koko ei saa olla negatiivnen");
        }
    }
    
    private void setUp(int kapasiteetti, int kasvatuskoko) {
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    private void tarkistaTaulukonKoko() {
        if (lukujono.length - alkioidenLkm == 0) {
                int[] taulukkoOld = lukujono;
                lukujono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, lukujono);
        }
    }
    
    private void poistaAlkio(int kohta) {
        for(int i = kohta;i < this.alkioidenLkm;i ++) {
            if(i + 1 < this.alkioidenLkm) {
                this.lukujono[i] = this.lukujono[i+1];
            }
        }
    }
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }
    
    // Class methods
    
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
            z.poista(bTaulu[i]);
        }
        return z;
    }
        
}