/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

/**
 *
 * @author ilkka
 */
public class HasFewerThan implements Matcher {
    
    
    private int value;
    private String fieldName;
    private Matcher matcher;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }
    
    public HasFewerThan(Matcher matcher, int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
        this.matcher=matcher;
    }

    @Override
    public boolean matches(Player p) {
        try {                                    
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer)method.invoke(p);
            return playersValue<value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }       
        
    }  
}