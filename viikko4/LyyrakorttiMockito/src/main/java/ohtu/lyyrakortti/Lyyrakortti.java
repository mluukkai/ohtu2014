package ohtu.lyyrakortti;

public class Lyyrakortti {

    private int saldo;

    public Lyyrakortti(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void lataa(int maara) {
        saldo += maara;
    }

    public void osta(int hinta) {
        saldo -= hinta;
    }
}
