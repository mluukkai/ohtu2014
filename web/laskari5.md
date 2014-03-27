# Laskari 5 UNDER CONSTRUCTION!

## 1. lisää mavenia: pom.xml

Maven-projekti konfiguroidaan projektin juuressa olevassa pom.xml-tiedostossa.

Tutkitaan hieman viime viikon tehtävissä 7-11 käytetyn projektin eli repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa __viikko4/LoginWeb2__ olevan projektin pom.xml:in sisältöä.

``` java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
 
    <groupId>com.mycompany</groupId>
    <artifactId>LoginWeb2</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <name>LoginWeb2</name>
 
    <properties>
        <spring.version>3.0.5.RELEASE</spring.version>
        <easyb.version>1.5</easyb.version>
        <slf4j.version>1.6.1</slf4j.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <repositories>
        <repository>
            <id>JBoss Repo</id>
            <url>https://repository.jboss.org/nexus/content/repositories/releases</url>
            <name>JBoss Repo</name>
        </repository>
    </repositories>
 
    <dependencies>
        <dependency>
            <groupId>org.easyb</groupId>
            <artifactId>easyb-core</artifactId>
            <version>${easyb.version}</version>
            <scope>test</scope>
        </dependency>
 
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit-dep</artifactId>
            <version>4.10</version>
            <scope>test</scope>
        </dependency>
 
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-all</artifactId>
            <version>1.1</version>
            <scope>test</scope>
        </dependency>
 
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>2.13.0</version>
            <scope>compile</scope>
        </dependency>
 
        <!-- loggaus -->
 
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.16</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
 
        <!-- servletit -->
 
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
 
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1</version>
            <scope>provided</scope>
            <classifier/>
        </dependency>
 
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
            <classifier/>
        </dependency>
 
        <!-- Spring -->
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
            <classifier/>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
 
        <!-- validointi -->
 
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>4.1.0.Final</version>
            <classifier/>
            <exclusions>
                <exclusion>
                    <groupId>javax.xml.bind</groupId>
                    <artifactId>jaxb-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.xml.bind</groupId>
                    <artifactId>jaxb-impl</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
 
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>1.0.0.GA</version>
            <classifier/>
        </dependency>
 
    </dependencies>
 
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.1.1</version>
            </plugin>
 
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
 
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.2.1</version>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
            </plugin>
 
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>2.6</version>
            </plugin>
 
            <plugin>
                <groupId>org.mortbay.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>7.4.2.v20110526</version>
                <configuration>
                    <stopKey>foo</stopKey>
                    <stopPort>9999</stopPort>
                    <webAppConfig>
                        <contextPath>/</contextPath>
                    </webAppConfig>
                </configuration>
 
                <executions>
                    <execution>
                        <id>start-jetty</id>
                        <phase>pre-integration-test</phase>
                        <goals>
                            <goal>run</goal>
                        </goals>
                        <configuration>
                            <scanIntervalSeconds>0</scanIntervalSeconds>
                            <daemon>true</daemon>
                        </configuration>
                    </execution>
 
                    <execution>
                        <id>stop-jetty</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>stop</goal>
                        </goals>
                    </execution>
 
                </executions>
            </plugin>
 
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

Alussa määritellään projektin tiedot (nimi, versio.)

Kohdassa *properties* määritellään mm. alempana käytettäviä vakioita

Maven osaa ladata riippuvuuksia (eli käytännössä jar-tiedostoja) automaattisesti oletusrepositorioista. Kaikki riippuvuudet eivät kuitenkaan löydy oletusrepositorioista ja tälläisiä tilanteita varten osaan *repositories* voi määritellä vaihtoehtoisia repositorioita joista maven voi etsiä riippuvuuksia.

Riippuvuudet määritellään osassa *dependencies*

* alussa olevien riippuvuuksien (mm. easyb, junit) scope on _test_, tämä tarkoittaa että ne ovat käytössä vain testeissä
* selenium-riippuvuuden scope on _compile_, tällöin selenium on käytössä testeissä ja normaalissa koodissa
* jos ohjelmassa tarvitaan jar:eja, tulee niitä vastaavat maven-riippuvuudet kirjata dependencies-osaan, riippuvuuksia voi etsiä mm. seuraavista: [http://search.maven.org](http://search.maven.org) tai [http://mvnrepository.com/](http://mvnrepository.com/)

Osassa *build* määritellään kääntämiseen liittyvien pluginien toimintaa

* kääntämisessä määritellään käytettävän javan versiota 1.6, tämä tapahtuu maven-compiler-plugin:ia konfiguroimalla
  * jos tätä konfiguraatiota ei tehdä käyttää compiler-plugin oletusarvoista javan versiota. maven 3:ssa se on 1.6 mutta maven 2.*:ssa versio 1.3
* jetty-pluginiin liittyy enemmänkin konfiguraatioita
  * kohdan __executions__-alla määritellään, että jetty (eli sovelluksen käyttämä maven-projektiin integroitu web-palvelin) käynnistetään vaiheessa __pre-integration-test__ ja sammutetaan vaiheessa __post-integration-test__, tämä saa aikaan sen, että kun ajetaan integraatiotestejä, eli suoritetaan komento <code>mvn integration-test</code>, on jetty päällä testien ajamisen aikana
* easyb-pluginin määritellään ajavan testit __integration-test__-vaiheessa

## 2. lisää mavenia: riippuvuuksien lisääminen

Hae repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa viikko5/TyhjaProjekti lähes tyhjän maven-projektin runko.

* mukana on kaksi kohta tarvitsemaasi luokkaa: __Palautus__ ja __Palautukset__:

Tehdään ohjelma jonka avulla voit lukea kurssilla palauttamiesi tehtävien statistiikan osoitteesta [http://ohtustats.herokuapp.com/](http://ohtustats.herokuapp.com/)

Omat palautukset palauttava sivu on __http://ohtustats.herokuapp.com/students/012345678/submissions__, missä __012345678__ siis opiskelijanumerosi. Palvelin palauttaa tietosi [json-muodossa](http://en.wikipedia.org/wiki/JSON)

Tavoitteena on tehdä ohjelma, joka ottaa komentoriviparametrina opiskelijanumeron ja tulostaa palautettujen tehtävien statistiikan ihmisystävällisessä muodossa.

Ohjelmassa tarvitaan muutamaa kirjastoa:

* HTTP-pyynnön tekemiseen [http://hc.apache.org/httpclient-3.x/](http://hc.apache.org/httpclient-3.x/)
* InputStreamin merkkijonoksi muuttamiseen [http://commons.apache.org/io/](http://commons.apache.org/io/)
* json-muotoisen merkkijonon muuttaminen olioksi [http://code.google.com/p/google-gson/](http://code.google.com/p/google-gson/)

Liitä projektisi pom.xml:n seuraavat riippuvuudet

* commons-httpclient, Commons IO, gson
* löydät riippuvuuksien tiedot seuraavista [http://search.maven.org](http://search.maven.org) tai [http://mvnrepository.com/](http://mvnrepository.com/)
* Ainakin seuraavat versiot on todettu yhteensopiviksi ja toimivaksi projektin koodin kanssa: commons-httpclient 3.1, Commons IO 2.0, gson 2.1

Ota mallia edellisen tehtävän projektista ja määrittele maven-compiler-plugin käyttämään javan versiota 1.6

Voit ottaa projektisi pohjaksi seuraavan tiedoston:

``` java

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats.herokuapp.com/students/"+studentNr+"/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream =  method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.println(submission);
        }

    }
}
```

*HUOM:* jos teet koodia NetBeansilla, kirjastoja ei ehkä tunnisteta ennenkiun teet clean and buildin ja NB lataa ne mavenin repositoriosta koneellesi.

Tehtäväpohjassa on valmiina luokan <code>Submission</code> koodin runko. Gson-kirjaston avulla json-muotoisesta datasta saadaan taulukollinen <code>Submission</code>-olioita, joissa jokainen olio vastaa yhden viikon palautusta. Tee luokkaan oliomuuttuja (sekä tarvittaessa getteri ja setteri) jokaiselle json-datassa olevalle kentälle, jota ohjelmasi tarvitsee. Kentät _a1_, _a2_ jne vastaavat viikolla tehtyjä yksittäisiä tehtäviä.

Tee kuitenkin ohjelmastasi tulostusasultaan miellyttävämpi, esim. seuraavaan tyyliin:

<pre>
opiskelijanumero 012345678

 viikko 1: tehtyjä tehtäviä yhteensä: 9, aikaa kului 3 tuntia, tehdyt tehtävät: 1 2 3 4 5 6 7 9 11 
 viikko 2: tehtyjä tehtäviä yhteensä: 6, aikaa kului 4 tuntia, tehdyt tehtävät: 1 2 3 6 7 8  

yhteensä: 15 tehtävää 7 tuntia
</pre>


## 3. lisää mavenia: jar joka sisältää kaikki riippuvuudet

* tehdään äskeisen tehtävän projektista jar-tiedosto komennolla <code>mvn install</code>
* suoritetaan ohjelma komennolla <code>java -cp tiedostonNimi.jar ohtu.Main</code>
* mutta ohjelma ei toimikaan, tulostuu:

``` java
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/commons/httpclient/HttpMethod
Caused by: java.lang.ClassNotFoundException: org.apache.commons.httpclient.HttpMethod
at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
at java.security.AccessController.doPrivileged(Native Method)
at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
Could not find the main class: ohtu.Main.  Program will exit.
```


Mistä on kyse?
* ohjelman riippuvuuksia eli projekteja commons-httpclient, Commons IO ja gson vastaavat jar-tiedostot eivät ole käytettävissä, joten ohjelma ei toimi
* saamme generoitua ohjelmasta jar-tiedoston joka sisältää myös riippuvuudet mavenin assembly-pluginin avulla
* lisää pom.xml:n plugineihin seuraava:


``` java
<build>
        <plugins>
 
            <!-- ... -->
 
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.2.1</version>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
            </plugin>
 
        </plugins>
 
    </build>
