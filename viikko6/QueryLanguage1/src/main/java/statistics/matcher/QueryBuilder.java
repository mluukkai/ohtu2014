/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statistics.matcher;

/**
 *
 * @author joonas
 */
public class QueryBuilder {
    
    private Matcher matcher;
    
    public QueryBuilder(){
    }
    
    public QueryBuilder(Matcher matcher){
        this.matcher = matcher;
    }
    
    public Matcher build(){
        return matcher;
    }
    
    public QueryBuilder playsIn(String team){
        if(matcher == null){
            return new QueryBuilder(new PlaysIn(team));
        }
        return new QueryBuilder(new And(matcher, new PlaysIn(team)));
    }
    
    public QueryBuilder hasAtLeast(int value, String category){
        if(matcher == null){
            return new QueryBuilder(new HasAtLeast(value, category));
        }
        return new QueryBuilder(new And(matcher, new HasAtLeast(value, category)));
    }
    
    public QueryBuilder hasFewerThan(int value, String category){
        if(matcher == null){
            return new QueryBuilder(new HasFewerThan(value, category));
        }
        return new QueryBuilder(new And(matcher, new HasFewerThan(value, category)));
    }
    
    public QueryBuilder oneOf(Matcher...matchers){
        return new QueryBuilder(new Or(matchers));
    }


    
}
