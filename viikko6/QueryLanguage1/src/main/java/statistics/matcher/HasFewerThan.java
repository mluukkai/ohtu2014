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
public class HasFewerThan implements Matcher {
    private int value;
    private String fieldName;
    
    public HasFewerThan(int value, String category) {
        this.value = value;
        this.fieldName = "get" + category.substring(0, 1).toUpperCase() + category.substring(1);
    }
    
    @Override
    public boolean matches(Player p) {
        try {
            int score = (Integer)p.getClass().getMethod(this.fieldName, new Class<?>[0]).invoke(p, new Object[0]);
            if(this.value > score) {
                return true;
            }
            return false;
        } 
        catch(Exception e) {
            System.out.println(e);
            throw new IllegalStateException("Player does not have field " + this.fieldName);
        }
    }
    
}
