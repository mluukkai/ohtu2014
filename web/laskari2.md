# Laskari 2

## Huom: ohjausta tehtävien tekoon to klo 14-16 ja pe klo 14-16 salissa BK107

### Tehtävien palautuksen deadline su 23.3. klo 23.59

## palautetaan GitHubin kautta

* tee palautusta varten yksityinen repositorio ja lisää sille collaboratoriksi käyttäjä mluukkai, voit käyttää myös viikon 1 repositoriotasi
  * jos et ole vielä ehtinyt saada GitHubin academic-tunnusta, voit tehdä palautuksen normaalia repositoriota käyttäen
* palautusrepositorion nimi ilmoitetaan tehtävien lopussa olevalla palautuslomakkeella

## 1. riippuvuuksien injektointi osa 1

* lue ensin tiistain luennolla nopeasti läpikäytyjen asioiden kertaus [https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md](https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md)
* hae koodiesimerkit repostitoriosta [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2014/) (hakemistosta viikko2/RiippuvuuksienInjektointi) ja kokeile että kaikki toimivat
* järkevintä lienee että kloonaat repositorion paikalliselle koneellesi
  * vaikka viime viikolla sama repositorio forkattiin, ei forkattua repositorioa saa ihan helposti synkronoitua alkuperäiseen

## 2. riippuvuuksien injektointi osa 2: NHL-tilastot

