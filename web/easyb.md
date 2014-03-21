## easyB:n konfigurointi ja suorittaminen mavenin avulla

Tutustumme nyt tarkemmin keskiviikon luennolla käsiteltyyn [easyB-testauskehykseen](http://easyb.org)

Lue ensin [http://www.javaworld.com/javaworld/jw-09-2008/jw-09-easyb.html](http://www.javaworld.com/javaworld/jw-09-2008/jw-09-easyb.html)

* easyB:llä voidaan kirjoittaa kahdentyylisiä testejä, _spesifikaatioita_ ja _storyjä_
* artikkelin sivulla 2 esitellään spesifikaatioita (it should...), niitä emme ainakaan tässä laskarissa käytä
* sivun 3 storyt ja niitä testaavat _skenaariot_ (given... when... then) on käyttämämme tekniikka
* sivun 4 "Running easyB" voit skipata, ajamme easyB:tä mavenin kautta

Tarkastellaan esimerkkiprojektia joka löytyy repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa __viikko3/LoginEasyB1__

Tutustu olevan ohjelman rakenteeseen 
* käynnistä ohjelma <code>mvn exec ...</code> -komennolla (ks. [viikon 1 laskarit](https://github.com/mluukkai/ohtu2014/blob/master/web/laskari1.md#5-maven))
* ohjelman tuntemat komennot ovat __login__ ja __new__

Testit on kirjotettu hakemistoon _src/main/test/easyb_ tekstietiedostoina, joilla on pääte .story. NetBeansissa easyB-testitiedostot löytyvät kohdan _Other Test Sources_ alta.

Projektiimme on konfiguroitu easyB maven-pluginiksi. Konfigurointi suoritetaan pom.xml-tiedostossa (käy katsomassa pomia, sen sisältöön sinun ei tällä kertaa tarvitse koskea):

``` java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
 
    // paljon tavaraa
 
    <build>
        <plugins>
 
            // muita plugineja
 
            <plugin>
                <groupId>org.easyb</groupId>
                <artifactId>maven-easyb-plugin</artifactId>
                <version>1.4</version>
                <executions>
                    <execution>
                        <phase>integration-test</phase>
                        <goals>
                            <goal>test</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <storyType>html</storyType>
                    <storyReport>${basedir}/target/easyb/easyb-report.html
                    </storyReport>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Konfiguraatio kertoo pluginin nimen (groupId+artifactId) ja version. Sen lisäksi sanotaan, että plugin suoritetaan vaiheessa __integration-test__, eli kun annetaan komento <code>mvn integration-test</code>.

Voisimme myös konfiguroida easyB:n suoritettavaksi vaiheessa __test__, jolloin komento <code>mvn test</code> suorittaisin muiden testien lisäksi easyB-testit. Ideana on kuitenkin jakaa testit kahteen vaiheeseen: yksikkötesteihin, jotka suoritetaan aina ja User storyjen hyväksymätesteihin (ja integraatiotesteihin), joita ei välttämättä suoriteta aina ja jotka on tämän takia konfiguroitu eri testivaiheeseen kuin muut testit. Hyväksymä- ja integraatiotestit ovat joskus hitaita suorittaa, siksi ne halutaan erilliseen vaiheeseen yksikkötestien kanssa.

Kun suoritamme testit komennolla <code>mvn integration-test</code> tulee tulosteen sekaan easyB:n ajamisesta kertovaa tekstiä:

<pre>
[INFO] --- maven-easyb-plugin:1.4:test (default) @ LoginEasyBv1 ---
 
...
 
     [java] Running new_user_creation story (/home/mluukkai/ohtu-2012/koodi/luento4/LoginEasyBv1/src/test/easyb/new_user_creation.story)
     [java] Scenarios run: 7, Failures: 0, Pending: 5, Time elapsed: 1.203 sec
     [java] Running logging_in story (/home/mluukkai/ohtu-2012/koodi/luento4/LoginEasyBv1/src/test/easyb/logging_in.story)
     [java] Scenarios run: 3, Failures: 0, Pending: 2, Time elapsed: 0.139 sec
     [java] 10 total behaviors ran with no failures
 
...
 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 11.820s
[INFO] Finished at: Sun Mar 25 14:12:42 EEST 2012
[INFO] Final Memory: 8M/133M
[INFO] ------------------------------------------------------------------------
</pre>

Kaiken tekstin seasta nähdään, että easyB on ajanut kahteen User Storyyn liittyvät testit eli skenaariot. Virheitä ei ole mutta muutamat storyt ovat "Pending".

Jos testit eivät mene läpi, nähdään ikävämpää tekstiä.

easyB:n konfiguraatiossa sanoimme, että testien tulost tulee raportoida html-muodossa tiedostossa _target/easyb/easyb-report.html_

![kuva](https://github.com/mluukkai/ohtu2014/raw/master/images/viikko3-1.png)


Aja testit ja avaa tiedosto selaimellasi.

## User storyt

Ohjelmassamme on siis kaksi User Storyä:

* *User can log in with valid username/password-combination*, tiedostossa logging_in.story
* *A new user account can be created if a proper unused username and a proper password are given* tiedostossa new_user_creation.story

User Storyä vastaavan tiedoston sisältö on (ennen kuin skenaarioiden askelia on mäpätty ajettavaan koodiin) seuraavanlainen:

``` java
description 'User can log in with valid username/password-combination'
 
scenario "user can login with correct password", {
    given 'command login selected'
    when 'a valid username and password are entered'
    then 'user will be logged in to system'
}
 
scenario "user can not login with incorrect password", {
    given 'command login selected'
    when 'a valid username and incorrect password are entered'
    then 'user will not be logged in to system'
}
 
scenario "nonexistent user can not login to ", {
    given 'command login selected'
    when 'a nonexistent username and some password are entered'
    then 'user will not be logged in to system'
}
```

Alussa on  __description__ eli Storyn kuvaus. Kyseessä on suunilleen sama asia, joka kirjoitetaan Storyä vastaavaan pahvikorttiin jos sellaiset ovat käytössä. Kuvaus voidaan antaa myös narrative-muodossa (as a... I want... so that), kuten linkitetyssä artikkelissa tehtiin.

Kuvauksen jälkeen määritellään Storyn testit eli skenaariot. Skenaariot määritellään given... when... then -muotoisina testi-askelina:

* given kuvaa testin alkutilanteen
* when kuvaa toiminnon mitä testataan
* then kuvaa testin odotettua lopputulosta

Testin askeleet (eli given, when, then -osat) sisältävät asiakkaan terminologialla kirjoitetun merkkijonon, joka kertoo mistä askeleessa on kysymys.

Askel voi olla myös moniosainen, esim:

``` java
scenario "too many trials with wrong password lock the account", {
    given 'command login selected'
    and   'already five consecutive unsuccessfull login attempts for the username'
    when  'username and incorrect password are entered'
    then  'user will not be logged in to system'
    and   'account is locked'
}
```

and-osan voi liittää myös when-askeleeseen.

## Storyjen testien mäppääminen suoritettavaan koodiin

Kun ajamme testit ja skenaarioihin ei liity mäppäystä testikoodiin, ilmoitetaan testien olevan "pending"-tilassa, eli ei läpi mutta ei myöskään feilanneena.

Testit mäpätään ajettavaan koodiin kirjoittamalla kunkin skenaarion askeleen kohdalle lohko, johon tulee askelta vastaava testikoodi. Testikoodi kirjoitetaan Groovy-kielellä. [Groovyn](http://groovy.codehaus.org/) voi ajatella olevan "laiskasti kirjoitettua Javaa", eli esim puolipisteitä ei tarvita, muuttujien tyyppejä ei määritellä jne.

Seuraavassa esimerkki skenaarion __"user can login with correct password"__ mäppäämisestä ajettavaan koodin:

``` java
import ohtu.*
import ohtu.services.*
import ohtu.data_access.*
import ohtu.domain.*
import ohtu.io.*
 
description 'User can log in with valid username/password-combination'
 
scenario "user can login with correct password", {
    given 'command login selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("login", "pekka", "akkep")
       app = new App(io, auth)
    }
 
    when 'a valid username and password are entered', {
       app.run()
    }
 
    then 'user will be logged in to system', {
       io.getPrints().shouldHave("logged in")
    }
}
 
//...
```

Viimeiseen askel eli _then_ varmistaa, että järjestelmä toimii halutulla tavalla. Eli kun käyttäjä antaa syötteet _login<enter>pekka<enter>akkep<enter>_ vastaa järjestelmä tulostamalla _logged in_

Jos et jo niin tehnyt, kokeile ajaa ohjelmaa komentoriviltä ja tutustu sen rakenteeseen.
* muistutus viikolta 1, maven-muotoisen ohjelman suoritus tapahtuu komennolla <code>mvn exec:java -Dexec.mainClass=ohtu.App</code>
* jos olet muuttanut koodia, tulee se myös kääntää, eli antaa komento  <code>mvn compile exec:java -Dexec.mainClass=ohtu.App</code>

Kuten huomaat, ohjelmassa hyödynnetään Dependency Injection:ia. Testattavuuden helpottamiseksi kommunikointi käyttäjän kanssa tehdään rajapinnan IO-kautta. Rajapinnalle on tehty kaksi toteutusta: ConsoleIO normaalikäyttöön ja StubIO testeille. StubIO:lle annetean konstruktorissa syöterivit jotka "käyttäjä" ohjelmalle syöttää. StubIO-oliolta saa pyydettyä listan ohjelman tulosteista metodilla getPrints().

## shouldHave ja muut toiminnallisuuden varmistavat komennot

Yllä toiminnallisuus varmistettiin shouldHave-muotoisella komennolla:

``` java
then 'user will be logged in to system', {
   io.getPrints().shouldHave("logged in")
}
```

testiä voisi vielä lujittaa varmistamalla, että virheellistä kirjautumista ilmaisevaa tulostusta ei tule:

``` java
then 'user will be logged in to system', {
   io.getPrints().shouldHave("logged in")
   io.getPrints().shouldNotHave("wrong username or password")
}
```

Komennoilla shoudHave ja shouldNotHave  testataan listan sisältöä. Yksittäistä arvoa voidaan testata esim. seuraavasti:

``` java
   tulosteita = io.getPrints().length()
 
   tulosteita.shouldBeAn Integer
   tulosteita.shouldEqual 3
   tulosteita.shouldBeGreaterThan 1
```

Lisää should:ista löytyy easyB:n sivulta, mm. [http://www.easyb.org/dsls.html](http://www.easyb.org/dsls.html)

## DAO-suunnittelumalli

AuthenticationService-olio ei talleta suoraan User-oliota vaan epäsuorasti UserDAO-rajapinnan kautta. Mistä on kysymys?

DAO eli Data Access Object on yleisesti käytetty suunnittelumalli jonka avulla abstrahoidaan sovellukselta se miten oliot on talletettu, ks. [http://www.corej2eepatterns.com/Patterns2ndEd/DataAccessObject.htm](http://www.corej2eepatterns.com/Patterns2ndEd/DataAccessObject.htm)

Ideana on, että sovellus "hakee" ja "tallettaa" User-oliot aina UserDAO-rajapinnan metodeja käyttäen. Sovellukselle on injektoitu konkreettinen toteutus, joka tallettaa oliot esim. tietokantaan tai tiedostoon. Se minne talletus tapahtuu on kuitenkin läpinäkyvää sovelluksen muden osien kannalta.

Ohjelmaamme on määritelty testauskäyttön sopiva InMemoryUserDao, joka tallettaa User-oliot ainoastaan muistiin. Muu ohjelma säilyisi täysin muuttumattomana jos määriteltäisiin esim. MySQLUserDao joka hoitaa talletuksen tietokantaan ja injektoitaisiin tämä sovellukselle.

DAO-suunnittelumalli on oikeastaan sama asia mistä jotkut käyttävät nimitystä [data mapper](http://martinfowler.com/eaaCatalog/dataMapper.html)

## Linkkejä
  
* [http://www.easyb.org](http://www.easyb.org)
* melko perusteellinen esimerkki [täällä](http://www.ibm.com/developerworks/java/tutorials/j-easyb/)
