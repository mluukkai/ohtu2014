package com.mycompany.webkauppa.sovelluslogiikka;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Tuote implements Serializable {

    private long id;
    private String nimi;
    private int hinta;
    private int saldo;

    public Tuote() {
    }

    public Tuote(String nimi, int hinta) {
        this.nimi = nimi;
        this.hinta = hinta;
    }

    public Tuote(long id, String nimi, int hinta) {
        this.id = id;
        this.nimi = nimi;
        this.hinta = hinta;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getHinta() {
        return hinta;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi + " " + hinta + " euroa";
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
        
}
