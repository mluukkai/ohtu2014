package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

    private Matcher matcher;

    public Not(Matcher matchers) {
        this.matcher = matchers;
    }

    public boolean matches(Player p) {
        return !matcher.matches(p);
    }
}
