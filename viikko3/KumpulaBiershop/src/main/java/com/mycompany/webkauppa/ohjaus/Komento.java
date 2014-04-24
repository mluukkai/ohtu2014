/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;

/**
 *
 * @author henrikorpela
 */
public interface Komento {
    public boolean suorita();
    public void setPankki(PankkiFasaadi pankki);
}
