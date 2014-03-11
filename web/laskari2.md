# laskari 2

deadline 

## palautetaan GitHubin kautta

* tee palautusta varten yksityinen repositorio ja lisää sille collaboratoriksi käyttäjä mluukkai, voit käyttää myös viikon 1 repositoriotasi
  * jos et ole vielä ehtinyt saada GitHubin academic-tunnusta, voit tehdä palautuksen normaalia repositoriota käyttäen
* palautusrepositorion nimi ilmoitetaan tehtävien lopussa olevalla palautuslomakkeella

## 1. riippuvuuksien injektointi osa 1

* tiistain luennolla nopeasti läpikäytyjen asioiden kertaus [https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md](https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md), lue se ensin
* hae koodiesimerkit repostitoriosta [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2014/) (hakemistosta viikko2/RiippuvuuksienInjektointi*) ja kokeile että kaikki toimivat

* järkevintä lienee että kloonaat repositorion paikalliselle koneellesi
** vaikka viime viikolla sama repositorio forkattiin, ei forkattua repositoria saa ihan helposti synkronoitua alkuperäiseen

## 2. riippuvuuksien injektointi osa 2: NHL-tilastot

* repositorion "https://github.com/mluukkai/ohtu2013/":https://github.com/mluukkai/ohtu2013/ hakemistosta laskari2/Ohtu-NHLStatistics1 on ohjelma, jonka avulla on mahdollista tutkia nhl.com-sivulla olevia pelaajien tilastotietoja
* Ohjelma koostuu kolmesta luokasta.
** <code>Statistics</code> on palvelun tarjoava luokka, se tarjoaa metodit yhden pelaajan tietojen näyttämiseen, pistepörssin näyttämiseen ja yhden joukkueen pelaajien tietojen näyttämiseen
** <code>Player</code>  on luokka jonka, olioina Statistics käsittelee yksittäisen pelaajan tietoja
** <code>PlayerReader</code>  on luokka, jonka avulla ohjelma käy hakemassa pelaajien tiedot internetistä
* Ohjelma on nyt ikävästi struktoroitu ja esim yksikkötestaus on kovin hankalaa

itse tehtävä:

* Määrittele rajapinta <code>Reader</code> , jolla on samat metodit kuin PlayerReaderilla. Laita PlayerReader toteuttamaan rajapinta.
* Muokkaa ohjelman rakennetta siten, että Statictics saa konstruktoriparametrina Reader-olion.
* Muokkaa pääohjelma siten, että se injektoi Statistics-oliolle PlayerReaderin ja kokeile että ohjelma toimii edelleen:

``` java
Statistics stats = new Statistics( new PlayerReader("http://nhlstatistics.herokuapp.com/players.txt") );
```

h2. 3.  NHLStatistics-ohjelman yksikkötestaus

* tee yksikkötestit luokalle Statistics
** testien rivikattavuuden tulee olla 100% (mitataan coberturalla, ks. viikko 1)
** testit eivät saa käyttää verkkoyhteyttä
** verkkoyhteyden tarpeen saat eliminoitua luomalla testiä varten rajapinnan Reader-toteuttavan "stubin" jonka sisälle kovakoodaat palautettavan pelaajalistan
** voit luoda stubin testin sisälle anonyyminä sisäluokkana seuraavasti:

``` java
public class StaticsticsTest {
 
    Statistics stats;
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    // ...
}
```

Kun injektoit readerStub-olion testissä Statistics-oliolle, palauttaa se aina saman pelaajalistan.

h2. 4. riippuvuuksien injektointi osa 3: Verkkokauppa

Repositorion  "https://github.com/mluukkai/ohtu2013/":https://github.com/mluukkai/ohtu2013/ hakemistosta laskari2/Verkkokauppa1 on yksinkertaisen verkkokaupan ohjelmakoodi

* tutustu koodiin, piirrä luokkakaavio ohjelman rakenteesta
* ohjelman luokista Pankki, Varasto, Viitegeneraattori ja Kirjanpito ovat sellaisia, että niistä on tarkoitus olla olemassa vaan yksi olio. Tälläisiä ainutkertaisia olioita sanotaan *singletoneiksi*. Koodissa singletonit ovat toteutettu "klassisella tavalla"
** Singleton on "GoF-kirjan":http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612/ref=pd_bxgy_b_text_y yksi alkuperäisistä suunnittelumalleista, lue lisää singletoneista esim. "täältä":http://www.oodesign.com/singleton-pattern.html
** Singleton ei ole erinäisistä syistä enää oikein muodissa, ja korvaamme sen seuraavassa tehtävässä
    
* kuten huomaamme, on koodissa toivottoman paljon konkreettisia riippuvuuksia:
** Varasto --> Kirjanpito
** Pankki --> Kirjanpito
** Kauppa --> Pankki
** Kauppa --> Viitegeneraatori
** Kauppa --> Varasto
* Pura luokan *Kauppa* konkreettiset riippuvuudet rajapintojen avulla
** *HUOM:* NetBeansissa on automaattinen refaktorointiominaisuus, jonka avulla luokasta saa helposti heneroitua rajapinnan jolla on samat metodit kuin luokalla. Klikkaa luokan kohdalla hiiren oikeaa nappia, valitse refactor ja "extract interface"
** muut riippuvuudet jätetään vielä
   
