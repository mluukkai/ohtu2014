# Laskari 1

## Huom: ohjausta tehtävien tekoon to klo 14-16 ja pe klo 14-16 salissa BK107

### Tehtävien palautuksen deadline su 16.3. klo 23.59

## Miten viikon 1 tehtävät palautetaan?

GitHubin, Jenkinsin ja lopussa olevan "palautuslomakkeen" avulla! Eli teet kaiken mitä alla sanotaan, se riittää, kenellekään ei tarvitse näyttää mitään.

Tämän viikon tehtävät ovat lähes luennoista riippumattomia, luentomateriaalista ei tehtäviin ole apua, kaikki tarvittava materiaali on linkitettetty tehtävien yhteyteen

## 1 Githubiin

Tee itsellesi tarvittaessa tunnus GitHubiin

* mene osoitteeseen https://github.com/plans
* valitse create free account

Luo repositorio nimellä ohtu-viikko1 

* klikkaa yläpalkin oikeassa reunassa olevaa  "Create a new repo"-ikonia 
* **laita rasti** kohtaan Initialize this repository with a README 

![kuva](https://github.com/mluukkai/ohtu2014/raw/master/images/viikko1-1.png)

Hae akateemista tunnusta, näin saat käyttöösi (ilmaiseksi) yksityisiä repositorioita

* https://github.com/edu
* HUOM: tämän viikon tehtävissä et vielä tarvitse akateemista tunnusta, ja tämän viikon repositorion *tulee* olla julkinen!

Luo paikalliselle koneellesi ssh-avain (tapahtuu komentoriviltä käsin)

* ks. ohje http://www.cs.helsinki.fi/group/kuje/compfac/ssh_avain.html

Lisää avaimen julkinen pari githubiin:

* https://github.com/settings/ssh

Näin pystyt käyttämään GitHubia ilman salasanan syöttämistä koneelta jossa juuri luodun avaimen salainen pari löytyy

Konfiguroi nimesi ja emailosoitteesi paikallisen koneesi git:iin antamalla komennot:

    git config --global user.name "Your Name"
    git config --global user.email my.address@gmail.com


Kloonaa nyt githubiin tehty repositorio **paikalliselle koneelle**. Tämä tapahtuu antamalla komentoriviltä komento

    git clone git@github.com:mluukkai/ohtu-viikko1.git

missä komennon <code>git clone</code> parametrina on repositorioosi sivulta selviävä 'clone URL' (huomaa, että formaatin on oltava SSH):

![kuva](https://github.com/mluukkai/ohtu2014/raw/master/images/viikko1-2.png)

Nyt paikalliselle koneellesi syntynyt hakemisto <code>ohtu-viikko1</code> joka on on githubissa olevan repositorion klooni.

## 2 Gitin alkeet

* Lue https://we.riseup.net/debian/git-development-howto ja http://www.ralfebert.de/tutorials/git/, molemmat kohtaan *Branching* asti ja samalla komentoriviltä kaikki dokumentin esimerkit. Koulun koneille git on jo asennettu, joten kohdan *Install git* voit skipata
  * Lisää git-ohjeita esim. [Pro Git -oppaassa](http://git-scm.com/book), kannattaa lukea näin alkuun luku 2
  * Hyviä ohjeita löydät myös [Githubin helpistä](https://help.github.com/articles/)
* git saattaa vaikuttaa aluksi sekavalta, pienen totuttelun jälkeen peruskäyttö on kuitenkin helppoa ja se nostaa elämäsi laatua merkittävästi

**tee seuraavat:**

* mene edellisessä tehtävässä luotuun repositorion klooniin (eli komennon <code>git clone</code> luomaan hakemiston)
* lisää ja committaa repositorioon kaksi tiedostoa ja hakemistoa, joiden sisällä on tiedostoja
  * muista hyödyllinen komento git status
* muuta ainakin kahden tiedoston sisältöä ja committaa muutokset repositorioon
* tee .gitignore-tiedosto, jossa määrittelet että repositorion juurihakemistossa olevat tiedostot joiden pääte on xxx ja hakemisto jonka nimi on target ignoroidaan
* lisää xxx-päätteisä tiedostoja repositorioon ja varmista että git jättää ne huomioimatta
* lisää myös hakemisto nimeltä _target_ ja hakemiston sisälle joku tiedosto, varmista että target sisältöineen ei mene versionhallinnan alaisuuteen
* tee muutos tiedostoon, älä lisää tiedostoa "staging"-alueelle
  * peru muutos (git status -komento antaa vihjeen miten tämä tapahtuu)
* tee muutos ja lisää tiedosto "staging"-alueelle
  * peru muutos (git status -komento antaa vihjeen miten tämä tapahtuu)

## 3 Tiedostojen lisääminen GitHubiin

Tehtävässä 1 tehtiin GitHubiin repostorio, joka liitettiin paikalliselle koneelle luotuun repositorioon "remote repositoryksi". Synkronoidaan paikallisen repositorion ja githubin tilanne:

* "pushaa" nämä GitHubissa olevaan etärepositorioon antamalla komento <code>git push</code>
* varmista selaimella että lisätyt tiedostot menevät GitHubiin

## 4 Monta kloonia samasta repositoriosta

Yleensä on tapana pitää GitHubissa olevaa repositorioa tiedostojen "keskitettynä" sijoituspaikkana ja liittää paikallisella koneella oleva repositorio GitHubissa olevan repositorion etärepositorioksi kuten teimme tehtävässä 1. 

Jos työskennellään useammalta koneelta, on githubissa olevasta repositoriosta monta kloonia ja kloonien tila on pidettävä ajantasalla.

Luodaan nyt paikalliselle koneelle repositoriosta toinen klooni:

* mene komentoriville ja esim. kotihakemistoosi  (tai johonkin paikkaan joka ei ole git-repositorio) 
* anna komento git clone git@github.com:githubtunnus/repositorionNimi.git nimiKloonille
  * githubtunnus ja repositorionNimi selviävät  GitHubista repositoriosi sivulta yllä olevan kuvan osoittamasta paikasta
  * *nimiKloonille* tulee olemaan kloonatun repositorion nimi, varmista että annat nimen jonka nimistä tiedostoa tai hakemistoa ei ole
* mene kloonattuun repositorioon, lisää sinne jotain tiedostoja ja committaa
* "pushaa" muutokset GitHubiin
* varmista selaimella että lisätyt tiedostot menevät GitHubiin

**Mene nyt tehtävässä 1 tehtyyn GitHub-repositorion klooniin.**

* alkuperäinen paikallinen klooni ei ole enää ajantasalla, "pullaa" sinne muutokset komennolla <code>git pull</code>
* varmista että molempien paikallisten repositorioiden sisältö on nyt sama
* lisää alkuperäiseen kopioon joitain tiedostoja ja pushaa ne GitHubiin
* mene jälleen kloonattuun kopioon ja pullaa



* hae osoitteesta https://github.com/mluukkai/ohtu2014/blob/master/viikko1/OhtuVarasto.zip?raw=true löytyvä zipattu paketti, pura se kloonattuun repositorioon
* lisää .gitignore-tiedostoosi hakemisto _OhtuVarasto/target/_
  * huom: hakemistoa ei vielä ole olemassa
* lisää ja committoi zipistä purettu hakemisto repositorioon ja pushaa se GitHubiin
* katso vielä kerran selaimella että GitHubissa kaikki on ajan tasalla

Nyt voit poistaa toisen paikallisista kopioista

## 5 Maven

* lue ensin https://www.ibm.com/developerworks/java/tutorials/j-mavenv2/ kohtaan _working with multiple projects_ asti
  * myös tämä voi olla hyödyksi http://docs.codehaus.org/display/MAVENUSER/The+Maven+2+tutorial  
  * vielä syventävämpää tietoa http://www.sonatype.com/books/mvnref-book/reference/

* edellisessä tehtävässä lisättiin repositorioon maven-muodossa oleva projekti, joka sisältää aikoinaan [ohjelmoinnin perusteissa](http://www.cs.helsinki.fi/u/wikla/ohjelmointi/materiaali/02_oliot/#15) olleen luokan <code>Varasto</code>, sen käyttöä demonstroivan pääohjelman ja muutaman JUnit-testin
* tutki maven-muotoisen projektin hakemistorakennetta, esim. antamalla komento <code>tree</code> projektin sisältävän hakeiston juuressa (tree ei ole maveniin liittyvä käsky vaan normaali shell-komento)
  * HUOM: macissa ei ole oletusarvoisesti tree-komentoa
  * mikäli koneellasi on [HomeBrew](http://mxcl.github.com/homebrew/ asennettuna), saat tree:n asennettua <code>brew install tree</code>
  * vaihtoehtoisesti saat treetä vastaavan toiminnallisuuden macissa komennolla <code>find .  ! -regex './\..'  -print | sed -e 's;[^/]*/;|__;g;s;__|; |;g'</code>
  * myöskään kaikissa linuxeissa ei tree ole oletusarvoisesti asennettu. debian-pohjaisissa linuxeissa (esim ubuntussa) saat asennettua tree:n komennolla <code>sudo apt-get install tree</code>
* tarkastele projektin määrittelevän pom.xml-tiedoston sisältöä

**avaa edellisen tehtävän projekti suosikki-idelläsi**
 
*  NetBeans ja IntellijIdea tukevat maven-muotoisia projekteja suoraan
* jos NetBeans ei tunnista projektia, asenna maven-plugin valitsemalla tools -> plugins -> available plugins
* HUOM: jos et ole aiemmin kääntänyt koneellasi maven-muotoisia projekteja, saattaa IDE valittaa tässä vaiheessa joidenkin kirjastojen (mm. JUnit) puuttumisesta, asia korjaantuu "buildaamalla" tai kääntämällä projekti komentoriviltä (ohjeet alla) 
* Ohjeita Eclipse-käyttäjille http://maven.apache.org/eclipse-plugin.html  

Ohjelmakoodin editointi kannattaa tehdä IDE:llä, ja välillä myös ohjelman ja testien ajaminenkin. **mavenia kannattaa kuitenkin ehdottomasti totutella käyttämään myös komentoriviltä.** 

**Kokeile seuraavia:**
 
* aloita puhtaalta pöydältä: <code>mvn clean</code>
* tee juuressa komento <code>tree</code>
* käännä: <code>mvn compile</code>
  * tee jälleen juuressa komento tree, mitä muutoksia huomaat?
  * huom: projekti olettaa että koneellasi on javan jdk:sta vähintään versio 1.7, jos koneellasi on venhempi versio, asenna uudempi jdk
* käännöksen jälkeen voit suorittaa pääohjelman komennolla <code>mvn exec:java -Dexec.mainClass=ohtu.ohtuvarasto.Main</code>
  * parametrina siis main-metodin sisältävän luokan nimi
* tee <code>mvn clean</code> ja yritä suorittaa ohjelma uudelleen, miten käy?
  * suorita cleanin jälkeen tree-komento, mitä clean tekee?
* aja testit komennolla <code>mvn test</code>
  * suorita jälleen komento <code>tree</code>
  * huomaat, että testien ajaminen luo hakemiston tree/surefire-reports, testien diagnostiikka tulee hakemistoon
* tee projektista jar-tiedosto: <code>mvn install</code>
  *  komennolla tree näet minne hakemistoon jar tulee
* suorita jar komennolla <code>java -cp tiedostonNimi.jar ohtu.ohtuvarasto.Main</code>
  * komento siis annetaan hakemistosta, jossa jar-tiedosto sijaitsee 

**HUOM** mavenin versiolla 3 komennolla <code>mvn install</code> tehty jar ei välttämättä toimi. Jos näin käy, tee seuraavasti:

* lisää projektissa olevaan _pom.xml_-tiedostoon _plugins_-tägien sisään seuraava:

```html
       <plugins>
            //
            // älä koske olemassaoleviin plugineihin!
            //            

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
```

* generoi jar komennolla <code>mvn assembly:assembly</code>

## 6. JUnit

* kertaa JUnitin perusteet osoitteesta https://github.com/mluukkai/OTM2013/wiki/Ohje-JUnit:in-k%C3%A4ytt%C3%B6%C3%B6n
* täydennä tehtävässä 4 repositorioosi lisäämäsi projektin testejä siten että luokan Varasto rivikattavuudeksi (line coverage) tulee 100%
  * Joudut huomioimaan ainakin tapaukset, joissa varastoon yritetään laittaa liikaa tavaraa ja varastosta yritetään ottaa enemmän kuin siellä on
  * edellinenkään ei vielä riitä
* testauskattavuuden saat selville seuraavasti:
  * käytetään mavenissa pluginina olevaa [cobertura](http://cobertura.sourceforge.net/)-nimistä koodikattavuustyökalua
  * suorita projektin juuresta komento <code>mvn cobertura:cobertura</code>
    * Jos törmäät virheeseen _Could not find artifact com.sun:tools:jar:0 at specified path /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/../lib/tools.jar_, asenna Java jdk 7 ja määrittele <code>JAVA_PATH</code>-ympäristömuuttuja. Ohje ympäristömuuttujan asettamiseen [OSX:llä](http://www.mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/)
  * kattavuusraportit tulevat hakemistoon target/site/cobertura
  * saat avattua raportin esim. komennolla <code>firefox target/site/cobertura/index.html</code>
* kun luokan <code>Varasto</code> testien rivikattavuus (line coverage) on 100% pushaa tekemäsi muutokset GitHubiin

## 7. Jenkins, osa 1

Alkuvalmistelut:

* Suomen johtava IaaS-palvelu [UpCloud](http://fi.upcloud.com/) on tarjonnut kurssin käyttöön palvelimen Jenkinsin hostaamista varten, palvelin on osoittessa http://ohtu.jamo.io/
* luo käyttäjätunnus Jenkinsiin
  * sign up oikeassa yläkulmassa
* Downloadaa Jenkins Guide osoitteesta http://www.wakaleo.com/books/jenkins-the-definitive-guide
  * jos et halua täyttää kaikkia lomakkeita, löytyy myös paikallinen kopio [intranetissä](https://www.cs.helsinki.fi/i/mluukkai/jenkins-the-definitive-guide.pdf)
  * tarkempia ohjeita allaoleviin tehtäviin on Jenkins Guidessa, sivuilla 21-40 tehdään periaatteessa lähes sama kuin alla

Luodaan ensimmäinen "build job" ja määritellään se kääntämään ja testaamaan edellisen tehtävän "OhtuVarasto"

* Varmista ensin, että Jenkins on sivun autorefreshaavassa moodissa:

![kuva](https://github.com/mluukkai/ohtu2014/raw/master/images/viikko1-4.png)

* valitse *new item* ja *free style software project*
  * nimeä projekti muodossa **<käyttäjätunnus>-viikko1**
* täytä lomakkeesta kohdat:
  * laita rasti kohtaan *discard old build* ja merkkaa avautuvaan laatikkoon esim 10
  * kohtaan *GitHub project* projetkin GitHub-repositorion osoite
* Source Code Management
  * valitse *git*
  * laita kohtaan *Repository URL* GitHubista projektin kohdalta löytyvä *HTTPS clone URL* (**Katso ao. kuva**)

![kuva](https://github.com/mluukkai/ohtu2014/raw/master/images/viikko1-3.png)

* Build
  * *add build step -> invoke top-level maven targets*
  * kohtaan *goals* laitetaan halutut maven-targetit, tällä kertaa <code>clean test</code>
  * klikkaa *advanced* ja määrittele kohtaan *POM* tiedoston pom.xml sijainti projektissasi eli <code>OhtuVarasto/pom.xml</code>
  * tämän jälkeen voit sanoa *save*
* peruskonfiguraatio on valmis, klikkaa *build now*
* jos kaikki on hyvin, tulee build job -kohtaan sininen pallo
  * klikkaa palloa katso mitä *console output*:ista löytyy
  * console output on tärkeä paikka jos kaikki ei mene odotusten mukaisesti

**Lisää github-repositoriosi readme.MD-tiedostoon linkki projektisi Jenkins-sivulle!**

## 8. Jenkins, osa 2

jatketaan kokeiluja

* riko projektistasi joku testi, pushaa se githubiin ja tee uudelleen *build now*
  * huomioi miten Jenkins raportoi tilanteen, katso myös *console outputia* rikki menneestä buildista
* testitulosten parempi raportointi
  * valitse *configure* ja laita rasti *post build actions*:in alta löytyvään kohtaan *Publish JUnit test result report*
  * määrittele testiraporttiesi sijainti, se on todennäköisesti seuraava __OhtuVarasto/target/surefire-reports/TEST-ohtu.ohtuvarasto.VarastoTest.xml__, varmista komennolla <code>tree</code>
  * talleta ja buildaa taas
  * tarkista punaisen pallon takaa löytyvästä kohdasta *Test results* miten Jenkins raportoi testituloksen
* buildaa projektisi vielä kerran
  * mene build job:in pääsivulle ja refreshaa selain. testien läpimenostatistiikan pitäisi nyt näkyä etusivulla 
* korjaa rikottu testi, commitoi koodi ja suorita jenkins build uudelleen  
  
  
__varmista, että ennen seuraavaa vaihetta testisi menevät läpi!__

* konfiguroi Jenkins myös näyttämään testikattavuus 
  * toimi Jenkins Guiden sivujen 34-39 ohjeiden mukaan
  * huom1: plugin on Jenkinsissä valmiiksi asennettuna
  * huom2: build goaliksi testikattavuuden osalta pitää laittaa <code>cobertura:cobertura -Dcobertura.report.format=xml</code>
  * kohtaan "Cobertura xml report pattern" voit laittaa <code>OhtuVarasto/target/site/cobertura/coverage.xml</code>
  * huom3: jos määrittelet erillisen build-stepin, muista määritellä pom.xml-tiedoston sijainti!
  
## 9 Jenkins, osa 3  
  
Konfiguroi Jenkins näyttämään myös JavaDoc

* kokeile javadoc:ien generointia paikallisella koneella antamalla komento <code>mvn javadoc:javadoc</code>
* etsi tree-komennon avulla generoitujen dokumenttien sijaintipaikka ja avaa JavaDoc selaimella
* laita JavaDoc:in generointi Jenkinsiin build targetiksi 
* Valitse *Post build -actions*:in alta *Publish Javadoc*, laita javadoc-directoryksi *OhtuVarasto/target/site/apidocs* report
* kun suoritat seuraavan kerran jenkins-buildin, tulee koodista generoitu javadoc projektin sivulle, huonosti nimetyn linkin *Document* taakse
  
Konfiguroi Jenkins vielä generoimaan projektistasi jar-tiedosto

* lisää *build goal*:iksi *install* (tämä tekee oikeastaan test-goalin tarpeettomaksi sillä install:ia ennen tehdään aina test)
* Valitse *Post build -actions*:sta kohta  *Archive the artifacts*
  * kohtaan *files to archive tulee* generoidun jar-tiedoston polku. Selvitä se tekemällä <code>mvn install</code> paikallisesti
  
Tutki mitä kaikkea Jenkins-projektissasi nyt on
* mikä on Workspace:n sisältö?

## 10 Jenkins, osa 4

Automatisoidaan vielä buildaus siten, että Jenkins tekee kaikki konfiguroidut toimenpiteet automaattisesti kun GitHubissa olevaan koodiin tulee muutos

Huonompi tapa hoitaa asia on laittaa Jenkins pollaamaan repositorioa määräajoin, kokeillaan ensin tätä
* valitse *configure* ja laita rasti kohtaan *poll scm*
* avautuvaan schedule-laatikkoon määritellään cron-formaatissa miten usein repositorioa pollataan
* määrittele että pollaus tapahtuu kerran minuutissa
* riko ohjelmasta joku testi, pushaa se githubiin ja varmista että Jenkins huomaa muutoksen
* korjaa testi ja varmista että Jenkins reagoi taas

Parempi tapa on määritellä GitHub kertomaan Jenkinssille aina kun projektiin tulee muutoksia, eli määritellä GitHubiin Post Update hook
* poista rasti kohdasta *poll scm*
* valitse *Trigger builds remotely*
* anna kohtaan *authentication token* joku merkkijono
* tämän jälkeen voit triggeröidä käänöksen urlista __<jenkinsprojektisi-url>/build?token=TOKEN_NAME__
* kokeile komentoriviltä että tämä onnistuu, voit käyttää esim, curl-komentoa tyyliin: <code>curl http://ohtu.jamo.io/job/kayttajatunnuksesi-otm-viikko1/build?token=maarittelemasi-token</code>
* mene selaimella GitHub-projektiisi ja klikkaa oikealta *settings*
* valitse *Webhooks and services* ja *Add webhook*
* lisää tähän Jenkins-projektisi buildin triggeröivä url kohtaan *payload url*
* tee muutos projektiisi ja varmista että Jenkins reagoi

## 11. Forkaa repositorio https://github.com/mluukkai/ohtu2014

* forkkaaminen tapahtuu seuraavasti:
  * kun olet kirjautuneena GitHubiin, mene yo. osoitteeseen
  * paina oikeassa yläkulmassa olevaa nappia "fork"
* saat näin oman "forkatun" kopion repositoriosta ohtu2014
* kloonaa forkattu repositorio paikalliselle koneellesi
* lisää repositorioon hakemisto jonka nimi on muotoa SukunimiEtunimi
  * eli esim. oma hakemistoni olisi LuukkainenMatti
* lisää hakemiston sisälle tiedosto nimeltä viikko1
* pushaa muutokset githubiin
* tee forkkaamastasi repositiosta "pull request"
  * mene selaimella forkkaamaasi repositorioon
  * paina oikeassa kulmassa olevaa nappia "pull request"
  * anna tehtävistä palautetta avautuvaan lomakkeeseen

## tehtävien kirjaaminen palautetuksi

** Jos olet tehnyt Jenkins-tehtävät, muista että github-repositoriosi readme.MD-tiedostossa tulee olla linkki projektisi Jenkins-sivulle!**

* Kirjaa tekemäsi tehtävät [tänne](http://ohtustats.herokuapp.com) 
  * huom: tehtävien palautuksen deadline on su 16.3. klo 23.59
