/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author nargiza
 */
public interface Ostoskori_inteface {

    int hinta();

    void lisaa(Tuote t);

    void poista(Tuote t);
    
}
