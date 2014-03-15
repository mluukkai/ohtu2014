
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;

public class TestReader implements Reader{

    @Override
    public List getPlayers() {
        List l = new ArrayList<Player>();
        l.add(new Player("Heimo Vesa", "Team Fingerpori", 1, 2));
        l.add(new Player("Rivo Riitta", "Team Fingerpori", 2, 1));
        l.add(new Player("Aku Ankka", "Kvaak", 3, 3));
        return l;
    }
    
}