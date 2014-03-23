# UNDER CONSTRUCTION !! Laskari 4 

## Huom: ohjausta tehtävien tekoon to klo 14-16 ja pe klo 14-16 salissa BK107

### Tehtävien palautuksen deadline su 6.4. klo 23.59

## palautetaan GitHubin kautta

* palautusta varten tarvitaan yksityinen repositorio, jolla collaboratorina käyttäjä mluukkai
  * kannattaa käyttää samaa repoa kuin viikon 2 ja 3 tehtävissä
* palautusrepositorion nimi ilmoitetaan tehtävien lopussa olevalla palautuslomakkeella

## 1. Spring WebMVC

Tarkastellaan edelliseltä viikolta tutun toiminnallisuuden tarjoamaa esimerkkiprojektia joka löytyy repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa __viikko4/LoginWeb2__

Hae projekti ja käynnistä se komennolla

``` java
mvn jetty:run
```

Jetty on keyvt HTTP-palvelin ja Servlettien ajoympäristö. Projektiin on konfiguroitu Jetty Maven-pluginiksi. Jos kaikki menee hyvin, on sovellus nyt käynnissä ja voit käyttää sitä web-selaimella osoitteesta [http://localhost:8080](http://localhost:8080) eli paikalliselta koneeltasi portista 8080.

Jos koneellasi on jo jotain muuta portissa 8090, voit konfiguroida sovelluksen käynnistymään johonkin muuhun porttiin esim. 9999:n seuraavasti:

``` java
mvn -D jetty.port=9999 jetty:run
```

SpringWebMVC:stä tällä kurssilla ei tarvitse ymmärtää. Kannattaa kuitenkin vilkaista tiedostoa __ohtu.OhtuController.java__, joka sisältää sovelluksen eri osoitteisiin tulevista kutsuista huolehtivan koodin. Kontrolleri käyttää __AuthenticationService__-luokkaa toteuttamaan kirjautumisen tarkastuksen ja uusien käyttäjien luomisen. Kontrolleri delegoi www-sivujen renderöinnin hakemiston __WebPages/WEB-INF-views__ alla oleville jsp-tiedostoille.

Eli tutustu nyt sovelluksen rakenteeseen ja toiminnallisuuteen. Saat sammutettua sovelluksen painamalla konsolissa ctrl+c tai ctrl+d.

## 2. Selenium, eli web-selaimen simulointi ohjelmakoodista

Web-selaimen simulointi onnistuu mukavasti [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/) -kirjaston avulla. Edellisessä tehtävässä olevassa projektissa on luokassa __ohtu.Tester.java__ pääohjelma, jonka koodi on seuraava:

``` java
public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
 
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
 
        System.out.println("==");
 
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
 
        System.out.println("==");
        System.out.println( driver.getPageSource() );
}
```

Käynnistä sovellus edellisen tehtävän tapaan komentoriviltä. Varmista selaimella että sovellus on päällä.

Aja Tester.java:ssa oleva ohjelma. Esim. NetBeansilla tämä onnistuu valitsemalla tiedoston nimen kohdalta oikealla hiiren napilla "Run file".

Katso mitä ohjelma tulostaa.

Tester-ohjelmassa luodaan alussa selainta simuloiva olio __WebDriver driver__. Tämän jälkeen "mennään" selaimella osoitteeseen __localhost:8080__ ja tulostetaan sivun lähdekoodi. Tämän jälkeen haetaan sivulta elementti, jossa on linkkiteksti __login__ eli

``` java
WebElement element = driver.findElement(By.linkText("login"));
```

Linkkielementtiä klikataan ja jälleen tulostetaan sivun lähdekoodi. Seuraavaksi etsitään sivulta elementti, jonka nimi on __username__, kyseessä on lomakkeen input-kenttä, ja ohjelma "kirjoittaa" kenttään komennolla sendKeys() nimen "pekka".

Tämän jälkeen täytetään vielä salasanakenttä ja painetaan lomakkeessa olevaa nappia. Lopuksi tulostetaan vielä sivun lähdekoodi.

Ohjelma siis simuloi selaimen käyttöskenaarion, jossa kirjaudutaan sovellukseen.

Muuta koodia siten, että läpikäyt seuraavat skenaariot:

* epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
* epäonnistunut kirjautuminen: eiolemassaoleva käyttäjätunnus
* uuden käyttäjätunnuksen luominen ja luodulla käyttäjätunnuksella kirjautuminen

*HUOM:* salasanan varmistuskentän (confirm password) nimi on __passwordConfirmation__

## 3. Web-sovelluksen testaaminen: easyB+Selenium

Pääsemme jälleen käyttämään viime viikolta tuttua [easyB:tä](https://github.com/mluukkai/ohtu2014/blob/master/web/easyb.md). Hakemistosta Other Test Sources/easyb löytyy valmiina User storyn *User can log in with valid username/password-combination* määrittelevä story. Yksi skenaarioista on valmiiksi mäpätty koodiin. Täydennä kaksi muuta skenaariota.

## 4. Web-sovelluksen testaaminen osa 2

Kuten viimeviikolta muistamme, toinen järjestelmän toimintaa määrittelevä User story on *A new user account can be created if a proper unused username and a proper password are given*

Löydät tämän Storyn easyB-pohjan viimeviikon tehtävistä. Kopioi story projektiisi ja tee skenaarioista suoritettavia kirjottamalla niihin Seleniumin avulla (edellisen tehtävän tyyliin) sovellusta testaavaa koodia. Muista lisätä story-tiedostoon Seleniumin vaatimat importit!

**Huomioita**
* voit (ja ehkä jopa kannattaa) tehdä __Tester.java__:n tapaisen pääohjelman sisältävän luokan jos haluat/joudut debuggaamaan testiä.
* Uuden käyttäjän luomisen pohjalla käytettävään luokkaan __UserData__ on määritelty validoinnit käyttäjätunnuksen muodon ja salasanan oikeellisuuden tarkastamiseksi. Eli toisin kuin viime viikolla, ei AuthenticationServicen tarvitse suorittaa validointeja.
* Skenaarion "can login with succesfully generated account" mäppäävän koodin kirjoittaminen ei ole täysin suoraviivaista. Koska luotu käyttäjä kirjautuu automaattisesti järjestelmään, joudut kirjaamaan käyttäjän ensin ulos ja kokeilemaan tämän jälkeen että luotu käyttäjä pystyy kirjautumaan sivulle uudelleen.
* Huomaa, että jos luot käyttäjän yhdessä testissä, et voi luoda toisessa testissä samannimistä käyttäjää uudelleen!

## 5. Yksikkötestaus ja riippuvuudet: Mockito, osa 1

Useimmilla luokilla on riippuvuuksia toisiin luokkiin. Esim. viikon 2 verkkokauppaesimerkin luokka Kauppa riippui Pankista, Varastosta ja Viitegeneraattorista. Riippuvuuksien injektoinnilla ja rajapinnoilla saimme mukavasti purettua riippuvuudet konreettisten luokkien väliltä.

Vaikka luokilla ei olisikaan riippuvuuksia toisiin konkreettisiin luokkiin, on tilanne edelleen se, että luokan oliot käyttävät joidenkin toisten luokkien olioiden palveluita. Tämä tekee joskus yksikkötestauksesta hankalaa. Miten esim. luokkaa Kauppa tulisi testata? Tuleeko Kaupan testeissä olla mukana toimivat versiot kaikista sen riippuvuuksista?

Olemme jo muutamaan otteeseen (esim. Nhl-Statsreader-tehtävässä viikolla 2) ratkaisseet asian ohjelmoimalla riippuvuuden korvaavan "tynkäkomponentin". Javalle (niinkuin kaikille muillekin kielille) on tarjolla myös valmiita kirjastoja tynkäkomponettien toiselta nimeltään "mock-olioiden" luomiseen.

Kuten pian huomaamme, mock-oliot eivät ole pelkkiä "tynkäolioita", mockien avulla voi myös varmistaa että testattava luokka kutsuu olioiden metodeja asiaankuuluvalla tavalla.

Tutustumme nyt [Mockito-nimiseen](http://code.google.com/p/mockito/) mock-kirjastoon. Muita vaihtoehtoja esim.

* [Easy Mock](http://www.easymock.org/)
* [jmock](http://www.jmock.org/)

Hae repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa __viikko4/MockitoDemo__ oleva projekti. Kyseessä on yksinkertaistettu versio Verkkokauppaesimerkistä.

Kaupan toimintaperiaate on yksinkertainen:

``` java
    Pankki myNetBank = new Pankki();
    Viitegeneraattori viitteet = new Viitegeneraattori();
    Kauppa kauppa = new Kauppa(myNetBank, viitteet);
        
    kauppa.aloitaOstokset();
    kauppa.lisaaOstos(5);
    kauppa.lisaaOstos(7);
    kauppa.maksa("1111");
``` 

Ostokset aloitetaan tekemällä metodikutsu <code>aloitaOstokset</code>. Tämän jälkeen "ostoskoriin" lisätään tuotteita joiden hinta kerrotaan metodin <code>lisaaOstos</code> parametrina. Ostokset lopetetaan kutsumalla metodia <code>maksa</code> joka saa parametrikseen tilinumeron jolta summa veloitetaan.

Kauppa tekee veloituksen käyttäen tuntemaansa luokan <code>Pankki</code> olioa. Viitenumerona käytetään luokan <code>Viitegeneraattori</code> generoimaa numeroa.

Projektiin on kirjoitettu 6 Mockitoa hyödyntävää testiä. Testit testaavat, että kauppa tekee ostoksiin liittyvän veloituksen oikein, eli että se kutsuu _pankin_ metodia <code>maksa</code> oikeilla parametreilla, ja että jokaiselle laskutukselle on kytsytty viitenumero _viitegeneraattorilta_. Testit siis eivät kohdistu olion pankki tilaan vaan sen muiden olioiden kanssa käymän interaktion oikeellisuuteen.
Testeissä kapuan riippuvuudet (Pankki ja Viitegeneraattori) on määritelty mock-olioina.

Seuraavassa testi, joka testaa, että kauppa kutsuu pankin metodia oikealla tilinumerolla ja summalla:

``` java
    @Test
    public void kutsutaanPankkiaOikeallaTilinumerollaJaSummalla() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        verify(mockPankki).maksa(eq("1111"), eq(10), anyInt());
    }
``` 

Testi siis aloittaa luomalla kaupan riippuvuuksista mock-oliot:

``` java
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        kauppa = new Kauppa(mockPankki, mockViite);
``` 

kyseessä siis eivät ole normaalit oliot vaan normaaleja olioita "matkivat" valeoliot, jotka myös pystyvät tarkastamaan että niiden metodeja on kutsuttu oikein parametrein. 

Testi tarkastaa, että kaupalle tehdyt metodikutsut aiheuttavat sen, että pankin mock-olion metodia <code>maksa</code> on kutsuttu oikeilla parametreilla. Kolmanteen parametriin eli tilinumeroon ei kiinnitetä huomiota:

``` java
    verify(mockPankki).maksa(eq("1111"), eq(10), anyInt());
``` 

Mock-olioille tehtyjen metodikutsujen paluuarvot on myös mahdollista määritellä. Seuraavassa määritellään, että viitegeneraattori palauttaa arvon 55 kun sen metodia <code>seruaava</code> kutsutaan:

``` java
    @Test
    public void kaytetaanMaksussaPalautettuaViiteta() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        
        // määritellään viitegeneraattorin metodikutsun vastaus
        when(mockViite.seruaava()).thenReturn(55);

        kauppa = new Kauppa(mockPankki, mockViite);

        kauppa.aloitaOstokset();
        kauppa.lisaaOstos(5);
        kauppa.lisaaOstos(5);
        kauppa.maksa("1111");

        verify(mockPankki).maksa(eq("1111"), eq(10), eq(55));
    }
``` 

Testin lopussa varmistetaan, että pankin mockolioa on kutsuttu oikeilla parametrinarvoilla, eli kolmantena parametrina tulee olla viitegeneraattorin palauttama arvo.

Tutustu projektiin ja sen kaikkin testeihin.

Mockiton dokumentaatio: [http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html](http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html)

## 6. Yksikkötestaus ja riippuvuudet: Mockito, osa 2

Hae repositorion [https://github.com/mluukkai/ohtu2014](https://github.com/mluukkai/ohtu2014) hakemistossa __viikko2/LyyrakorttiMockito__ oleva projekti. Kyseessä on yksinkertaistettu versio ohjelmoinnin perusteista tutusta tehtävästä Kassapääte ja tyhmä lyyrakortti.

Tässä tehtävässä on tarkoitus testata ja täydentää luokkaa <code>Kassapaate</code>. Lyrakorttin koodiin ei tehtävässä saa koskea ollenkaan! Testeissä ei myöskään ole tarkoitus luoda konkreettisia instansseja lyyrakortista, testien tarvitsemat kortit tulee luoda mockitolla.

Projektissa on valmiina kaksi testiä:

``` java
public class KassapaateTest {
    
    Kassapaate kassa;
    Lyyrakortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Lyyrakortti.class);
    }
    
    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);

        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);

        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }
}
``` 

Ensimmäisessä testissa varmistetaan, että jos kortilla on riittävästi rahaa, kassapäätteen metodin <code>ostaLounas</code> kutsuminen 
varmistaa kortin saldon _ja_ velottaa summan kortilta. 

Testi ottaa siis kantaa ainoastaan siihen miten kassapääte kutsuu lyyrakortin metodeja. Lyyrakortin saldoa ei erikseen tarkasteta, sillä oletuksena on, että lyyrakortin omat testit varmistavat kortin toiminnan.

Toinen testi varmisteta, että jos kortilla ei ole riittävästi rahaa, kassapäätteen metodin <code>ostaLounas</code> kutsuminen 
varmistaa kortin saldon mutta _ei_ velota kortilta rahaa.

Testit eivät mene läpi. Korjaa kassapäätteen metodi <code>ostaLounas</code>.

Tee tämän jälkeen samaa periaatetta noudattaen seuraavat testit:
* kassapäätteen metodin <code>lataa</code> kutsu lisää lyyrakortille ladattavan rahamäärän käyttäen kortin metodia <code>lataa</code> jos ladattava summa on positiivinen
* kassapäätteen metodin <code>lataa</code> kutsu ei tee lyyrakortille mitään jos ladattava summa on positiivinen

Korjaa kassapäätettä siten, että määrittelemäsi testit menevät läpi. 

## 7. Yksikkötestaus ja riippuvuudet: Mockito, osa 3

Testataan viikolta 2 tutun Verkkokaupan Kauppa-luokkaa

* Spring-versio löytyy [https://github.com/mluukkai/ohtu2013](https://github.com/mluukkai/ohtu2013) hakemistossa viikko2/Verkkokauppa3 (xml:llä konfiguroitu) ja viikko2/Verkkokauppa4 (annotaatioilla konfiguroitu)
* ota edellisistä jompi kumpi pohjaksi jos ei tehnyt tehtävää

Kaupalle injektoidaan konstruktorissa Pankki, Viitelaskuri ja Varasto.

Tehdään näistä testissä Mockitolla mockatut versiot.

Seuraavassa esimerkkinä testi, joka testaa, että ostostapahtuman jälkeen pankin metodia __tilisiirto__ on kutsuttu:

``` java
    @Test
    public void ostoksenPaaytyttyapankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);
        
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);
        
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
       
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());        
    }
```

Tee seuraavat testit:

* aloitataan asiointi, koriin lisätään koriin tuote jota varastossa on ja suoritetaan ostos (eli kutsutaan metodia kaupan __tilimaksu()__). varmistettava että kutsutaan pankin metodia __tilisiirto__ oikealla asiakkaalla, tilinumerolla ja summalla
* aloitataan asiointi, koriin lisätään koriin kaksi eri tuotetta joita varastossa on ja suoritetaan ostos. varmistettava että kutsutaan pankin metodia __tilisiirto__ oikealla asiakkaalla, tilinumerolla ja summalla
* aloitataan asiointi, koriin lisätään koriin kaksi samaa tuotetta jota on varastossa tarpeeksi ja suoritetaan ostos. varmistettava että kutsutaan pankin metodia __tilisiirto__ oikealla asiakkaalla, tilinumerolla ja summalla
* aloitataan asiointi, koriin lisätään koriin tuote jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos. varmistettava että kutsutaan pankin metodia __tilisiirto__ oikealla asiakkaalla, tilinumerolla ja summalla
* varmistettava että metodin aloita asiointi kutsuminen nollaa edellisen ostoksen tiedot
* varmistettava että kauppa pyytää uuden viitenumeron jokaiselle maksutapahtumalle

Kaikkien testien tarkastukset onnistuvat mockiton __verify__-komennolla.

Tarkasta vanhan ystävämme coberturan avulla mikä on luokan Kauppa testauskattavuus. Jotain taitaa puuttua. Lisää testi joka nostaa kattavuuden noin sataan prosenttiin!

Muista lisätä pom.xml-tiedoston riippuvuuksiin mockito:

``` java
<dependency>
     <groupId>org.mockito</groupId>
     <artifactId>mockito-all</artifactId>
     <version>1.9.0</version>
     <scope>test</scope>
</dependency>
```


Lisää testitiedostoosi import:

``` java
import static org.mockito.Mockito.*;
``` java

## tehtävien kirjaaminen palautetuksi

tehtävien kirjaus:

* Kirjaa tekemäsi tehtävät [tänne](http://ohtustats.herokuapp.com) 
  * **Palautus onnistuu vasta ma 31.3.**
  * huom: tehtävien palautuksen deadline on su 6.4. klo 23.59

palaute tehtävistä:

* Lisää viikon 1 tehtävässä 11 forkaamasi repositorion omalla nimelläsi olevaan hakemistoon tiedosto nimeltä viikko2
* tee viime viikon tehtävän tapaan pull-request
  * anna tehtävistä palautetta avautuvaan lomakkeeseen
  * huom: jos teeh tehtävät alkuviikosta, voi olla, että edellistä pull-requestiasi ei ole vielä ehditty hyväksyä ja et pääse vielä tekemään uutta requestia
