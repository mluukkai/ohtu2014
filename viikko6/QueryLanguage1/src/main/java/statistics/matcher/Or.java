/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author henrikorpela
 */
public class Or implements Matcher {
    private Matcher matchers[];
    
    public Or(Matcher...matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public boolean matches(Player p) {
        for(int i = 0;i < this.matchers.length;i ++) {
            if(this.matchers[i].matches(p)) {
                return true;
            }
        }
        return false;
    }
}
