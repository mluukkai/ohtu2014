/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author Eemi
 */
public interface IPankki {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