```

komennolla <code>mvn assembly:assembly</code> syntyy koko ohjelman sisältävä "standalone"-jar-tiedosto:

<pre>
$ java -cp TyhjaProjekti2-1.0-jar-with-dependencies.jar ohtu.Main 012345678
 
opiskelijanumero 012345678

 viikko 1: tehtyjä tehtäviä yhteensä: 9, aikaa kului 3 tuntia, tehdyt tehtävät: 1 2 3 4 5 6 7 9 11 
 viikko 2: tehtyjä tehtäviä yhteensä: 6, aikaa kului 4 tuntia, tehdyt tehtävät: 1 2 3 6 7 8  

yhteensä: 15 tehtävää 7 tuntia
</pre>


Riippuvuudet sisältävä jar-voidaan myös tehdä käyttämällä mavenin [shade-pluginia](http://maven.apache.org/plugins/maven-shade-plugin/) Shade-pluginin avulla saadaan itseasiassa aikaan "helppokäyttöisempi" jar, joka voidaan käynnistää määrittelemättä main-metodin sisältävää luokkaa.

Määrittele shade-pluginille mainClassin sijainti lisäämällä pom.xml:ääsi seuraava:

``` java
<build>
        <plugins>
 
            <!-- ... -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>1.6</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>ohtu.Main</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
 
    </build>
