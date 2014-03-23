package ohtuesimerkki;
import ohtuesimerkki.reader.Reader;
import ohtuesimerkki.reader.Statistics;
import ohtuesimerkki.reader.PlayerReader;
import ohtuesimerkki.reader.Player;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReader("http://nhlstats-2013-14.herokuapp.com/players.txt") );
          
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("");
        
        System.out.println("Top scorers");
        for (Player player : stats.topScorers(10) ) {
            System.out.println( player );
        }        
    }
}
