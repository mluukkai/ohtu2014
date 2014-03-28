package ohtu.verkkokauppa.interfaces;

/**
 *
 * @author Joel
 */
public interface Bank {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);

}