```

Saat luotua jar:in komennolla <code>mvn package</code>, ja ohjelman suoritus tapahtuu komennolla <code>java -jar tiedostonnimi.jar</code>


## 4. git: stash

Lue [http://git-scm.com/book/en/Git-Tools-Stashing](http://git-scm.com/book/en/Git-Tools-Stashing) kohtaan Un-applying a Stash asti.

Oletetaan että olet repositoriossa, jossa on ainakin kaksi branchia: master ja joku toinen (kutsutaan sitä tässä nimellä __toinen__).

* ollessasi master-branchissa tee muutoksia, joita lisäät staging-alueelle ja joitain muutoksia joita et vielä "äddää"
* pomosi käskee sinua välittömästi tekemään pari muutosta branchiin __toinen__. Et kuitenkaan halua vielä comittoida masterissa olevia muutoksia
* jos siirryt branchiin __toinen__ tekemättä comittia, tulee hirveä sotku, sillä muutokset pysyvät muutoksina toisessakin branchissa
* stashays pelastaa tästä tilanteesta, eli stashaa masterissa olevat muutoset
** kokeile ennen ja jälkeen stash-komennon komentoa <code>git status</code>
* siirry branchiin toinen, tee sinne joku muutos jonka committaat
* palaa jälleen masteriin
* palauta stashatyt muutokset komennolla <code>git stash apply</code>
  * varmista että muutokset palasivat
  * kuten huomaat, staging-alueelle jo lisätty muutos ei palaa staging-alueelle, vaan joudut lisäämään sen uudelleen
  * jos edellisessä komento olisi annettu muodossa <code>git stash apply --index</code>, olisi tilanne palautunut täysin ennalleen

## 5. git: vahingossa tuhotun tiedoston palautus

* viikon 4 [tehtävässä 6](https://github.com/mluukkai/ohtu2014/blob/master/web/laskari4.md#6-git-t%C3%A4git) palasimme jo menneisyyteen checkouttaamalla tagillä merkittyyn kohtaan
* katsotaan nyt miten voimme palauttaa jonkun menneisyydessä olevan tilanteen uudelleen voimaan
* tee tiedosto xxx, lisää ja committaa se
* poista tiedosto ja committaa
* tee jotain muutoksia johonkin tiedostoon ja committaa
* historiasi näyttää seuraavalta

<pre>
(1) - (2) - (3)
</pre>
    
* Nykyhetki eli HEAD on (3). Commitissa (1) tiedosto xxx on olemassa, nykyhetkellä ja (2):ssa xxx:ää ei ole.
  * huom: komennolla <code>gitk</code> voit tutkia historiaa
* haluamme palauttaa tiedoston
* selvitä sen commitin id, jossa tiedosto vielä on olemassa, tämä onnistuu gitk:lla tai <code>git log</code> -komennolla
* anna komento <code>git checkout 3290b03cea08af987ee7ea57bb98a4886b97efe0 -- xxx</code> missä pitkä merkkijono on siis kyseisen commitin id
  * varmista että tiedosto on ilmestynyt staging-alueelle komennolla <code>git status</code>
* tee commit
* xxx on palannut!
* HUOM: koko id:tä ei komennossa tarvitse antaa, riittää antaa alusta niin monta merkkiä, että niiden perusteella id voidaan päätellä yksikäsitteisesti repositoriosi historiassa

## 6. git: commitin muutosten kumoaminen, branchin "siirtäminen"

* huomaamme, että juuri tehty commit oli virhe, kumotaan se sanomalla <code>git revert HEAD --no-edit</code>
  * HEAD siis viittaa siihen committiin minkä kohdalla nyt ollaan
* syntyy uusi commit, jossa edellisessä tehdyt muutokset on kumottu
  * ilman optiota __no-edit__ pääset editoimaan kumoamiseen liittyvään commitiin tulevaa viestiä 
  * huom: sanomalla <code>git checkout HEAD^</code> pääsemme takaisin kumottuun tilanteeseen, eli mitään ei ole lopullisesti kadotettu
* vastaavalla tavalla voidaan revertata mikä tahansa commit eli: <code>git revert kumottavancommitinid</code>

## 7. git: branchin "siirtäminen"

* tee repoosi branchi nimeltä haara ja tee masteriin ja haaraan committeja siten että saat aikaan seuraavankaltaisen tilanteen:

<pre>
/------master
--
   \---haara
</pre>

* eli sekä master että haara ovat edenneet muutamien commitien verran haarautumisen tapahduttua
  * huom: komennolla <code>gitk --all</code> näet kaikki haarat, kokeile!
* yhtäkkiä huomaat, että master:iin tekemäsi asiat eivät olekaan kovin hyviä ja haara:ssa on paljon parempaa tavaraa, haluaisitkin että haara:sta tulisi uusi master
* tämä onnistuu kun menet masteriin ja annat komennon <code>git reset --hard haara</code>
  * varmista että komento toimii oikein
  * vanhan master-haarankaan tavarat eivät katoa mihinkään, jos niihin jostain syystä vielä halutaan palata

## 8. Git: rebase

Lue [http://git-scm.com/book/en/Git-Branching-Rebasing](http://git-scm.com/book/en/Git-Branching-Rebasing)

Aikaansaa seuraavankaltainen tilanne

<pre>
------- master
\
 \--- haara
</pre>

"rebeissaa" haara masteriin, eli aikaansaa seuraava tilanne:

<pre>
------- master
       \
        \--- haara
</pre>

Varmista komennolla <code>gitk --all</code> että tilanne on haluttu.

"mergeä" master vielä haaraan:

<pre>
-------
       \     master
        \--- haara
</pre>

Lopputuloksena pitäisi siis olla lineaarinen historia ja master sekä haara samassa. Varmista jälleen komennolla <code>gitk --all</code> että kaikki on kunnossa.

Poista branch haara. Etsi googlaamalla komento jolla saat tuhottua branchin.

Tämä oli kurssin viimeinen git-tehtävä. Muista pitää git-rutiiniasi yllä päivittäin/viikoittain. Jos olet tehnyt kurssin kaikki git-tehtävät, tulet saamaan kurssisuorituksen myös Versionhallinta-kurssista (1 op). Jos git-tehtäviä on jäänyt tekemättä, ota yhteyttä välittömästi jos haluat saada Ohtun lisäksi myös Versionhallinaopintopisteen.


# AO siirtyy viikolle 6 (dl tulee olemaan 2.5 tms)

## 9. Kyselykieli NHLStatistics-ohjelmaan

Repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistosta __viikko5/QueryLanguage__ löytyy jälleen yksi versio tutusta NHL-tilastoja lukevasta ohjelmasta.

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

## 10. Parannettu kyselykieli

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