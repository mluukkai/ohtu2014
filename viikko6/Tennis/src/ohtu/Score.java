/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author henrikorpela
 */
public enum Score {
    LOVE("Love"), FIFTEEN("Fifteen"), THIRTY("Thirty"), FORTY("Forty"), ALL("All"), DEUCE("Deuce"),
    WIN_FOR("Win for"), ADVANTAGE("Advantage"), SEPARATOR("-");
    
    private String text;
    private Score(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
