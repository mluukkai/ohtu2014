package ohtu;

import ohtu.laskin.KonsoliIO;

public class Main {
    public static void main(String[] args) {
        Laskin laskin = new Laskin( new KonsoliIO() );
        laskin.suorita();
//        new ohtu.laskin.Laskin(new KonsoliIO()).suorita();
    }
}
