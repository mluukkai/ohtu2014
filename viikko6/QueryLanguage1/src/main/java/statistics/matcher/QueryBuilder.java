package statistics.matcher;

import statistics.Player;

public class QueryBuilder { 
    private Matcher matchers;
    
    public QueryBuilder() {
    }
    
    public Matcher build(){
        return matchers;
    }
    
    public QueryBuilder playsIn(String team){
        this.matchers = new PlaysIn(matchers, team);
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category){
        this.matchers = new HasAtLeast(matchers, value, category);
        return this;
    }    
    
    public QueryBuilder hasFewerThan(int value, String category){
        this.matchers = new HasFewerThan(matchers, value, category);
        return this;
    }     
  
}

