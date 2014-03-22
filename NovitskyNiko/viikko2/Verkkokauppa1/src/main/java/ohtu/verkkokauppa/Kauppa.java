package ohtu.verkkokauppa;

public class Kauppa implements kauppaInt {

    private varastoInt varasto;
    private pankkiInt pankki;
    private viiteInt viitegeneraattori;
    private Ostoskori ostoskori;
    
    private String kaupanTili;

    public Kauppa(Varasto v, Pankki p, Viitegeneraattori vg) {
        this.varasto = v;
        this.pankki = p;
        this.viitegeneraattori = vg;
        this.kaupanTili = "33333-44455";
    }

    @Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    @Override
    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    @Override
    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    @Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
