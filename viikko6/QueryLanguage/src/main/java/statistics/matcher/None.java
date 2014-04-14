package statistics.matcher;

import statistics.Player;

public class None implements Matcher {

    public boolean matches(Player p) {
        return false;
    }

}
