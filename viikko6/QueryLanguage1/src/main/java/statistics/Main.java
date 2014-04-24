package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        /*Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(1, "goals"),
                             new HasFewerThan(8, "assists"),
                             new PlaysIn("PHI"),
                             new Not(0, "assists")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }*/
        
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        Matcher m = query.oneOf(
                        query.playsIn("PHI")
                             .hasAtLeast(10, "goals")
                             .hasFewerThan(15, "assists").build(),

                        query.playsIn("EDM")
                             .hasAtLeast(50, "points").build()
                       ).build();
        /*Matcher m = new Or(new And(new PlaysIn("EDM"), new HasAtLeast(50, "points")), 
                new And(new PlaysIn("PHI"), new HasAtLeast(10, "goals"), new HasFewerThan(15, "assists")));*/
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
