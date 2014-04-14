package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    private int val;
    private String category;

    public HasFewerThan(int val, String category) {
        this.val = val;
        this.category = category;
    }
    
    public boolean matches(Player p) {
        return new Not(new HasAtLeast(val, category)).matches(p);
    }
    
}
