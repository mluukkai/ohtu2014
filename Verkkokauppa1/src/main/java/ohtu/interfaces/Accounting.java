
package ohtu.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public interface Accounting {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
