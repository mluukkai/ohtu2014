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

Jetty on keyvt HTTP-palvelin ja Servlettien ajoympäristö. Projektiin on konfiguroitu Jetty Maven-pluginiksi. Jos kaikki menee hyvin, on sovellus nyt käynnissä ja voit käyttää sitä web-selaimella osoitteesta __http://localhost:8080__ eli paikalliselta koneeltasi portista 8080.

Jos koneellasi on jo jotain muuta portissa 8080, voit konfiguroida sovelluksen käynnistymään johonkin muuhun porttiin esim. 9999:n seuraavasti:

``` java
mvn -D jetty.port=9999 jetty:run
```

SpringWebMVC:stä tällä kurssilla ei tarvitse ymmärtää. Kannattaa kuitenkin vilkaista tiedostoa __ohtu.OhtuController.java__, joka sisältää koodin joka hoitaa sovelluksen eri osoitteisiin tulevat kutsut. Kontrolleri käyttää __AuthenticationService__-luokkaa toteuttamaan kirjautumisen tarkastuksen ja uusien käyttäjien luomisen. Kontrolleri delegoi www-sivujen renderöinnin hakemiston __WebPages/WEB-INF-views__ alla olevien jsp-sivujen avulla.

Eli tutustu nyt sovelluksen rakenteeseen ja toiminnallisuuteen. Saat sammutettua sovelluksen painamalla ctrl+c tai ctrl+d konsolissa.

h2. 2. Selenium, eli web-selaimen simulointi ohjelmakoodista

Web-selaimen simulointi onnistuu mukavasti [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/) -kirjaston avulla. Edellisessä tehtävässä olevassa projektissa on luokassa __ohtu.Tester.java__ pääohjelma, jonka koodi on seuraava:

``` java
public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
 
        driver.get("http://localhost:8080");
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

Käynnistä sovellus edellisen tehtävän tapaan. Varmista selaimella että sovellus on päällä.

Aja Tester.java:ssa oleva ohjelma. Esim. NetBeansilla tämä onnistuu valitsemalla tiedoston nimen kohdalta oikealla hiiren napilla "Run file".

Katso mitä ohjelma tulostaa.

Tester-ohjelmassa luodaan alussa selainta simuloiva olio __WebDriver driver__. Tämän jälkeen "mennään" selaimella osoitteeseen __localhost:8080__ ja tulostetaan sivun lähdekoodi. Tämän jälkeen haetaan sivulta elementti, jossa on linkkiteksti __login__ eli

``` java
WebElement element = driver.findElement(By.linkText("login"));
```

Linkkielementtiä klikataan ja jälleen tulostetaan sivun lähdekoodi. Seuraavaksi etsitään sivulta jonne päädyttiin elementti, jonka nimi on __username__, kyseessä on lomakkeen input-kenttä, ja ohjelma "kirjoittaa" kenttään komennolla sendKeys() nimen "pekka".

Tämän jälkeen täytetään vielä salasanakenttä ja painetaan lomakkeessa olevaa nappia. Lopuksi tulostetaan vielä sivun lähdekoodi.

Ohjelma siis simuloi selaimen käyttöskenaarion, jossa kirjaudutaan sovellukseen.

Muuta koodia siten, että läpikäyt seuraavat skenaariot:

* epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
* epäonnistunut kirjautuminen: eiolemassaoleva käyttäjätunnus
* uuden käyttäjätunnuksen luominen
* uuden käyttäjätunnuksen luominen ja luodulla käyttäjätunnuksella kirjautuminen

*HUOM:* salasanan varmistuskentän (confirm password) nimi on __passwordConfirmation__

## 3. Web-sovelluksen testaaminen: easyB+Selenium

Pääsemme jälleen käyttämään viime viikolta tuttua [easyB:tä](https://github.com/mluukkai/ohtu2014/blob/master/web/easyb.md) . Hakemistosta Other Test Sources/easyb löytyy valmiina User Storyn *User can log in with valid username/password-combination* määrittelevä story. Yksi skenaarioista on valmiiksi mäpätty koodiin. Täydennä kaksi muuta skenaariota.


## 4. Web-sovelluksen testaaminen osa 2

Kuten viimeviikolta muistamme, toinen järjestelmän toimintaa määrittelevä User Story on *A new user account can be created if a proper unused username and a proper password are given*

Löydät tämän Storyn easyB-pohjan viimeviikon tehtävistä. Kopioi story projektiisi ja tee skenaarioista suoritettavia kirjottamalla niihin sopivaa seleniumin avulla (edellisen tehtävän tyyliin) sovellusta testaavaa koodia. Muista lisätä story-tiedostoon Seleniumin vaatimat importit!

*Huom1:* voit tehdä __Tester.java__:n tapaisen pääohjelman sisältävän luokan jos haluat/joudut debuggaamaan AuthenticationServiceä.

*Huom2: Uuden käyttäjän luomisen pohjalla käytettävään luokkaan __UserData__ on määritelty validoinnit käyttäjätunnuksen muodon ja salasanan oikeellisuuden tarkastamiseksi. Eli toisin kuin viime viikolla, ei AuthenticationServicen tarvitse suorittaa validointeja.

## 5.-10. tulossa


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
