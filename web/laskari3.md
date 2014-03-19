# UNDER CONSTRUCTION!! Laskari 3

## Huom: ohjausta tehtävien tekoon to klo 14-16 ja pe klo 14-16 salissa BK107

### Tehtävien palautuksen deadline su 30.3. klo 23.59

## palautetaan GitHubin kautta

* palautusta varten tarvitaan yksityinen repositorio, jolla collaboratorina käyttäjä mluukkai
  * kannattaa käyttää samaa repoa kuin viikon 2 tehtävissä
* palautusrepositorion nimi ilmoitetaan tehtävien lopussa olevalla palautuslomakkeella

## 1. lisää mavenia: koodin staattinen analyysi

* luennolla 5 puhuttiin koodikatselmoinnin yhteydessä staattisen analyysin työkaluista, joita voidaan käyttää koodin katselmoinnin apuna
* tutustu staattisen analyysin työkaluun chekstyleen ks. [http://checkstyle.sourceforge.net/](http://checkstyle.sourceforge.net/)
* checkstyleä on helppo käyttää maven-projekteiss,a sillä checkstyle on valmiiksi konfiguroituna pluginina mavenissa, ks. [http://maven.apache.org/plugins/maven-checkstyle-plugin/](http://maven.apache.org/plugins/maven-checkstyle-plugin/) checkstyleä kannattaa käyttää yhdessä [http://maven.apache.org/plugins/maven-jxr-plugin/](http://maven.apache.org/plugins/maven-jxr-plugin/):n kanssa
  * jxr:n avulla checkstylen raportista pääsee klikkaamalla vastaaville sorsakoodin riveille

mene nyt johonkin valmiiseen projektiisi, esim. viikon 2 verkkokauppaan
* kokeile suorittaa <code>mvn jxr:jxr checkstyle:checkstyle</code>
* avaa raportti selaimella polulta __/target/site/checkstyle.html__
  * tutki raporttia
* oletusarvoisesti raportoidaan paljon kaikenlaista ja oleellinen uhkaa hukkua detaljien joukkoon

checkstylen tarkkailemien virheiden joukko on konfiguroitavissa erillisen koniguraatiotiedoston avulla
* hae repositiorion [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2014/) hakemistosta viikko3 konfiguraatiotiedoston pohja __my_checks.xml__, talleta se sopivaan paikkaan, kokeile:
        <code>mvn jxr:jxr checkstyle:checkstyle -Dcheckstyle.config.location=my_checks.xml</code>       
* komennossa oleva polku olettaa että konfiguraatiotiedosto sijaitsee projektihakemistoston juuressa
  * huom: saattaa olla, että komento ei toimi windowsilla, ongelma ehkä vaan win 8:ssa. jos näin käy, konfiguraatiotiedoston sijainnon voi määritellä pom.xml-tiedostossa seuraavasti:

<pre>
    <plugins>
         <!-- muut mahdolliset pluginit -->
 
         <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-checkstyle-plugin</artifactId>
              <version>2.10</version>
              <configuration>
                  <configLocation>my_checks.xml</configLocation>
              </configuration>
         </plugin>
    </plugins>
</pre>

eli lisää uusi __plugin__-määrittely __plugins__-määrittelyjen sisälle. Jos __plugins__:eja ei ole, joudut lisäämään myös <code><plugins>...</plugins></code>-tägit. 

* tee alkuperäisestä konfiguraatiotiedostosta kopio, ja poista kopiosta kaikki elementin <code>tree walker</code> sisällä olevat tarkistukset 
* määrittele tiedostoon seuraavat säännöt (ks. available checks ja standard checks checkstylen [sivuilta](http://checkstyle.sourceforge.net/)):
  * metodien pituus max 10 riviä (tämä ja seuraavat säännöt määritellään moduulin tree walker sisälle)
  * ei yli yhtä sisäkkäisiä if:iä
  * ei sisäkkäisiä for:eja
  * koodi on oikein sisennettyä
  * syklomaattinen koodikompleksisuus korkeinaan 3 (selvitä mitä tarkoittaa!)
  * ei yli 5 rivin copy-pasteja (eli duplicated codea, löytyy kohdasta _standard checks_), huom: tätä tarkastusta ei tule laittaa tree-walkerin sisälle vaan "samalle tasolle" walkerin kanssa
  * tee koodiin muutoksia, jolla testaat että rikkoutuvat ehdot huomataan

## 2. sonar

[Sonar](http://www.sonarsource.org/) on ohjelma, joka kokoaa erilaisia koodin laatua mittaavien työkalujen (mm. staattisen analyysin työkalut kuten checkstyle ja testikattavuus kuten cobertura) yhdeksi raportiksi. Sonar tallettaa generoidut raportit tietokantaan, ja näinollen koodin laadun kehitystä on mahdollista seurata projektin edetessä.

Tutkitaan nyt Sonarilla muutaman projektin koodia

Käynnistetään ensin Sonar
* lataa Sonar osoitteesta [http://www.sonarsource.org/downloads/](http://www.sonarsource.org/downloads/)
* pura zip-paketti esim. kotihakemistoosi ja mene hakemistoon <code>sonar-3.5/bin/linux-x86-32</code> (tai koneesi tyyppiä vastaavaan hakemistoon)
* käynnistä Sonar. Linuxissa tämä tapahtuu komennolla <code>./sonar.sh start</code> 
* Sonarin raportit ovat näkyvissä selaimessa osoitteessa [http://localhost:9000](http://localhost:9000), avaa sivu
  * *HUOM:* laitoksen koneilla sonar vaikuttaa käynnistyvän __hitaasti__. Jos niin käy, sammuta sonar komennolla <code>./sonar.sh stop</code>, uudelleenkäynnistä ja uudelleenlataa sivua... jo sonar toivonmukaan herää  

Generoidaan raportti muutamalle projektille
* maven-muotoisille projekteille raportti generoidaan antamalla projektin hakemistossa komento <code>mvn clean test sonar:sonar</code>
* generoi raportti viikon 2 laskarien verkkokaupalle ja  repositiorion [https://github.com/mluukkai/ohtu2014/](https://github.com/mluukkai/ohtu2013/) hakemistostosta KumpulaBiershop löytyvälle olutverkkokaupalle
  * huomaa, että Sonar pitää olla käynnistettynä raportin generointihetkellä!
* tutki raportteja
  * katso erityisesti mitä löytyy kohdan __Tools__ alta
  * mieti miten korjaisit sonarin raportoimat koodin ongelmat 

Sivulta [http://nemo.sonarsource.org](http://nemo.sonarsource.org) löytyy useiden open source -projektien Sonar-raportteja. 
* vertaile esim. [Mavenin](http://nemo.sonarqube.org/dashboard/index/99176) raportoitua koodin laatua Spring-sovelluskehykseen kuuluvan komponentin [Spring-patch](http://nemo.sonarqube.org/dashboard/index/140632) koodin laatuun

## 3. tutustuminen easyB:hen

Lue seuraava [https://github.com/mluukkai/ohtu2014/blob/master/web/easyb.md](https://github.com/mluukkai/ohtu2014/blob/master/web/easyb.md)

tutustu linkin takana olevan ohjelman rakenteeseen ja aja siihen liittyvään testit.
* käynnistä ohjelma <code>mvn exec ...</code> -komennolla (ks. [viikon 1 laskarit](https://github.com/mluukkai/ohtu2014/blob/master/web/laskari1.md#5-maven))
* ohjelman tuntemat komennot ovat __login__ ja __new__

## 4. Kirjautumisen testit

tee User Storyn *User can log in with valid username/password-combination* kaikista testeistä ajettavia

## 5. Uuden käyttäjän rekisteröitymisen testit

tee User storyn *A new user account can be created if a proper unused username and a proper password are given* kaikista testeistä ajattavia.

* käyttäjätunnuksen on oltava merkeistä a-z koostuva vähintään 3 merkin pituinen merkkijono joka ei ole vielä käytössä
* salasanan on oltava pituudeltaan vähintään 8 merkkiä ja sen tulee sisältää vähintään yksi numero tai erikoismerkki
* **Täydennä ohjelmaa siten että testit menevät läpi**

Testejä kannattaa tehdä yksi kerrallaan, laittaen samalla vastaava ominaisuus ohjelmasta kuntoon.

## 6. Spring jälleen kerran

Ennen kuin sovellus päästään käynnistämään, on se konfiguroitava:

``` java
public static void main(String[] args) {
    UserDao dao = new InMemoryUserDao();
    IO io = new ConsoleIO();
    AuthenticationService auth = new AuthenticationService(dao);
    new App(io, auth).run();
}
```

Muuta ohjelmaa siten, että sovelluksen konfigurointi hoidetaan Springin avulla (joko xml- tai annotaatioperustaisesti), ja main:iksi riittää:

``` java
public static void main(String[] args) {
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
 
    App application = ctx.getBean(App.class);
    application.run();
}
```

## 7. FileUserDAO

Laita ohjelma tallettamaan käyttäjätiedot tiedostoon. Hoida asia siten, että teet luokan <code>FileUserDAO</code>, joka toteuttaa rajapinnan <code>UserDAO</code>. Anna FileUserDAO:lle sen käyttämä tiedosto konstruktorin parametrina. Testatessa on edelleen mielekästä käyttää InMemoryUserDAO:a.

* Jos tiedostojen käsittely on päässyt unohtumaan, ohjeita esim. [täällä](http://www.cs.helsinki.fi/group/java/k12/ohja/materiaali.html#53)
** jos salasanatiedosto sijaitsee projektihakemiston juuressa, sen luettavaksi avaaminen onnistuu komennolla <code>new Scanner(new File("salasanat.txt"));</code>
** päätä itse mitä tapahtuu tilanteessa, jossa parametrina annettua tiedostoa ei ole olemassa

Jos teit edellisen tehtävän, muokkaa Spring-konfiguraatiosi ottamaan huomioon uusi tilanne. Huom: joutunet konfiguroimaan FileUserDAO:n xml:ssä, sillä merkkijonomuotoista konstruktoriparametria ei pysty injektoimaan @Autowired-annotaatiolla. Ohje String-tyyppisen arvon injektointiin xml-konfiguraatiossa [täällä](http://www.roseindia.net/tutorial/spring/spring3/ioc/springconstructorargs.html)

## 8. muutaatiotestaus

Tarkastellaan tässä tehtävässä viikolla 1 täydentämiesi luokan <code>Varasto</code> testien hyvyyttä. 

Pelkkä koodirivien kattaminen testeillä ei riitä

On mahdollista kirjoittaa testejä jotka "koskevat" kaikkia koodirivejä mutta eivät testaa oikein mitään järkevää. Pelkän suuren kattavuuden tavoittelun lisäksi on siis tärkeä muistaa testata:

* normaali toimina
* poikkeuksellinen toiminta
* virhetilanteet

Erityinen huomio kannattaa kiinnittää ns. raja-arvoihin:

* toimiiko esim. varastosta ottaminen jos yritetään ottaa täsmälleen varastossa oleva määrä tavaraa?

Yksi tapa testien hyvyyden testaamiseen on mutaatiotestaus, josta oli hieman puhetta myös luennolla. Testaamme seuraavaksi testiemme hyvyyttä PIT-työkalun avulla.

Seuraavassa PIT:in sivulta (joka ei tällä hetkellä toimi! "webarchivesta":http://web.archive.org/web/20130124070410/http://pitest.org/ sivu onneksi löytyy) otettu selitys joka kertoo mistä mutaatiotestauksessa on kyse:

bq.  Mutation testing is conceptually quite simple. Faults (or mutations) are automatically seeded into your code, then your tests are run. If your tests fail then the mutation is killed, if your tests pass then the mutation lived.

bq. The quality of your tests can be gauged from the percentage of mutations killed.

bq. To put it another way – PIT runs your unit tests against automatically modified versions of your application code. When the application code changes, it should produce different results and cause the unit tests to fail. If a unit test does not fail in this situation, it may indicate an issue with the test suite.

Mutaatiotestaa varastosi suorittamalla komento
<code>mvn test org.pitest:pitest-maven:mutationCoverage</code>

Tulokset tulevat projektihakemistosi alihakemistoon target/pit-reports/aikaleima/index.html. Aikaleima on numerosarja joka kertoo suoritushetken, tyyliin 201211181742 (testi ajettu 18.11.2012 klo 1742). Avaa tulokset web-selaimella. Firefoxilla tämä tapahtuu komennolla open file. Voit myös avata selaimen terminaalissa menemällä ensin projektihakemistoon ja antamalla komento <code>chromium-browser target/pit-reports/aikaleima/index.html</code>. Kun ajat mutaatiotestit uudelleen, muista avat oikea raportti!

Voit poistaa vanhat raportit komennolla <code>mvn clean</code>

Raportista näet mitkä mutantit jäävät henkiin. Yritä parantaa testejäsi siten, että lausekattavuus säilyy ja mahdollisimman moni mutantti kuolee. *HUOM* osa mutanteista on ns. ekvivalentteja mutantteja ks. "http://en.wikipedia.org/wiki/Mutation_testing":http://en.wikipedia.org/wiki/Mutation_testing ja niitä on mahdotonta saada kuolemaan!

## tehtävien kirjaaminen palautetuksi

tehtävien kirjaus:

* Kirjaa tekemäsi tehtävät [tänne](http://ohtustats.herokuapp.com) 
  * **Palautus onnistuu vasta ma 24.3.**
  * huom: tehtävien palautuksen deadline on su 30.3. klo 23.59

palaute tehtävistä:

* Lisää viikon 1 tehtävässä 11 forkaamasi repositorion omalla nimelläsi olevaan hakemistoon tiedosto nimeltä viikko2
* tee viime viikon tehtävän tapaan pull-request
  * anna tehtävistä palautetta avautuvaan lomakkeeseen
  * huom: jos teeh tehtävät alkuviikosta, voi olla, että edellistä pull-requestiasi ei ole vielä ehditty hyväksyä ja et pääse vielä tekemään uutta requestia


