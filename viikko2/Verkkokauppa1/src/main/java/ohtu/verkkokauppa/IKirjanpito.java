package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface IKirjanpito {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);

}
