/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author Eemi
 */
public interface IKauppa {

    void aloitaAsiointi();

    void lisaaKoriin(int id);

    void poistaKorista(int id);

    boolean tilimaksu(String nimi, String tiliNumero);
    
}
