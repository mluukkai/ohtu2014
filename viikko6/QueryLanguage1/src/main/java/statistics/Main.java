package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
/*          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
            System.out.println("Has Fewer Than");    
            
        Matcher m1 = new And( new HasFewerThan(10, "goals"),
                             new HasFewerThan(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player1 : stats.matches(m1)) {
            System.out.println( player1 ); 
        } 
            System.out.println("OR");    
            
        Matcher m2 = new Or( new HasFewerThan(2, "goals"),
                             new HasFewerThan(2, "assists")
        );
        
        for (Player player2 : stats.matches(m2)) {
            System.out.println( player2 );
        }        
 
            System.out.println("NOT");    
            
        Matcher m3 = new Not( new HasAtLeast(2, "goals")
        );
        
        for (Player player3 : stats.matches(m3)) {
            System.out.println( player3 );
        }
*/ 
                    System.out.println("Rakentaja"); 
        
    QueryBuilder query = new QueryBuilder();    
    
        Matcher m4 = query.playsIn("NYR") //.build();
                          .hasAtLeast(10, "goals").build();
         //                 .hasFewerThan(3, "assists").build();

    for (Player player4 : stats.matches(m4)) {
        System.out.println( player4 );
    }
        
    }
}
