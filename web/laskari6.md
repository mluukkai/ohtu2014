# Laskari 6

## Huom: ohjausta tehtävien tekoon to klo 12-14 ja pe klo 14-16 salissa BK107

### Tehtävien palautuksen deadline vasta **su 4.5. klo 23.59**

## palautetaan GitHubin kautta

* palautusta varten tarvitaan yksityinen repositorio, jolla collaboratorina käyttäjä mluukkai
  * kannattaa käyttää samaa repoa kuin edellisten viikkojen tehtävissä
* palautusrepositorion nimi ilmoitetaan tehtävien lopussa olevalla palautuslomakkeella


## 1. Laskin ja komento-oliot

Repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta __viikko6/Laskin__ löytyy hieman modifioitu versio Ohjelmoinnin jatkokurssin viikon 5 [tehtävästä](https://www.cs.helsinki.fi/group/java/s2013-ohpe/ohja/viikko11/#147laskin).

Sovellusta on laajennettu lisäämällä siihen painike _undo_-toiminnallisuutta varten, undoa ei kuitenkaan ole vielä toteutettu.

Sovelluksen varsinainen toimintalogiikka on luokassa <code>Tapahtumankuuntelija</code>. Koodissa on tällä hetkellä hieman ikävä if-hässäkkä:

``` java
    @Override
    public void actionPerformed(ActionEvent ae) {
        int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
 
        if (ae.getSource() == plus) {
            sovellus.plus(arvo);
        } else if (ae.getSource() == miinus) {
            sovellus.miinus(arvo);
        } else if (ae.getSource() == nollaa) {
            sovellus.nollaa();
        } else {
            System.out.println("undo pressed");
        }
        
        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if ( laskunTulos==0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }
```

Refaktoroi koodi iffittömäksi luennolla 8 esiteltyä suunnittelumallia [komento-olio](https://github.com/mluukkai/ohtu2014/blob/master/web/luento8.md#laskin-ja-komento-olio) käyttäen.

Tässä tehtävässä ei tarvitse vielä toteuttaa undo-komennon toiminnallisuutta!

Luokka <code>Tapahtumankuuntelija</code> voi näyttää refaktoroituna esim. seuraavalta:

``` java
public class Tapahtumankuuntelija implements ActionListener {
    private JButton nollaa;
    private JButton undo;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
 
        Komento komento = komennot.get(ae.getSource());
        if  (komento!=null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }
        
        nollaa.setEnabled(sovellus.tulos()!=0);
        undo.setEnabled(edellinen!=null);
    }
 
}
```

Komennoilla on nyt siis kaksi julkista metodia <code>void suorita()</code> ja <code>void peru()</code>.



## 2. Undo

Toteuta nyt laskimeen myös undo-toiminnallisuus. Periaatteena on siis tallettaa jokaiseen komentoon sen verran dataa, että kutsuttaessa metodia <code>peru</code> komento osaa palauttaa tilanteen joka oli voimassa (eli käytännössä laskimen arvon) ennen komennon suoritusta.

Riittää että ohjelma muistaa edelliseksi suoritetun komennon, eli undo-toimintoa ei tarvitse osata suorittaa kahta tai useampaa kertaa peräkkäin. Tosin komento-olio-suunnittelumallin avulla olisi melko helppo toteuttaa myös useamman undo- tai redo-toiminnallisuuden hallitseva sovellus.


## 3. IntJoukon testaus ja siistiminen

*HUOM* tässä ja osassa muissakin viikon tehtävissä oli aluksi vahingossa puutteellinen __pom.xml__-tiedosto (build-pluginia ei oltu määritelty). Varmista, että tiedosto on kunnossa ennen kuin teet tehtävää!

* repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta __laskari6/IntJoukkoSovellus__ aloittelevan ohjelmoijan ratkaisu syksyn 2011 Ohjelmoinnin Jatkokurssin viikon 2 tehtävään 3 (ks. [http://www.cs.helsinki.fi/u/wikla/ohjelmointi/jatko/s2011/harjoitukset/2/](http://www.cs.helsinki.fi/u/wikla/ohjelmointi/jatko/s2011/harjoitukset/2/))
** ratkaisussa joukko-operaatiot on toteutettu suoraan luokkaan IntJoukko staattisina metodeina
* koodi jättää hieman toivomisen varaa ylläpidettävyyden suhteen
* refaktoroi luokan IntJoukko koodi mahdollisimman siistiksi
  * copypaste pois
  * muuttujille selkeät nimet
  * ei pitkiä (yli 8 rivisiä) metodeja
* koodissa on refaktorointia helpottamaan joukko yksikkötestejä
** kirjoita testejä tarpeen vaatiessa lisää, ne eivät ole välttämättä 100% kattavat

*HUOM* refaktoroi mahdollisimman pienin askelin ja pidä koodi koko ajan toimivana. Aja testit aina jokaisen refaktorointiaskeleen jälkeen! Järkevä refaktorointiaskeleen koko pieni muutos yhteen metodiin.

## 4. Tenniksen pisteenlaskun refaktorointi

Repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta __viikko6/Tennis__ löytyy ohjelma joka on tarkoitettu tenniksen [pisteenlaskentaan](https://github.com/emilybache/Tennis-Refactoring-Kata#tennis-kata).

Pisteenlaskennan rajapinta on yksinkertainen. Metodi <code>void getScore()</code> kertoo voimassa olevan tilanteeen tennispisteenlaskennan määrittelemän tavan mukaan. Sitä mukaa kun jompi kumpi pelaajista voittaa palloja, kutsutaan metodia  <code>void wonPoint(String player)</code> jossa parametrina on pallon voittanut pelaaja.

Esim. käytettäessä pisteenlaskentaa seuraavasti: 
``` java
public static void main(String[] args) {
    TennisGame game = new TennisGame("player1", "player2");

    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player2");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());
}
```

tulostuu

``` java
Love-All
Fifteen-Love
Thirty-Love
Thirty-Fifteen
Forty-Fifteen
Win for player1
```

Tulostuksessa siis kerrotaan mikä on pelitilanne kunkin pallon jälkeen kun _player1_ voittaa ensimmäiset 2 palloa, _player2_ kolmannen pallon ja _player1_ loput 2 palloa. 

Pisteenlaskentaohjelman koodi toimii ja sillä on erittäin kattavat testit. Koodi on kuitenkin luettavuudeltaan erittäin huonossa kunnossa. 

Tehtävänä on refaktoroida koodi luettavuudeltaan mahdollisimman ymmärrettäväksi. Koodissa tulee välttää "taikanumeroita" ja huonosti nimettyjä muuttujia. Koodi kannattaa jakaa moniin pieniin metodeihin, jotka nimennällään paljastavat oman toimintalogiikkansa.

Etene refaktoroinnissa __todella pienin askelin__. Aja testejä mahdollisimman usein. Yritä pitää ohjelma koko ajan toimintakunnossa.

Jos haluat käyttää jotain muuta kieltä kuin Javaa, löytyy koodista ja testeistä versioita useilla eri kielillä osoitteesta [https://github.com/emilybache/Tennis-Refactoring-Kata](https://github.com/emilybache/Tennis-Refactoring-Kata)

Tehtävä on kenties hauskinta tehdä pariohjelmoiden. Itse tutustuin tehtävään kesällä 2013 Extreme Programming -konferenssissa järjestetyssä Coding Dojossa, jossa tehtävä tehtiin satunnaisesti valitun parin kanssa pariohjelmoiden.

Lisää samantapaisia refaktorointitehtäviä osoitteessa [https://github.com/emilybache/Refactoring-Katas](https://github.com/emilybache/Refactoring-Katas)
* laskin

## 5. Kyselykieli NHLStatistics-ohjelmaan

Repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta __viikko6/QueryLanguage__ löytyy jälleen yksi versio tutusta NHL-tilastoja lukevasta ohjelmasta.

Tällä kertaa olemme kiinnostuneita tekemään hieman monimutkaisempia "kyselyjä" pelaajatietoihin, esim. __listaa kaikki joukkueen PHI pelaajat joilla on vähintään 5 maalia ja vähintään 10 syöttöä__.

Koodin onkin luotu hieman valmista kalustoa josta pääset liikkeelle. Edelläolevan kyselyn voi suorittaa seuraavasti:


``` java
public static void main(String[] args) {
    Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
 
    Matcher m = new And( new HasAtLeast(5, "goals"),
                         new HasAtLeast(10, "assists"),
                         new PlaysIn("PHI")
    );
 
    for (Player player : stats.matches(m)) {
        System.out.println( player );
    }
}
```

Luokalle __Statistics__ on tehty metodi __matches__, joka palauttaa listan pelaajista joille parametrina annettu __Matcher__-rajapinnan toteuttava olio palauttaa __true__

Tutustu ohjelman rakenteeseen

* huomioi miten __HasAtLeast__ käyttää Javan ns. reflektio-ominaisuutta kutsuessaan merkkijonoparametria vastaavaa metodia
* toinen huomioinarvoinen piirre on __And__-luokan konstruktorissa käytetty vaihtuvamittainen parametrilista, eli "vararg", ks. lisää esim: [http://www.javadb.com/using-varargs-in-java](http://www.javadb.com/using-varargs-in-java)

Tee rajapinnan __Matcher__ toteuttavat luokat, joiden avulla voit tehdä operaatiot

* HasFewerThan (HasAtLeast-komennon negaatio eli, esim. on vähemmän kuin 25 maalia)
* or
* not

Tee erilaisia kyselyjä, ja varmista että uudetkin operaatiot toimivat

Kyselyt perustuvat rakenteeltaan __decorator__-suunnittelumalliin, vastaavasti kuten luennon 9 dekoroitu pino. __And__- ja __OR__-muotoiset kyseltyt on muodostetty composite-suunnittelumallin hengessä, ne ovat __Matcher__-rajapinnan toteuttavia olioita, jotka sisältävät itse monta __Matcher__-olioa. Niiden käyttäjä ei kuitenkaan tiedä sisäisestä rakenteesta mitään.

## 6. Parannettu kyselykieli

Matcher-olioiden avulla tehtyä kyselykieltä vaivaa se, että kyselyjen rakentaminen on hieman ikävää, sillä jokaista kyselyn osaa kohti on luotava new-komennolla uusi olio. Tee luennon 9 pinorakentajan hengessä kyselyrakentaja, jonka avulla voit luoda Matcher-olioita.

Rakentaja voi toimia esim. seuraavaan tapaan.

Ensin kysely missä tulostetaan pelaajat joiden joukkue on NYR, joilla on vähintään 10 mutta vähemmän kuin 25 maalia:

``` java
public static void main(String[] args) {
    Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
 
    QueryBuilder query = new QueryBuilder();
 
    Matcher m = query.playsIn("NYR")
                     .hasAtLeast(10, "goals")
                     .hasFewerThan(25, "assists").build();
 
    for (Player player : stats.matches(m)) {
        System.out.println( player );
    }
```

Peräkkäin ketjutetut ehdot siis toimivat "and"-periaatteella.

Or-ehdon sisältävä komento voi olla muodostettu esim. seuraavasti:

``` java

Matcher m1 = query.playsIn("PHI")
                  .hasAtLeast(10, "goals")
                  .hasFewerThan(15, "assists").build();
 
Matcher m2 = query.playsIn("EDM")
                  .hasAtLeast(50, "points").build();
 
Matcher m = query.oneOf(m1, m2).build();

```

Tai kaikki sama ilman apumuuttujia:

``` java

Matcher m = query.oneOf(
                        query.playsIn("PHI")
                             .hasAtLeast(10, "goals")
                             .hasFewerThan(15, "assists").build(),
 
                        query.playsIn("EDM")
                             .hasAtLeast(50, "points").build()
                       ).build();
```

Rakentajasi ei ole pakko toimia samalla tavalla.


## 7. biershopin refaktorointia, osa 1

Repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta *viikko3/KumpulaBiershop* löytyy olutkaupan koodi.

Komento <code>mvn jetty:run</code> käynnistää ohjelman localhostin porttiin 8080. Jetty:n käynnistäminen aiheuttaa muutaman ikävän virheilmoituksen, mutta ei välitetä niistä.

Testit ajetaan tuttuun tapaan komennolla <code>mvn test</code>. Ohjelmassa on komenlaisia testejä

* JUnitilla tehdyt bisneslogiikkaa testaavat yksikkötestit
* JUnitilla tehdyt ohjausolioiden integraatiotason testit
* JBehavella tehdyt storytason testit

[JBehavessa](http://jbehave.org/) periaate on sama kuin easyB:ssä. Erona se, että testien bindausta koodiin ei tehdä story-tiedostoissa Emme tässä tehtävässä tarvitse JBehavea, mutta jos kiinnostaa voit vilkaista testejä:

* storyt hakemistossa __src/main/resources__ (NetBeansissa kohdan other sources alla)
* storyjen bindaus selenium-koodiin source packagen alla pakkauksessa __com.mycompany.webkauppa.scenarios__

Ohjelman arkkitehtuuria selvitetään hiukan luennon 9 kalvoilla 11-13. Käyttöliittymän ohjauksen toteuttavat Servletit löytyvät pakkauksesta __com.mycompany.webkauppa__. Ne käyttävät järjestelmän bisneslogiikkaa palvelukerroksen komento-olioiden välityksellä. Komento-oliot ovat pakkauksessa __com.mycompany.webkauppa.ohjaus__.

Ohjelmoija ei kuitenkaan ole tehnyt kaikin osin virheetöntä jälkeä. Servletit käyttävät komentoja seuraavasti:

``` java

public class LisaaOstoskoriinServlet extends WebKauppaServlet {
 
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        long tuoteId = Long.parseLong( request.getParameter("tuoteId") );
 
        OstoksenLisaysKoriin lisays = new OstoksenLisaysKoriin(haeSessionOstoskori(request), tuoteId);
        lisays.suorita();
 
        naytaSivu("/Tuotelista", request, response);
    }
}

```

Servletti siis luo itse komento-olion __OstoksenLisaysKoriin__, tästä syntyy tarpeeton riippuvuus konkreettiseen luokkaan.

Poista riippuvuus esittelemällä sopiva rajapinta (joudut muuttamaan kaikkien komentojen metodit yhteneviksi paluuarvon tyypeiltään) ja tekemällä sopiva tehdasluokka. Muuta komentojen konstruktorien näkyvyydeksi pakkausnäkyvyys, eli poista konstruktorin edestä sana public. Joudut muuttamaan myös pakkauksessa __com.mycompany.webkauppa.sovelluslogiikka.ohjaus__ olevia testejä.

Muutoksen jälkeen edellinen luokka muuttuu suunilleen seuraavanlaiseksi:

``` java

public class LisaaOstoskoriinServlet extends WebKauppaServlet {
 
    // HUOM: Komentotehtaan luominenja muuttujan määrittely kannattaa hoitaa yliluokassa WebKauppaServlet
    Komentotehdas komennot;
 
    public LisaaOstoskoriinServlet(){
        komennot = new Komentotehdas();
    }
 
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        long tuoteId = Long.parseLong( request.getParameter("tuoteId") );
 
        komennot.ostoksenLisaysKoriin(haeSessionOstoskori(request), tuoteId).suorita();
 
        naytaSivu("/Tuotelista", request, response);
    }
}

```

Varmista että testit menevät läpi ja ohjelma toimii muutosten jälkeen.

huom: tee refaktorointia pieni askel kerrallaan koko ajan varmistaen että testit menevät läpi. esim:

* tee ensin rajapinta Komento
* laita yksi komento-olioista toteuttamaan rajapinta
* korvaa Servletissä komento-olion muistavan muuttujan tyyppi rajapinnalla
* tee tehdasluokka ja tehdasmetodi ensimmäistä rajapinnan toteuttavaa komentoa varten
* luo olio servletissä komentotehtaan avulla
* ...

## 8. biershopin refaktorointia, osa 2

Huomioita biershopista edellisen jäljiltä:

* jouduit todennäköisesti tekemään testejä varten yhden oman metodin komentotehtaaseen, ratkaisu on hieman ikävä
* Ohjelmassa on vielä muutamia muitakin ikäviä piirteitä, mm. luokkien Varasto, Pankki, Toimitusjärjestelmä ja TuoteDAO ainoisiin instansseihin päästään käsiksi Singleton-suunnittelumallin avulla

Ratkaise nämä ongelmat seuraavasti:

* siirry singletonin käytöstä dependency injektioon, eli
** älä hae luokkien Varasto, Pankki, Toimitusjärjestelmä ja TuoteDAO instansseja getInstance-metodilla luokkia käytettäessä
** vaan oleta, että luokkien käyttäjille on annettu niiden instanssi konstruktoriparametrina
* tee Komentotehtaasta kaksi versiota, toinen Servlettien käyttöön ja toinen testien käyttöön
** testien käyttöön tarkoitettu tehdas luo testeille sopivia komentoja (mm. korvaa tietokannan keskusmuista käyttävällä tallennuksella, pankin ja toimitusjärjestelmän valekomponentilla)
** "normaali" tehdas taas luo komentoja jotka toimivat tuotantoympäristössä
* voit hyödyntää abstraktin tehtaan periaatetta:
** "http://en.wikipedia.org/wiki/Abstract_factory_pattern":http://en.wikipedia.org/wiki/Abstract_factory_pattern
** "http://www.javapractices.com/topic/TopicAction.do?Id=128":http://www.javapractices.com/topic/TopicAction.do?Id=128
** "http://www.oodesign.com/abstract-factory-pattern.html":http://www.oodesign.com/abstract-factory-pattern.html

## 9. kurssipalaute

On taas aika perinteisen kurssipalautteen: [https://ilmo.cs.helsinki.fi/kurssit/servlet/Valinta](https://ilmo.cs.helsinki.fi/kurssit/servlet/Valinta)


## bonustehtävät

*Viikon maksimi on 9, mutta tekemällä seuraavia voit paikata edellisten viikkojen tekemättömiä rasteja*

## 10 ja 11 (kahden rastin tehtävä) KPS yksin- ja kaksinpeli

* repositorion "https://github.com/mluukkai/ohtu2013":https://github.com/mluukkai/ohtu2013 hakemistosta __viikko6/KiviPaperiSakset__ löytyy tutun pelin tietokoneversio 
* ohjelmassa on kolme pelimoodia: ihminen vs. ihminen, ihminen vs. yksinkertainen tekoöly ja ihminen vs. monimutkainen tekoäly
* koodi sisältää runsaat määrät copy pastea, muutenkaan oliosuunnittelun periaatteet eivät ole vielä alkuperäisellä ohjelmoijalla olleet hallussa
* poista koodista kaikki toisteisuus ja tee siitä rakenteellisesti luennon 8 hengessä oikeaoppinen
** pelaa-metodi tulee toteuttaa template-metodina
** sopivan peliolion (kaksinpeli, helppo yksinpeli, vaikea yksinpeli) luominen tulee toteuttaa staattisen tehdasmetodin avulla
** pääohjelmalla ei saa olla riippuvuuksia konkreettisiin pelin toteuttaviin luokkiin

* jos teet tehtävän mielestäsi kaikkien tyylisääntöjen mukaan, merkkaa 2 rastia, jos ratkaisu ei ole kaikin osin tyylikäs, merkkaa yksi rasti

## 12 referaatti

lue joku allaolevista artikkeleista ja tee siitä noin 0.5 sivun referaatti

* [http://martinfowler.com/articles/designDead.html](http://martinfowler.com/articles/designDead.html)
* Lauri Suomalaisen kandidaattityö [Ohjelmistotuotantomenetelmien kehittyminen 1950-luvulta nykypäivään](https://github.com/Fleuri/Kandi/blob/master/kandi.pdf?raw=true)
* Tero Huomon kandidaattityö [Ohjelmistoarkkitehtuurin sisällyttäminen ketteriin ohjelmistotuotantomenetelmiin](http://www.cs.helsinki.fi/u/tahuomo/Kandidaatin_tutkielma_Tero_Huomo.pdf) 
* Kasper Hirvikosken kandidaattityö [Metriikat käytänteiden tukena ohjelmiston laadun arvioimisessa](https://github.com/kasperhirvikoski/kandidaatintutkielma/blob/master/tutkielma/metriikat-kaytanteiden-tukena.pdf?raw=true)
* Kenny Heinosen kandidaattityö [Ohjelmistoala ja ryhmätyöskentely](http://www.cs.helsinki.fi/u/kennyhei/kandi.pdf)
* [http://www.infoq.com/articles/ddd-evolving-architecture](http://www.infoq.com/articles/ddd-evolving-architecture)
* [http://www.infoq.com/articles/internal-dsls-java](http://www.infoq.com/articles/internal-dsls-java)


Palautus samaan repositorioon mihin palautat viikon muut tehtävät

## 13. toinen artikkelireferaatti

tee referaatti jostain toisesta tehtävän 12 artikkelista

## 14. kolmas artikkelireferaatti

tee referaatti kolmannestakin tehtävän 12 artikkelista

## 15. neljäs artikkelireferaatti

tee referaatti vielä neljännestä tehtävän 12 artikkelista

## tehtävien kirjaaminen palautetuksi

tehtävien kirjaus:

* Kirjaa tekemäsi tehtävät [tänne](http://ohtustats.herokuapp.com) 
  * **Palautus onnistuu vasta ma 14.4.**
  * huom: tehtävien palautuksen deadline on su 4.5. klo 23.59

palaute tehtävistä:

* Lisää viikon 1 tehtävässä 11 forkaamasi repositorion omalla nimelläsi olevaan hakemistoon tiedosto nimeltä viikko6
* tee viime viikon tehtävän tapaan pull-request
  * anna tehtävistä palautetta avautuvaan lomakkeeseen
  * huom: jos teeh tehtävät alkuviikosta, voi olla, että edellistä pull-requestiasi ei ole vielä ehditty hyväksyä ja et pääse vielä tekemään uutta requestia
