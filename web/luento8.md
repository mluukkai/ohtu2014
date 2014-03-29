##. koheesio metoditasolla

Esimerkki artikkelista [http://www.ibm.com/developerworks/java/library/j-eaed4/index.html](http://www.ibm.com/developerworks/java/library/j-eaed4/index.html)

``` java
public void populate() throws Exception  {
    Connection c = null;
    try {
        c = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(SQL_SELECT_PARTS);
        while (rs.next()) {
            Part p = new Part();
            p.setName(rs.getString("name"));
            p.setBrand(rs.getString("brand"));
            p.setRetailPrice(rs.getDouble("retail_price"));
            partList.add(p);
        }
    } finally {
        c.close();
    }
}
```

Metodissa tehdään montaa asiaa:

* luodaan yhteys tietokantaan
* tehdään tietokantakysely
* käydään kyselyn tulosrivit läpi ja luodaan jokaista tulosriviä kohti Part-olio
* suljetaan yhteys

Ikävänä seurauksena tästä on myös se, että metodi toimii monella abstraktiotasolla. Toisaalta käsitellään teknisiä tietokantatason asioita kuten tietokantayhteyden avaamista ja kyselyn tekemistä, toisaalta "bisnestason" olioita.

Metodi on helppo __refaktoroida__ pilkkomalla se pienempiin osiin joiden kutsumista alkuperäinen metodi koordinoi.

``` java
public void populate() throws Exception {
    Connection c = null;
    try {
        c = getDatabaseConnection();
        ResultSet rs = createResultSet(c);
        while (rs.next()){
            addPartToListFromResultSet(rs);
        }
    } finally {
        c.close();
    }
}
 
private ResultSet createResultSet(Connection c)throws SQLException {
    return c.createStatement().
            executeQuery(SQL_SELECT_PARTS);
}
 
private Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
    return DriverManager.getConnection(DB_URL,"webuser", "webpass");
}
 
private void addPartToListFromResultSet(ResultSet rs) throws SQLException {
    Part p = new Part();
    p.setName(rs.getString("name"));
    p.setBrand(rs.getString("brand"));
    p.setRetailPrice(rs.getDouble("retail_price"));
    partList.add(p);
}
```

Yksittäiset metodit ovat nyt kaikki samalla abstraktiotasolla toimivia ja hyvin nimettyjä.

Nyt aikaansaatu lopputulos ei ole vielä välttämättä ideaali koko ohjelman kontekstissa. [Artikkelissa](http://www.ibm.com/developerworks/java/library/j-eaed4/index.html) esimerkkiä jatketaankin eristäen tietokantaoperaatiot (joita myös muut ohjelman osat tarvitsevat) omaan luokkaansa.

## Single responsibility -periaate eli koheesio luokkatasolla

Kurssin alussa tarkastelimme yksinkertaista laskinta:

``` java
public class Laskin {
 
    private Scanner lukija;
 
    public Laskin() {
        lukija = new Scanner(System.in);
    }
 
    public void suorita(){
        while( true ) {
            System.out.print("luku 1: ");
            int luku1 = lukija.nextInt();
            if ( luku1==-9999  ) return;
 
            System.out.print("luku 2: ");
            int luku2 = lukija.nextInt();
            if ( luku2==-9999  ) return;
 
            int vastaus = laskeSumma(luku1, luku2);
            System.out.println("summa: "+ vastaus);
        }
    }
 
    private int laskeSumma(int luku1, int luku2) {
        return luku1+luku2;
    }
 
}
```

Luokka rikkoo Single responsibility -periaatteen? Miksi? Periaate sanoo, että luokalla saa olla vain yksi vastuu eli syy muuttuua. Nyt luokalla on kuitenkin useita syitä muuttua:

* luokalle halutaan toteuttaa uusia laskutoimituksia
* kommunikointi käyttäjän kanssa halutaan hoitaa jotenkin muuten kuin konsolin välityksellä

Eriyttämällä käyttäjän kanssa kommunikointi omaan luokkaan ja eristämällä se rajapinnan taakse (eli kapseloimalla kommunikoinnin toteutustapa) saadaan luokan Laskin vastuita vähennettyä:

``` java
public interface IO {
    int nextInt();
    void print(String m);
}
 
public class Laskin {
    private IO io;
 
    public Laskin(IO io) {
        this.io = io;
    }
 
    public void suorita(){
        while( true ) {
            io.print("luku 1: ");
            int luku1 = io.nextInt();
            if ( luku1==-9999  ) return;
 
            io.print("luku 2: ");
            int luku2 = io.nextInt();
            if ( luku2==-9999 ) return;
 
            int vastaus = laskeSumma(luku1, luku2);
            io.print("summa: "+vastaus+"\n");
        }
    }
 
    private int laskeSumma(int luku1, int luku2) {
        return luku1+luku2;
    }
}
```

Nyt kommunikointitavan muutos ei edellytä luokkaan mitään muutoksia edellyttäen että uusikin kommunikoinitapa toteuttaa rajapinnan jonka kautta Laskin hoitaa kommunikoinnin.

Vaikka luokka Laskin siis toteuttaakin edelleen käyttäjänsä näkökulmasta samat asiat kuin aiemmin, ei se hoida kaikkea itse vaan delegoi osan vastuistaan muualle.

Luokka ei ole vielä kaikin osin laajennettavuuden kannalta optimaalinen. Palaamme asiaan hetken kuluttua.

## Favour composition over inheritance eli milloin ei kannata periä

Meillä on käytössämme luokka joka mallintaa pankkitiliä:

``` java
public class Tili {
    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private double korkoProsentti;
 
    public Tili(String tiliNumero, String omistaja, double korkoProsentti) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korkoProsentti = korkoProsentti;
    }
 
    public boolean siirraRahaaTililta(Tili tilille, double summa){
        if ( this.saldo<summa ) return false;
 
        this.saldo -= summa;
        tilille.saldo += summa;
 
        return true;
    }
 
    public void maksaKorko(){
        saldo += saldo*korkoProsentti*100;
    }
}
```

Huomaamme, että tulee tarve toisentyyppiselle tilille joko 1, 3, 6 tai 12 kuukaiden Euribor-korkoon perustuvalle tilille. päätämme tehdä uuden luokan EuriborTili perimällä luokan Tili ja ylikirjoittamalla metodin maksaKorko siten että Euribor-koron senhetkinen arvo haetaan verkosta:

``` java
public class EuriborTili extends Tili {
    private int kuukauden;
 
    public EuriborTili(String tiliNumero, String omistaja, int kuukauden) {
        super(tiliNumero, omistaja, 0);
        this.kuukauden = kuukauden;
    }
 
    @Override
    public void maksaKorko() {
        saldo += saldo*korko()*100;
    }
 
    private double korko() {
        Scanner lukija = null;
        double korko = 0;
        try {
            lukija = new Scanner(new URL("http://www.suomenpankki.fi/fi/_layouts/BOF/RSS.ashx/tilastot/Korot/fi").openStream());
        } catch (Exception e) {
        }
 
        String sisalto = lukija.nextLine();
 
        for (String rivi : sisalto.split("Reutersin ilmoittama")) {
            String osa = rivi.split("%")[0];
 
            try {
                if (osa.contains(kuukauden + " kuukauden")) {
                    korko = Double.parseDouble(osa.substring(osa.length() - 6, osa.length()).replace(',', '.'));
                    break;
                }
            } catch (Exception e) {
            }
        }
 
        return korko;
    }
}
```

Huomaamme, että EuriborTili rikkoo Single Responsibility -periaatetta, sillä luokka sisältää normaalin tiliin liittyvän toiminnan lisäksi koodia joka hakee tavaraa internetistä. Vastuut kannattaa selkeyttää ja korkoprosentin haku eriyttää omaan rajapinnan takana olevaan luokkaan:

``` java
public interface EuriborLukija {
    double korko();
}
 
public class EuriborTili extends Tili {
    private EuriborLukija euribor;
 
    public EuriborTili(String tiliNumero, String omistaja, int kuukauden) {
        super(tiliNumero, omistaja, 0);
        euribor = new EuriborlukijaImpl(kuukauden);
    }
 
    @Override
    public void maksaKorko() {
        saldo += saldo*euribor.korko()*100;
    }
 
}
 
public class EuriborlukijaImpl implements EuriborLukija {
    private int kuukauden;
 
    public EuriborlukijaImpl(int kuukauden) {
        this.kuukauden = kuukauden;
    }
 
    @Override
    public double korko() {
        Scanner lukija = null;
        double korko = 0;
        try {
            lukija = new Scanner(new URL("http://www.suomenpankki.fi/fi/_layouts/BOF/RSS.ashx/tilastot/Korot/fi").openStream());
        } catch (Exception e) {
        }
 
        String sisalto = lukija.nextLine();
 
        for (String rivi : sisalto.split("Reutersin ilmoittama")) {
            String osa = rivi.split("%")[0];
 
            try {
                if (osa.contains(kuukauden + " kuukauden")) {
                    korko = Double.parseDouble(osa.substring(osa.length() - 6, osa.length()).replace(',', '.'));
                    break;
                }
            } catch (Exception e) {
            }
        }
 
        return korko;
    }
}
```

EuriborTili-luokka alkaa olla nyt melko siisti, EuriborLukijassa olisi paljon parantemisen varaa, mm. sen ainoan metodin koheesio on huono, metodi tekee aivan liian montaa asiaa. Palaamme siihen kuitenkin myöhemmin.

Seuraavaksi huomaamme että on tarvetta Määräaikaistilille joka on muuten samanlainen kuin Tili mutta määräaikaistililtä ei voi siirtää rahaa muualle ennen kuin se tehdään mahdolliseksi tietyn ajan kuluttua. Eli ei ongelmaa, perimme jälleen luokan Tili:

``` java
public class MääräaikaisTili extends Tili {
    private boolean nostokielto;
 
    public MääräaikaisTili(String tiliNumero, String omistaja, double korkoProsentti) {
        super(tiliNumero, omistaja, korkoProsentti);
        nostokielto = true;
    }
 
    public void salliNosto(){
        nostokielto = false;
    }
 
    @Override
    public boolean siirraRahaaTililta(Tili tilille, double summa) {
        if ( nostokielto )
            return false;
 
        return super.siirraRahaaTililta(tilille, summa);
    }
 
}
```

Luokka syntyi tuskattomasti.

Seuraavaksi tulee idea Euribor-korkoa käyttävistä määräaikaistileistä. Miten nyt kannattaisi tehdä? Osa toiminnallisuudesta on luokassa Määräaikaistili ja osa luokassa Euribor-tili...

Ehkä koronmaksun hoitaminen perinnän avulla ei ollutkaan paras ratkaisu, ja kannattaisi noudattaa "favor composition over inheritance"-periaatetta. Eli erotetaan koronmaksu omaksi luokakseen, tai rajapinnan toteuttaviksi luokiksi:

``` java
public interface Korko {
    double korko();
}
 
public class Tasakorko implements Korko {
    private double korko;
 
    public Tasakorko(double korko) {
        this.korko = korko;
    }
 
    public double korko() {
        return korko;
    }
}
 
public class EuriborKorko implements Korko {
    EuriborLukija lukija;
 
    public EuriborKorko(int kuukausi) {
        lukija = new EuriborlukijaImpl(kuukausi);
    }
 
    public double korko() {
        return korko();
    }
}
```

Nyt tarve erilliselle EuriborTili-luokalle katoaa, ja pelkkä Tili muutettuna riittää:

``` java
public class Tili {
    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private Korko korko;
 
    public Tili(String tiliNumero, String omistaja, Korko korko) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korko = korko;
    }
 
    public boolean siirraRahaaTililta(Tili tilille, double summa){
        if ( this.saldo<summa ) return false;
 
        this.saldo -= summa;
        tilille.saldo += summa;
 
        return true;
    }
 
    public void maksaKorko(){
        saldo += saldo*korko.korko()*100;
    }
}
```

Erilaisia tilejä luodaan nyt seuraavasti:

``` java
Tili normaali = new Tili("1234-1234", "Kasper Hirvikoski", new Tasakorko(4));
Tili euribor12 = new Tili("4422-3355", "Tero Huomo", new EuriborKorko(12));
```

Muutetaan luokkaa vielä siten, että tilejä saadaan luotua ilman konstruktoria:

``` java
public class Tili {
 
    private String tiliNumero;
    private String omistaja;
    private double saldo;
    private Korko korko;
 
    public static Tili luoEuriborTili(String tiliNumero, String omistaja, int kuukausia) {
        return new Tili(tiliNumero, omistaja, new EuriborKorko(kuukausia));
    }
 
    public static Tili luoMääräaikaisTili(String tiliNumero, String omistaja, double korko) {
        return new MääräaikaisTili(tiliNumero, omistaja, new Tasakorko(korko));
    }
 
    public static Tili luoKäyttöTili(String tiliNumero, String omistaja, double korko) {
        return new Tili(tiliNumero, omistaja, new Tasakorko(korko));
    }
 
    protected Tili(String tiliNumero, String omistaja, Korko korko) {
        this.tiliNumero = tiliNumero;
        this.omistaja = omistaja;
        this.korko = korko;
    }
 
    // ...
 
    public void vaihdaKorkoa(Korko korko) {
        this.korko = korko;
    }
}
```

Lisäsimme luokalle 3 staattista apumetodia helpottamaan tilien luomista. Tilejä voidaan nyt luoda seuraavasti:

``` java
Tili määräaikais = Tili.luoMääräaikaisTili("1234-1234", "Kasper Hirvikoski", 2.5);
Tili euribor12 = Tili.luoEuriborTili("4422-3355", "Tero Huomo", 12 );
Tili fyrkka = Tili.luoEuriborTili("7895-4571", "Esko Ukkonen", 10.75 );
```

Käyttämämme periaate olioiden luomiseen staattisten metodien avulla on hyvin tunnettu suunnittelumalli *staattinen tehdas, engl. static factory*.

Huomaamme, että tehdasmetodien avulla voimme kapseloida luokan todellisen tyypin. Kasperin tilihän on määräaikaistili, se kuitenkin pyydetään Tili-luokassa sijaitsevalta factoryltä, olion oikea tyyppi on piilotettu tarkoituksella käyttäjältä. Määräaikaistilin käyttäjällä ei siis ole enää konkreettista riippuvuutta luokkaan Määräaikaistili.

Teimme myös metodin jonka avulla tilin korkoa voi muuttaa. Kasperin tasakorkoinen määräaikaistili on helppo muuttaa lennossa kolmen kuukauden Euribor-tiliksi:

``` java
määräaikais.vaihdaKorkoa(new EuriborKorko(3));
```

Eli luopumalla perinnästä selkeytyy oliorakenne huomattavasti ja saavutetaan ajonaikaista joustavuuttaa (koronlaskutapa) joka perintää käyttämällä ei onnistu.

Tekniikka jolla koronmaksu hoidetaan on myöskin suunnittelumalli nimeltään *strategia eli englanniksi strategy*.

## Laskin ilman iffejä

Olemme laajentaneet Laskin-luokkaa osaamaan myös muita laskuoperaatioita:

``` java
public class Laskin {
 
    private IO io;
 
    public Laskin(IO io) {
        this.io = io;
    }
 
    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            if (komento.equals("lopetus")) {
                return;
            }
 
            io.print("luku 1: ");
            int luku1 = io.nextInt();
 
            io.print("luku 2: ");
            int luku2 = io.nextInt();
 
            int vastaus = 0;
 
            if ( komento.equals("summa") ){
                vastaus = laskeSumma(luku1, luku2);
            } else if ( komento.equals("tulo") ){
                vastaus = laskeTulo(luku1, luku2);
            } else if ( komento.equals("erotus") ){
                vastaus = laskeErotus(luku1, luku2);
            }
 
            io.print("summa: " + vastaus + "\n");
        }
    }
 
    private int laskeSumma(int luku1, int luku2) {
        return luku1 + luku2;
    }
 
    private int laskeTulo(int luku1, int luku2) {
        return luku1 * luku2;
    }
 
    private int laskeErotus(int luku1, int luku2) {
        return luku1-luku2;
    }
}
```

Ratkaisu ei ole kaikin puolin tyydyttävä. Entä jos haluamme muitakin operaatioita kuin summan, tulon ja erotuksen? if-hässäkkä tulee kasvamaan.

Päätämme siirtyä strategian käyttöön, eli hoidetaan laskuoperaatio omassa luokassaan. Rajapinnan sijasta käytämme tällä kertaa abstraktia luokkaa:

``` java
public abstract class Operaatio {
 
    protected int luku1;
    protected int luku2;
 
    public Operaatio(int luku1, int luku2) {
        this.luku1 = luku1;
        this.luku2 = luku2;
    }
 
    public static Operaatio luo(String operaatio, int luku1, int luku2) {
        if (operaatio.equals("summa")) {
            return new Summa(luku1, luku2);
        } else if (operaatio.equals("tulo")) {
            return new Tulo(luku1, luku2);
        }
        return new Erotus(luku1, luku2);
    }
 
    public abstract int laske();
}
 
public class Summa extends Operaatio {
 
    public Summa(int luku1, int luku2) {
        super(luku1, luku2);
    }
 
    @Override
    public int laske() {
        return luku1 + luku2;
    }
}
 
public class Tulo extends Operaatio {
 
    public Tulo(int luku1, int luku2) {
        super(luku1, luku2);
    }
 
    @Override
    public int laske() {
        return luku1 * luku2;
    }
}
 
public class Erotus extends Operaatio {
 
    public Erotus(int luku1, int luku2) {
        super(luku1, luku2);
    }
 
    @Override
    public int laske() {
        return luku1 - luku2;
    }
}
```

Laskin-luokka yksinkertaistuu huomattavasti:

``` java
public class Laskin {
 
    private IO io;
 
    public Laskin(IO io) {
        this.io = io;
    }
 
    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            if (komento.equals("lopetus")) {
                return;
            }
 
            io.print("luku 1: ");
            int luku1 = io.nextInt();
 
            io.print("luku 2: ");
            int luku2 = io.nextInt();
 
            Operaatio operaatio = Operaatio.luo(komento, luku1, luku2);
 
            io.print("summa: " + operaatio.laske() + "\n");
        }
    }
}
```

Hienona puolena laskimessa on nyt se, että voimme lisätä operaatioita ja Laskinta ei tarvitse muuttaa millään tavalla!

Entä jos haluamme laskimelle muunkinlaisia kuin 2 parametria ottavia operaatioita, esim. neliöjuuren?

Jatkamme muokkaamista seuraavassa luvussa

## laskin ja komento-olio

Muutamme Operaatio-luokan olemusta, päädymme jo oikeastaan Strategy-suunnittelumallin lähisukulaisen Command-suunnittelumallin puolelle ja annammekin sille nimen Komento ja teemmie siitä rajapinnan sillä siirrämme erillisten komento-olioiden luomisen Komentotehdas-luokalle:

``` java
public interface Komento {
    void suorita();
}
```

Komento-rajapinta on siis äärimmäisen yksinkertainen. Komennon voi ainoastaan suorittaa eikä se edes palauta mitään!

Komento-olioita luova komentotehdas on seuraavassa:

``` java
public class Komentotehdas {
 
    private IO io;
 
    public Komentotehdas(IO io) {
        this.io = io;
    }
 
    public Komento hae(String operaatio) {
        if (operaatio.equals("summa")) {
            return new Summa(io);
        } else if (operaatio.equals("tulo")) {
            return new Tulo(io);
        } else if (operaatio.equals("nelio")) {
            return new Nelio(io);
        } else if (operaatio.equals("lopeta")) {
            return new Lopeta();
        }
        return new Tuntematon(io);
    }
}
```
Komentotehdas siis palauttaa hae-metodin merkkijonoparametria vastaavan komennon. Koska vastuu käyttäjän kanssa kommunikoinnista on siirretty Komento-olioille, annetaan niille IO-olio konstruktorissa.

if-hässäkkä näyttää hieman ikävältä. Mutta hetkinen! Voisimme tallentaa erilliset komennon HashMap:iin:

``` java
public class Komentotehdas {
    private HashMap<String, Komento> komennot;
 
    public Komentotehdas(IO io) {
        komennot = new HashMap<String, Komento>();
        komennot.put("summa", new Summa(io));
        komennot.put("tulo", new Tulo(io));
        komennot.put("nelio", new Nelio(io));
        komennot.put("tuntematon", new Tuntematon(io));
    }
 
    public Komento hae(String operaatio) {
        Komento komento = komennot.get(operaatio);
        if (komento == null) {
            komento = komennot.get("tuntematon");
        }
        return komento;
    }
}
```

Pääsimme kokonaan eroon if-ketjusta, loistavaa!

Yksittäiset komennot ovat hyvin yksinkertaisia:

``` java
public class Nelio implements Komento {
    private IO io;
 
    public Nelio(IO io) {
        this.io = io;
    }
 
    @Override
    public void suorita() {
        io.print("luku 1: ");
        int luku = io.nextInt();
 
        io.print("vastaus: "+luku*luku);
    }
}
 
public class Tuntematon implements Komento {
    private IO io;
 
    public Tuntematon(IO io) {
        this.io = io;
    }
 
    @Override
    public void suorita() {
        io.print("sallitut komennot: summa, tulo, nelio, lopeta");
    }
}
 
public class Lopeta implements Komento {
    private IO io;
 
    public Lopeta(IO io) {
        this.io = io;
    }
 
    @Override
    public void suorita() {
        io.print("kiitos ja näkemiin");
        System.exit(0);
    }
 
}
```

Koska kaksi parametria käyttäjältä kysyvillä komennoilla on paljon yhteistä, luodaan niitä varten yliluokka:

``` java
public abstract class KaksiparametrinenLaskuoperaatio implements Komento {
 
    protected IO io;
    protected int luku1;
    protected int luku2;
 
    public KaksiparametrinenLaskuoperaatio(IO io) {
        this.io = io;
    }
 
    @Override
    public void suorita() {
        io.print("luku 1: ");
        int luku1 = io.nextInt();
 
        io.print("luku 2: ");
        int luku2 = io.nextInt();
 
        io.print("vastaus: "+laske());
    }
 
    protected abstract int laske();
}
 
public class Summa extends KaksiparametrinenLaskuoperaatio {
 
    public Summa(IO io) {
        super(io);
    }
 
    @Override
    protected int laske() {
        return luku1+luku2;
    }
}
 
public class Tulo extends KaksiparametrinenLaskuoperaatio {
 
    public Tulo(IO io) {
        super(io);
    }
 
    @Override
    public int laske() {
        return luku1*luku2;
    }
}
```

Ja lopulta luokka Laskin, jossa ei ole enää juuri mitään jäljellä:

``` java
public class Laskin {
 
    private IO io;
    private Komentotehdas komennot;
 
    public Laskin(IO io) {
        this.io = io;
        komennot = new Komentotehdas(io);
    }
 
    public void suorita() {
        while (true) {
            io.print("komento: ");
            String komento = io.nextLine();
            komennot.hae(komento).suorita();
        }
    }
}
```

Ohjelmasta on näinollen saatu laajennettavuudeltaan varsin joustava. Uusia operaatioita on helppo lisätä ja lisäys ei aiheuta muutoksia moneen kohtaan koodia. Laskin-luokallahan ei ole riippuvuuksia muualle kuin rajapintoihin IO ja Komento ja luokkaan Komentotehdas.

Hintana joustavuudelle on luokkien määrän kasvu. Nopealla vilkaisulla saattaakin olla vaikea havaita miten ohjelma toimii, varsinkaan jos ei ole vastaavaan tyyliin tottunut, mukaan on nimittäin piilotettu factory- ja command-suunnittelumallien lisäksi suunnittelumalli __template method__ (kaksiparametrisen komennon toteutukseen). Luokka- ja sekvenssikaavion piirtäminen lienee paikallaan.

Yksinkertaisessa ohjelmassa ei tietenkään ole järkeä tehdä ohjelman rakenteesta näin joustavaa.