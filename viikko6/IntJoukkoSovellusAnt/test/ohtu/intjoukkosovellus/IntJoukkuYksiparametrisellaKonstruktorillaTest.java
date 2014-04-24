package ohtu.intjoukkosovellus;

import ohtu.intjoukkosovellus.IntJoukkoTest;
import ohtu.intjoukkosovellus.IntJoukko;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(3);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    // perii kaikki testit luokasta IntJoukkoTest
}
