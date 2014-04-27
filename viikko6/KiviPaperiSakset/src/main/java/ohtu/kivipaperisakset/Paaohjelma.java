package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pelialusta alusta = new Pelialusta(scanner);
        alusta.suorita();
    }
}