* repositorion [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2014/) hakemistossa viikko2/Ohtu-NHLStatistics1 on ohjelma, jonka avulla on mahdollista tutkia [http://nhl.com](http://nhl.com)-sivulla olevia pelaajien tilastotietoja

* Ohjelma koostuu kolmesta luokasta.
  * <code>Statistics</code> on palvelun tarjoava luokka, se tarjoaa metodit yhden pelaajan tietojen näyttämiseen, pistepörssin näyttämiseen ja yhden joukkueen pelaajien tietojen näyttämiseen
  * <code>Player</code>  on luokka, jonka olioina Statistics käsittelee yksittäisen pelaajan tietoja
  * <code>PlayerReader</code>  on luokka, jonka avulla ohjelma käy hakemassa pelaajien tiedot internetistä
* Ohjelma on nyt ikävästi struktoroitu ja esim. yksikkötestaus on kovin hankalaa

**itse tehtävä:**

* Määrittele rajapinta <code>Reader</code>, jolla on samat julkiset metodit kuin PlayerReaderilla, eli ainoastaan metodi <code>List<Player> getPlayers()</code>. Laita PlayerReader toteuttamaan rajapinta.
* Muokkaa ohjelman rakennetta siten, että Statictics saa konstruktoriparametrina <code>Reader</code>-tyyppisen olion.
* Muokkaa pääohjelma siten, että se injektoi Statistics-oliolle PlayerReaderin ja kokeile että ohjelma toimii edelleen:

``` java
Statistics stats = new Statistics( new PlayerReader("http://nhlstats-2013-14.herokuapp.com/players.txt") );
```

## 3.  NHLStatistics-ohjelman yksikkötestaus

* tee yksikkötestit luokalle Statistics
  * testien rivi- ja haarautumakattavuuden tulee (Statistics-luokan osalta) olla 100% (mitataan coberturalla, ks. viikko 1)
  * testit eivät saa käyttää verkkoyhteyttä
  * verkkoyhteyden tarpeen saat eliminoitua luomalla testiä varten rajapinnan Reader-toteuttavan "stubin", jonka sisälle kovakoodaat palautettavan pelaajalistan
  * voit luoda stubin testin sisälle anonyyminä sisäluokkana seuraavasti:

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


## 4. lisää git:iä: branchit

lue brancheja käsittelevät osuudet seuraavista: [https://we.riseup.net/debian/git-development-howto](https://we.riseup.net/debian/git-development-howto) ja [http://www.ralfebert.de/tutorials/git/](http://www.ralfebert.de/tutorials/git/)
* jos haluat lukea hieman perusteellisemman selityksen asiasta, lue [http://git-scm.com/book](http://git-scm.com/book):n luku kolme
* tee samalla kaikki tekstien esimerkit

Kannattaa huomioida myös erittäin hyvä brancheja käsittelevä visuaalinen materiaali osoitteessa [http://pcottle.github.com/learnGitBranching/](http://pcottle.github.com/learnGitBranching/)

Varsin selkeältä vaikuttaa myös [https://www.atlassian.com/git/tutorial/git-branches](https://www.atlassian.com/git/tutorial/git-branches)

**huom:** kun liikut branchien välillä kannattaa pitää working tree ja staging -alue tyhjinä!

tee seuraavat paikalliseen git-repositorioosi (kyseessä ei siis tarvitse olla tehtävien palautusrepositorio)

* luo repositorio ja committaa masteriin tiedosto __masteri1.txt__
* luo branch __eka__, siirry branchiin, luo sinne tiedosto __eka.txt__ ja committaa
* siirry takaisin __master__-branchiin, tiedoston __eka.txt__ ei pitäisi nyt näkyä
* lisää ja committaa __masteriin__ tiedosto __masteri2.txt__
* mene branchiin __eka__ ja tarkasta, että __masteriin__ lisätty tiedosto ei ole branchissa
* lisää branchiin tavaraa, esim. tiedosto __eka2.txt__ ja committaa
* siirry takaisin __master__-branchiin
* tarkasta että __eka__-branchiin lisätyt muutokset eivät ole masterissa
* tarkastele komennolla <code>gitk --all</code> miltä repositorio ja branchit näyttävät (ei tietoa toimiiko gitk windowsissa)
* mergeä branchin __eka__ sisältö __masteriin__
* katso jälleen miltä näyttää gitk --all
* tuhoa branchi __eka__

## 5. lisää git:iä: konflikti!

tee paikalliseen git-repoon seuraavat

* lisää __master__-branchiin tiedosto __tarkea.txt__, kirjota sinne muutama rivi tekstiä ja committaa
* tee uusi branchi __toka__, editoi tiedoston __tarkea.txt__ loppua ja committaa
* mene takaisin __master__-branchiin, editoi tiedoston __tarkea.txt__ alkua ja committaa
* mergeä branchin __toka__ sisältö __masteriin__
  * katso tiedoston __tarkea.txt__-sisältöä, sen pitäisi sisältää nyt molemmissa brancheissa tehdyt muutokset
  * **huom:** jo tässä vaiheessa saattaa syntyä konflikti jos olet vahingossa muuttanut merkkejä väärästä kohtaa tiedostoa! Toimi tällöin ao. ohjeen mukaan.
* lisää jotain tiedoston loppuun ja committaa
* siirry branchiin __toka__
* lisää jotain tiedoston __tarkea.txt__ loppuun ja committaa
* mergeä branchin __master__ sisältö branchiin __toka__
  * nyt pitäisi aiheutua konflikti
* ratkaise konflikti:
  * editoi tiedoston __tarkea.txt__ sisältö haluamaksesi
  * ja toimi em. artikkelien ohjeen mukaan eli lisää konfliktoinut tiedosto staging-alueelle ja committoi

## 6. lisää git:iä: branchit ja GitHub

aloita lukemalla ProGit kirjasta luku [Remote Branches](http://git-scm.com/book/en/Git-Branching-Remote-Branches)

branch githubiin:

* lisää tehtävien palauttamiseen käyttämäsi GitHub-reposition paikalliseen kopioon branchit __haara1__ ja __haara2__
* mene branchiin __haara1__, lisää sinne tiedosto __haara1.txt__ ja committaa
* mene branchiin __haara2__, lisää sinne tiedosto __haara2.txt__ ja committaa
* pushaa uudet branchit GitHubiin
* tarkastele GitHub-repositoria selaimella, varmista että branchit syntyvät ja niillä on haluttu sisältö

kloonaa GitHub-repositoriosta koneellesi toinen kopio

* kuten huomaat, eivät branchit tule kloonattuun kopioon
* tee paikalliseen kopioon branch joka "träkkää" GitHub:issa olevan projektisi branchia __haara1__ (ks. [http://git-scm.com/book/en/Git-Branching-Remote-Branches](http://git-scm.com/book/en/Git-Branching-Remote-Branches) kohta Tracking Branches)
* lisää "träkkäävään" branchiin joku tiedosto, committaa ja pushaa branchi GitHubiin
* tarkastele GitHub-repositoria selaimella, varmista että branchi päivittyy

mene GitHub-repon alkuperäiseen paikalliseen kopioon

* mene branchiin __haara1__ ja pullaa muutokset GitHub:in vastaavasta branchista
  * huom: koska kyseessä ei ole "träkkäävä" branchi, joudut pullaamaan komennolla <code>git pull origin haara1</code>
* mene branchiin __haara2__, lisää sitten tiedosto, committaa ja pushaa branchi GitHubiin
  * huom: koska kyseessä ei ole "träkkäävä" branchi, ei git push riitä vaan joudut määrittelemään branchin jonne push kohdistuu eli antamaan komennon <code>git push origin haara2</code>

mene jälleen toiseen kopioon
* suorita komento <code>git remote show origin</code>
  * komento kertoo 'origin':issa eli githubissa olevien branchien ja paikallisten branchien suhteen 
* tee sinne GitHub:issa olevan projektisi branchia __haara2__ träkkäävä branch
* suorita jälleen <code>git remote show origin</code>, mitä muutoksia huomaat?
* tee branchiin muutoksia ja pushaa ne githubiin
  *  huom: koska kyseessä träkkäävä branch, riittää git push
* tarkastele GitHub-repositoria selaimella, varmista että branchi päivittyy

suorita vielä komento <code>git remote show origin</code> alkuperäisessä paikallisessa kopiossa

Branchien kanssa työskentely voi aluksi tuntua sekavalta varsinkin jos GitHub:issa on myös useita brancheja.

### mihin brancheja käytetään?

Ohjelmistotimi voi käyttää Gitiä hyvin monella eri tyylillä. Artikkeli
[https://www.atlassian.com/git/workflows](https://www.atlassian.com/git/workflows) esittelee muutamia erilaisia tapoja järjestellä tiimin gitin käyttöön liittyvä workflow. Yksi yleinen tapa branchien käyttöön ovat ns. _featurebranchit_:

> The core idea behind the Feature Branch Workflow is that all feature development should take place in a dedicated branch instead of the master branch. This encapsulation makes it easy for multiple developers to work on a particular feature without disturbing the main codebase. It also means the master branch will never contain broken code, which is a huge advantage for continuous integration environments.

Jos kiinnostaa, lue lisää yo. dokumentista.

## 7. epäajantasaisen kopion pushaaminen

Demonstroidaan usein esiintyvää tilannetta, jossa epäajantasaisen repositorion pushaaminen githubissa olevaan etärepositorioon epäonnistuu.

* mene alkuperäiseen repositorion alkuperäisen kopion __master__ haaraan, tee joku muutos, commitoi ja pushaa se githubiin
* mene toisen kopion __master__-haaraan ja  tee sinne joku muutos 
* commitoi ja pushaa muutos githubiin
* kaikki ei kuitenkaan mene hyvin, seurauksena on seuraavantyylinen virheilmoitus:

<pre>
mbp-18:ohtu-viikko1-2014 mluukkai$ git push
To git@github.com:mluukkai/ohtu-viikko1-2014.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'git@github.com:mluukkai/ohtu-viikko1-2014.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first merge the remote changes (e.g.,
hint: 'git pull') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
mbp-18:ohtu-viikko1-2014 mluukkai$ 
</pre>

Virheen syynä on se, että githubissa oleva __master__-haara oli edellä paikallisen repositorion __master__-haaraa. Ongelma korjaantuu tekemällä ensin <code>git pull</code>, ratkaisemalla mahdolliset konfliktit ja pushaamalla sitten uudelleen.
* eli toimi näin ja varmista, että tekemäsi muutokset menevät githubiin

## 8. riippuvuuksien injektointi osa 3: Verkkokauppa

Repositorion [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2014/) hakemistossa viikko2/Verkkokauppa1 on yksinkertaisen verkkokaupan ohjelmakoodi

* tutustu koodiin, piirrä luokkakaavio ohjelman rakenteesta
* ohjelman luokista <code>Pankki</code>, <code>Varasto</code>, <code>Viitegeneraattori</code> ja <code>Kirjanpito</code> ovat sellaisia, että niistä on tarkoitus olla olemassa vain yksi olio. Tälläisiä ainutkertaisia olioita sanotaan **singletoneiksi**. Koodissa singletonit ovat toteutettu "klassisella tavalla"
  * Singleton on [GoF-kirjan](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612/ref=pd_bxgy_b_text_y) yksi alkuperäisistä suunnittelumalleista, lue lisää singletoneista esim. [täältä](http://www.oodesign.com/singleton-pattern.html)
  * Singleton ei ole erinäisistä syistä enää oikein muodissa, ja korvaamme sen seuraavassa tehtävässä
    
* kuten huomaamme, on koodissa toivottoman paljon konkreettisia riippuvuuksia:
  * Varasto --> Kirjanpito
  * Pankki --> Kirjanpito
  * Kauppa --> Pankki
  * Kauppa --> Viitegeneraatori
  * Kauppa --> Varasto
* Pura luokan <code>Kauppa</code> konkreettiset riippuvuudet rajapintojen avulla
  * *HUOM:* NetBeansissa on automaattinen refaktorointiominaisuus, jonka avulla luokasta saa helposti generoitua rajapinnan, jolla on samat metodit kuin luokalla. Klikkaa luokan kohdalla hiiren oikeaa nappia, valitse refactor ja "extract interface"
  * muut riippuvuudet jätetään vielä
   
* Määrittele luokalle sopiva konstruktori, jotta voit injektoida riippuvuudet, konstruktorin parametrien tulee olla tyypiltään **rajapintoja**
* Muokkaa pääohjelmasi seuraavaan tyyliin:

``` java
Kauppa kauppa = new Kauppa(Varasto.getInstance(), Pankki.getInstance(), Viitegeneraattori.getInstance() );
```

## 9. riippuvuuksien injektointi osa 4: ei enää singletoneja verkkokaupassa

* singleton-suunnittelumallia pidetään osittain ongelmallisena, poistammekin edellisestä tehtävästä singletonit
** katso esim. [http://blogs.msdn.com/b/scottdensmore/archive/2004/05/25/140827.aspx](http://blogs.msdn.com/b/scottdensmore/archive/2004/05/25/140827.aspx)
* **poista** kaikista luokista <code>getInstance</code>-metodit ja staattinen <code>instance</code>-muuttuja
  * joudut muuttamaan luokilla olevat private-konstruktorit julkisiksi
* poista rajapintojen ja dependency injektionin avulla edellisen tehtävän jäljiltä jääneet riippuvuudet, eli
  * Varasto --> Kirjanpito
  * Pankki --> Kirjanpito
* Muokkaa pääohjelmasi vastaamaan uutta tilannetta, eli suunilleen muotoon:

``` java
Kirjanpito kirjanpito      = new Kirjanpito();
Varasto varasto            = new Varasto(kirjanpito);
Pankki pankki              = new Pankki(kirjanpito);
Viitegeneraattori viitegen = new Viitegeneraattori();
Kauppa kauppa              = new Kauppa(varasto, pankki, viitegen);
```

Kuten huomaamme, alkaa kaupan konfigurointi olla aika vaivalloista...

## 10. Spring osa 1: Verkkokauppa siistiksi

Spring tarjoaa pelastuksen käsillä olevaan tilanteeseen.

Lue nyt [sivu](https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md) Riippuvuuksien-injektointi kohdasta [Dependency injection Spring-sovelluskehyksessä](https://github.com/mluukkai/ohtu2014/blob/master/web/riippuvuuksien_injektointi.md#dependency-injection-spring-sovelluskehyksess%C3%A4) loppuun asti

*  projektiin on konfiguroitu valmiiksi springin tarvitsemat riippuvuudet, konfiguraatiotiedosto <code>spring-context.xml</code> löytyy hakemiston _src/main/resources_ alta (NetBeansissa tämä löytyy kohdan Other Sources -alta)
  * *HUOM* mahdolliset virheilmoitukset __"org.springframework... package does not exist"__ katoavat kun buildaat projektin ensimmäisen kerran!
* ota mallia tehtävän 1 ohjeesta ja konfiguroi verkkokauppa Springin xml-muotoista konfiguraatiota siten, että kauppa-olion luominen onnistuu Springin avulla seuraavasti:

``` java
public static void main(String[] args) {
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
 
    Kauppa kauppa = ctx.getBean(Kauppa.class);
    //...
}
```

Kannattanee edetä tehtävässä pienin askelin siirtäen yksi luokka kerrallaan Springin hallinnoinnin alle

## 12. Spring osa 2: Verkkokauppa siistiksi annotaatioilla

* HUOM: älä tee tätä edellisen tehtävän päälle, tee projektista kopio
* tai tee se erilliseen branchiin:
  * lue kohta branches osoitteesta http://www.ralfebert.de/tutorials/git/
  * tee tälle tehtävälle branch "annotaatiot"
  * jotta joku muu branch kuin master (eli "pääbranch") saadaan githubiin, tulee push-komennon olla muodossa git push origin <branchin-nimi>
  * palaamme brancheihin tarkemmin ensi viikon tehtävissä

* muuta edellistä tehtävää siten, että konfigurointi tapahtuu annotaatioiden <code>@Component</code> ja <code>@Autowired</code> avulla
* huom: 
  * tehtävää ei välttämättä kannata tehdä yhtenä isona askeleena, saattaa olla viistasta muuttaa luokka kerrallaan xml-konfiguraatiosta annotaatiokonfiguroiduksi
  * virheilmoitukset eivät ole noviisille selkeimpiä mahdollisa
  * muista määritellä <code>@Component</code> kaikkiin edellisessä tehtävässä xml:ssä määriteltyihin luokkiin
  * muista laittaa <code>@Autowired</code> jokaiseen luokkaan, jolla on riippuvuuksia

## tehtävien kirjaaminen palautetuksi

tehtävien kirjaus:

* Kirjaa tekemäsi tehtävät [tänne](http://ohtustats.herokuapp.com) 
  * **Palautus onnistuu vasta ma 17.3.**
  * huom: tehtävien palautuksen deadline on su 23.3. klo 23.59

palaute tehtävistä:

* Lisää viikon 1 tehtävässä 11 forkaamasi repositorion omalla nimelläsi olevaan hakemistoon tiedosto nimeltä viikko2
* tee viime viikon tehtävän tapaan pull-request
  * anna tehtävistä palautetta avautuvaan lomakkeeseen
  * huom: jos teeh tehtävät alkuviikosta, voi olla, että edellistä pull-requestiasi ei ole vielä ehditty hyväksyä ja et pääse vielä tekemään uutta requestia


