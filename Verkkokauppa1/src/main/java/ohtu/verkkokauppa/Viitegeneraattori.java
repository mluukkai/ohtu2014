package ohtu.verkkokauppa;

import ohtu.interfaces.Generator;

public class Viitegeneraattori implements Generator {

    private int seuraava;

    public Viitegeneraattori() {
        seuraava = 1;
    }

    @Override
    public int uusi() {
        return seuraava++;
    }
}