* Määrittele luokalle sopiva konstruktori, jotta voit injektoida riippuvuudet
* Muokkaa pääohjelmasi seuraavaan tyyliin:


``` java
Kauppa kauppa = new Kauppa(Varasto.getInstance(), Pankki.getInstance(), Viitegeneraattori.getInstance() );
```

h2. 5. riippuvuuksien injektointi osa 4: ei enää singletoneja verkkokaupassa

* singleton-suunnittelumallia pidetään osittain ongelmallisena, poistammekin edellisestä tehtävästä singletonit
** katso esim. "http://blogs.msdn.com/b/scottdensmore/archive/2004/05/25/140827.aspx":http://blogs.msdn.com/b/scottdensmore/archive/2004/05/25/140827.aspx
* poista kaikista luokista getInstance-metodit ja staattinen instance-muuttuja
* poista rajapintojen ja dependency injektionin avulla edellisen tehtävän jäljiltä jääneet riippuvuudet, eli
** Varasto --> Kirjanpito
** Pankki --> Kirjanpito
* Muokkaa pääohjelmasi vastaamaan uutta tilannetta, eli suunilleen muotoon:

``` java
Kirjanpito kirjanpito      = new Kirjanpito();
Varasto varasto            = new Varasto(kirjanpito);
Pankki pankki              = new Pankki(kirjanpito);
Viitegeneraattori viitegen = new Viitegeneraattori();
Kauppa kauppa              = new Kauppa(varasto, pankki, viitegen);
```

Kuten huomaamme, alkaa kaupan konfigurointi olla aika vaivalloista...

h2. 6. Spring osa 1: Verkkokauppa siistiksi

Spring tarjoaa pelastuksen käsillä olevaan tilanteeseen.

Lue nyt "sivu":https://github.com/mluukkai/ohtu2013/wiki/Riippuvuuksien-injektointi kohdasta Dependency injection Spring-sovelluskehyksessä loppuun asti

*  projektiin on konfiguroitu valmiiksi springin tarvitsemat riippuvuudet, konfiguraatiotiedosto spring-context.xml löytyy src/main/resources-kansion alta (NetBeansissa tämä löytyy kohdan Other Sources -alta)
** *HUOM* mahdolliset virheilmoitukset __"org.springframework... package does not exist"__ katoavat kun buildaat projektin ensimmäisen kerran!
* ota mallia tehtävän 1 ohjeesta ja konfiguroi verkkokauppa Springin xml-muotoista konfiguraatiota siten, että kauppa-olion luominen onnistuu Springin avulla seuraavasti:

``` java
public static void main(String[] args) {
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
 
    Kauppa kauppa = ctx.getBean(Kauppa.class);
    //...
}
```

Kannattanee edetä tehtävässä pienin askelin siirtäen yksi luokka kerrallaan Springin hallinnoinnin alle

h2. 7. Spring osa 2: Verkkokauppa siistiksi annotaatioilla

* HUOM: älä tee tätä edellisen tehtävän päälle, tee projektista kopio
* tai tee se erilliseen branchiin:
** lue kohta branches osoitteesta http://www.ralfebert.de/tutorials/git/
** tee tälle tehtävälle branch "annotaatiot"
** jotta joku muu branch kuin master (eli "pääbranch") saadaan githubiin, tulee push-komennon olla muodossa git push origin <branchin-nimi>
** palaamme brancheihin tarkemmin ensi viikon tehtävissä

* muuta edellistä tehtävää siten, että konfigurointi tapahtuu annotaatioiden @Component ja @Autowired avulla
* huom: 
** tehtävää ei välttämättä kannata tehdä yhtenä isona askeleena, saattaa olla viistasta muuttaa luokka kerrallaan xml-konfiguraatiosta annotaatiokonfiguroiduksi
** virheilmoitukset eivät ole noviisille selkeimpiä mahdollisa
** muista määritellä kaikkiin @Component edellisessä tehtävässä xml:ssä määriteltyihin luokkiin
** muista laittaa @Autowired jokaiseen luokkaan jolla on riippuvuuksia

h2. tehtävien kirjaaminen palautetuksi

tehtävien kirjaus:

* Kirjaa tekemäsi tehtävät "tänne":http://ohtustats-2013.herokuapp.com/ (sivun aukeamisessa saattaa joskus kestää hetki)
** huom: tehtävien palautuksen deadline on su 24.3. klo 23.59
* Kirjauksen onnistumisen ja aiemmat kirjauksesi voit tarkistaa "täältä":http://ohtustats-2013.herokuapp.com/opiskelija

palaute tehtävistä:

* Lisää viikon 1 tehtävässä 8 forkaamaasi repositorion omalla nimelläsi olevaan repositorioon hakemistoon tiedosto nimeltä viikko2
* tee viime viikon tehtävän tapaan pull-request
** anna tehtävistä palautetta avautuvaan lomakkeeseen
** huom: jos teeh tehtävät alkuviikosta, voi olla, että edellistä pull-requestiasi ei ole vielä ehditty hyväksyä ja et pääse vielä tekemään uutta requestia


