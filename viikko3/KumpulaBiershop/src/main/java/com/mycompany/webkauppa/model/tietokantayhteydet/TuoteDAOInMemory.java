
package com.mycompany.webkauppa.model.tietokantayhteydet;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import java.util.*;

public class TuoteDAOInMemory extends TuoteDAO {
    public static TuoteDAO getInMemoryDAO(){
        return new TuoteDAOInMemory();
    }
    
    private ArrayList<Tuote> tuotteet;
    private long id;
    
    public TuoteDAOInMemory() {
        tuotteet = new ArrayList<Tuote>();
        tuotteet.add( new Tuote(1, "Fink Bräu", 1) );
        tuotteet.add( new Tuote(2, "Karjala", 2) );
        tuotteet.add( new Tuote(3, "Huvila Pale Ale", 4) );
        tuotteet.add( new Tuote(4, "Nögne IPA", 7) );
        tuotteet.add( new Tuote(5, "Weihenstephaner", 4) );
        for (Tuote tuote : tuotteet) {
            tuote.setSaldo(100);
        }
        id = 6;
    }        
    
    @Override
    public List<Tuote> findAll() {
        return tuotteet;
    }

    @Override
    public void save(Tuote tuote) { 
        if ( tuote.getId()==0 ) {
            tuote.setId(++id);
            System.out.println( "saved "+tuote.getNimi() );
        }
    }
    
}
