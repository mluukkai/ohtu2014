package ohtu.interfaces;

import ohtu.verkkokauppa.Tuote;

/**
 *
 * @author Joel
 */
public interface Warehouse {

    Tuote haeTuote(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);

    int saldo(int id);

}
