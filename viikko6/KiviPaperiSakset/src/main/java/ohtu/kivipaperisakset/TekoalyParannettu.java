package ohtu.kivipaperisakset;

public class TekoalyParannettu {

    private String[] muisti;
    private int vapaaMuistiIndeksi;
    private int k;
    private int s;
    private int p;

    public TekoalyParannettu(int muistinKoko) {
        muisti = new String[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }
    
    public void nollaaMuuttujat(){
        k = 0;
        s = 0;
        p = 0;
    }

    public void asetaSiirto(String siirto) {
        unohdaViimeinen();
        muisti[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }

    private void unohdaViimeinen() {
        if (vapaaMuistiIndeksi == muisti.length) {
            for (int i = 1; i < muisti.length; i++) {
                muisti[i - 1] = muisti[i];
            }
            vapaaMuistiIndeksi--;
        }
    }

    public void kasvata(String siirto) {
        switch (siirto) {
            case "k": k++; break;
            case "p": p++; break;
            default: s++; break;
        }
    }

    public String annaSiirto() {
        if (onkoMuistiaTarpeeksi()) return "k";
        String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];
        nollaaMuuttujat();
        haeSiirrot(viimeisinSiirto);
        return valinta();
    }

    private boolean onkoMuistiaTarpeeksi() {
        return vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1;
    }

    private void haeSiirrot(String viimeisinSiirto) {
        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto.equals(muisti[i])) {
                String seuraava = muisti[i + 1];
                kasvata(seuraava);  
            }
        }
    }

    private String valinta() {
        if (k > p && k > s) {
            return "p";
        } else if (p > k && p > s) {
            return "s";
        } else {
            return "k";
        }
    }
}
