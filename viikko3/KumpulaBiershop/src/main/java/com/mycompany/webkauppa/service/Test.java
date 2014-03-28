package com.mycompany.webkauppa.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Gson gson = new Gson();

        Ostokset kset = new Ostokset();
        kset.ostokst = new ArrayList();
        kset.ostokst.add(new Ostos(1, 2));
        kset.ostokst.add(new Ostos(7, 3));
        
        System.out.println( gson.toJson(kset) );
        
    }
}


class Ostos {

    public Ostos(long id, int maara) {
        this.id = id;
        this.maara = maara;
    }

    long id;
    int maara;
}

class Ostokset {

    List<Ostos> ostokst;
}
