/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author Eemi
 */
public interface IVarasto {

    Tuote haeTuote(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);

    int saldo(int id);
    
}
