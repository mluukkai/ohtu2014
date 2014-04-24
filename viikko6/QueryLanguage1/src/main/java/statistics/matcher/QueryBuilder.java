/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayList;

/**
 *
 * @author henrikorpela
 */
public class QueryBuilder {
    private ArrayList<Matcher> matchers;
    
    public QueryBuilder() {
        this.matchers = new ArrayList<Matcher>();
    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder not(int value, String category) {
        this.matchers.add(new Not(value, category));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher...matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }
    
    public Matcher build() {
        Matcher and =  new And(this.matchers.toArray(new Matcher[this.matchers.size()]));
        this.matchers.clear();
        return and;
    }
}
