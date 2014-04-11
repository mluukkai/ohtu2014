## Pelaajastatistiikkaa Java 8:lla

Muokataan hieman  [viikon 2 laskareissa]
(https://github.com/mluukkai/ohtu2014/tree/master/viikko2)
työn alla ollutta NHL-pelaajastatistiikka-ohjelmaa.

### forEach

Pystymme tulostamaan 10 parasta pistemiestä metodin <code>public List<Player> topScorers(int howMany)</code> avulla seuraavasti:

``` java
public static void main(String[] args) {
    Statistics stats = new Statistics();
    
    for (Player player : stats.topScorers(10)) {
        System.out.println(player);
    }
}
```

Java 8:ssa kokoelimille on lisätty metodi <code>forEach</code>, jonka avulla kokoelma on helppo käydä läpi. Metodille voidaan antaa parametriksi _lambdalauseke_ jota metodi kutsuu jokaiselle kokoelman alkiolle:

``` java
    Statistics stats = new Statistics();

    stats.topScorers(10).forEach(s->{
        System.out.println(s);
    });
```

Nyt parametrina on labdalauseke <code>s->{ System.out.println(s); }</code>. Lausekkeen parametrina on nuolen vasemmalla puolella oleva _s_. Nuolen oikealla puolella on lausekkeen koodilohko, joka tulostaa parametrina olevan pelaajan. Metodi <code>forEach</code> siis kutsuu jokaiselle kokoelman pelaajalle lambdalauseketta.

Lambdalauseke olisi voitu kirjoittaa myös kokonaan yhdelle riville. Tällöin koodilohkoa ei ole välttämätöntä laittaa aaltosulkeisiin:

``` java
    stats.topScorers(3).forEach(s->System.out.println(s) );
```

Teknisesti ottaen metodi <code>forEach</code> saa parametrikseen rajapinnan <code>Consumer<T></code> toteuttavan olion. Consumer on käytännössä luokka, joka toteuttaa metodin <code>void accept(T param)</code>. Consumer-rajapinnan toteuttavia olioita on helppo generoida edellä olevan esimerkin tapaan lambda-lausekkeiden avulla.

Java täydentääkin edellä määritellyn lambdalausekkeen anonyymiksi sisäluokaksi:

``` java
    stats.topScorers(10).forEach(new Consumer<Player>() {
        @Override
        public void accept(Player s) {
            System.out.println(s);
        }
    });
```

Java 8:ssa on mahdollista viitata myös luokkien yksittäisiin metodeihin. Metdodi joka ottaa parametrikseen merkkijonon ja on ei palauta mitään onkin tyyppiä <code>Consumer<String></code>

Voimmekin antaa metodille <code>forEach</code> parametriksi viittauksen metodiin. Java 8:ssa metodeihin viitataan syntaksilla <code>Luokka::metodi</code>. 

Voimmekin muuttaa parhaiden pistemiehin tulostuksen seuraavaan muotoon

``` java
    stats.topScorers(10).forEach(System.out::println);
```

Nyt <code>forEach</code> kutsuu metodia <code>System.out.println</code> jokainen pelaaja parametrinaan.

Tulostuskomento <code>System.out.println</code> on hieman ikävä kirjoittaa kokonaisuudessaan. Importataan <code>System.out</code> staattisesti jolloin pystymme viittaamaan olioon <code>System.out</code> suoraan kirjoittamalla koodiin <code>out</code>:

``` java
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics();

        stats.topScorers(3).forEach(out::println);
    }
    
}
```

Staattisen importtauksen jälkeen voimme siis tulostaa ruudulle helpommin, kirjoittamalla <code>out.println("tekstiä")</code>.

### filter

Luokan <code>Statistics</code> metodit toimivat hyvin samaan tyyliin, ne käyvät läpi pelaajien listan ja palauttavat joko yksittäisen tai useampia pelaajia metodin määrittelemästä kriteeristä riippuen. Jos lisäisimme luokalle samalla periaatteella muita hakutoiminnallisuuksia (esim. kaikkien yli 10 maalia tehneiden pelaajien lista), joutuisimme "copypasteamaan" pelaajat läpikäyvää koodia vielä useampiin metodeihin.

Parempi ratkaisu olisikin ohjelmoida luokalle geneerinen etsintämetodi joka saa parametrina etsinnän kriteerin. [Edelliseltä viikolta tutut](https://github.com/mluukkai/ohtu2014/blob/master/web/luento8.md#koodissa-olevan-ep%C3%A4triviaalin-copypasten-poistaminen-strategy-patternin-avulla-java-8a-hy%C3%B6dynt%C3%A4v%C3%A4-versio) Java 8:n oliovirrat eli streamit tarjoavat sopivan välineen erilaisten hakujen toteuttamiseen. 

Muutetaan ensin metodi <code>List<Player> team(String teamName)</code> käyttämään stream-apia:

``` java
    public List<Player> team(String teamName) {
        return players
                .stream()
                .filter(p->p.getTeam().contains(teamName))
                .collect(Collectors.toList());
    }
```

Sensijaan että pelaajien lista käytäisiin eksplisiittisesti läpi käsitelläänkin metodilla <code>stream</code> listasta saatavaa oliovirtaa. Virrasta filtteröidään ne jotka toteuttavat lambda-lausekkeella määritellyn ehdon. Tuloksena olevasta filtteröidystä streamista sitten "kerätään" oliot palautettavaksi listaksi. 

Metodi <code>filter</code> saa parametrikseen rajapinnan <code>Predicate<T></code> toteuttavan olion. Päätämmekin poistaa metodin <code>team</code> ja sensijaan lisätä luokalle geneerisemmän hakumetodin <code>find</code> joka etsintäehdon parametriksi:

``` java
    public List<Player> find(Predicate<Player> condition) {
        return players
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
```

Yleistetyn metodin avulla on nyt helppo tehdä mielivaltaisen monimutkaisia hakuja. Hakuehdon muodostaminen lambda-lausekkeiden avulla on suhteellisen helppoa:

``` java
    public static void main(String[] args) {
        Statistics stats = new Statistics();
        
        stats.find(p->p.getGoals()>20 && p.getAssists()>20).forEach(out::println);
    }        
```

Java 8:ssa rajapinnoilla voi olla oleutustoteutuksen omaavia metodeja. Rajapinnalla <code>Predicate</code> löytyykin mukavasti valmiiksi toteutetut metodit <code>and</code>, <code>or</code> ja <code>negate</code>. Näiden avulla on helppo muodostaa yksittäisten esim. lambda-lausekkeen avulla muodostettujen ehtojen avulla mielivaltaisen monimutkaisia ehtoja. Seuraavassa edellisen esimerkin tuloksen tuottava haku de Morganin lakia hyväksikäyttäen muodostettuna:

``` java
    Statistics stats = new Statistics();
    
    Predicate<Player> cond1 = p->p.getGoals()<=20;
    Predicate<Player> cond2 = p->p.getAssists()<=20;
    Predicate<Player> cond = cond1.or(cond2);
            
    stats.find(cond.negate()).forEach(out::println);
```

### järjestys

Metodin <code>topScorers(int howMany)</code> avulla on mahdollista tulostaa haluttu määrä pelaajia tehtyjen pisteiden mukaan järjestettynä. Metodi on hieman ikävä sillä palautettavat pelaajat on kerättävä yksi kerrallaan erilliselle listalle.

Metodista on helppo tehtä Java 8:a hyödyntävä versio:

``` java
    public List<Player> topScorers(int howMany) {
        return players
                .stream()
                .sorted()
                .limit(howMany)
                .collect(Collectors.toList());
    }
```

Eli otamme jälleen pelaajista muodostuvat streamin. Stream muutetaan luonnollisen järjestyksen (eli luokan <code>Player</code> metodin <code>compareTo</code> määrittelemään järjestyksen) mukaisesti järjestetyksi streamiksi metodilla <code>sorted</code>. Medodilla <code>limit</code> rajoitetaan streamin koko haluttuun määrään pelaajia joista muodostettu lista palautetaan. 

Jotta myös muunlaiset järjestykset olisivat mahdollisia, generalisoidaan metodi muotoon, joka ottaa parametriksi halutun järjestyksen määrittelevän <code>Comparator<Player></code>-rajapinnan määrittelevän olion:

``` java
    public List<Player> sorted(Comparator<Player> compare, int number) {
        return players
                .stream()
                .sorted(compare)
                .limit(number)
                .collect(Collectors.toList());
    }
```

Metodin tarvitsema vertailujaolio on helppo luoda lambda-lausekkeena:

``` java
    Comparator<Player> byPoints = (p1, p2)->p2.getPoints()-p1.getPoints();
    
    System.out.println("sorted by points");
    stats.sorted(byPoints, 10).forEach(out::println);
```

Comparator-olioiden luominen on hieman ikävää, varsinkin jos joutuisimme luomaan useiden eri kriteerien avulla tapahtuvia vertailijoita:

``` java
    Comparator<Player> byPoints = (p1, p2)->p2.getPoints()-p1.getPoints();
    Comparator<Player> byGoals = (p1, p2)->p2.getGoals()-p1.getGoals();
    Comparator<Player> byAssists = (p1, p2)->p2.getAssists()-p1.getAssists();
    Comparator<Player> byPEnalties = (p1, p2)->p2.getPenalties()-p1.getPenalties();
```

Koodi sisältää ikävästi copy-pastea. 

Voimme siistiä koodia Comparatoreja rakentavan tehdasmetodin avulla. Periaatteena on, että tehtaalle annetaan viitteenä metodi, jonka perusteella <code>Player</code>-olioiden vertailu tehdään. Esim. pisteiden perusteella tapahtuvan vertailun tekevä vertailija luotaisiin seuraavasti:

``` java
    Comparator<Player> byPoints = by(Player::getPoints);
```

Tehdasmetodin nimi on siis <code>by</code>. 

Koska <code>Player</code>-olioiden getterimetodit ovat parametrittomia ja palauttavat arvon kokonaislukuarvon, ne 
toteuttavat rajapinnan <code>Function<Player, Integer></code>. Tehtaan koodi on seuraavassa:

``` java
    public static Comparator<Player> by(Function<Player, Integer> getter){
        return (p1, p2)->getter.apply(p2)-getter.apply(p1);
    }
```

Tehtaan parametrina saaman getterimetodin kutsumistapa on hiukan erikoinen, esim. <code>getter.apply(p1)</code> siis tarkoittaa samaa kuin <code>p1.getPoints()</code> jos tehdasta on kutsuttu ylläolevalla tavalla.

Järjestäminen esim. maalien perusteella onnistuu nyt tehtaan avulla seuraavasti:

``` java
    Comparator<Player> byGoals = by(Player::getGoals);
    stats.sorted(byGoals, 10).forEach(out::println);
```

Vertailijaa ei ole oikeastaan edes tarvetta tallettaa muuttujaan sillä tehdasmetodi on nimetty siten, että sen kutsuminen on sujuvaa suoraan <code>sorted</code>-metodin parametrina:

``` java
    stats.sorted(by(Player::getGoals), 10).forEach(out::println);
```

Comparator-rajapinnalle on lisätty pari kätevää oletustoteutuksen omaavaa metodia <code>thenComparing</code> ja <code>reversed</code>. Ensimmäinen näistä mahdollistaa toissijaisen järjestämiskriteerin määrittelemisen erillisen vertailijan avulla. Eli jos ensin sovellettu vertailija ei erottele järjestettäviä oliota, sovelletaan niihin toissijaista vertailijaa. Metodi <code>reversed</code> toimii kuten nimi antaa olettaa, se muodostaa vertailijata käänteisesti toimivan vertailijan. 

Seuraavassa pelaajat listattuna ensisijaisesti tehtyjen maalien ja toissijaisesti syöttöjen "vähyyden" perusteella:

``` java
    Comparator<Player> order = by(Player::getPoints)
                               .thenComparing(by(Player::getAssists).reversed());

    stats.sorted(order, 20).forEach(out::println);
```

### numeerinen statsitiikka 

Haluaisimme laskea erilaisia numeerisia tilastoja pelaajista. Esim. yksittäisen joukkueen yhteenlasketun maalimäärän. 

Ensimmäinen yrityksemme on seuraava:

``` java
    int maalit = 0;
    stats.find(p->p.getTeam().equals("PHI")).forEach(p->{
        maalit += p.getGoals();
    });
    System.out.println(maalit);
```

Koodi ei kuitenkaan käänny. Syynä tälle on se, että lambda-lausekkeen sisältä ei pystytä muuttamaan metodin paikallista muuttujaa <code>maalit</code>. Asia korjaantuisi määrittelemällä muuttuja luokkatasolla. Emme kuitenkaan tee näin, vaan pyrimme jälleen hyödyntämään Java 8:n tarjoamia uusia ominaisuuksia.

Talletetaan ensin käsiteltävien olioiden stream muuttujaan:

``` java
    Stream<Player> playerStream = stats.find(p->p.getTeam().equals("PHI")).stream();
```

Streameille on määritelty metodi <code>map</code>, jonka avulla voimme muodostaa streamista uuden streamin jonka jokainen alkio on muodostettu alkuperäisen streamin alkiosta suorittamalla tälle metodin <code>map</code> parametrina määritelty metodi. 

Saamme muutettua pelaajien streamin pelaajien maalimääristä koostuvaksi streamiksi seuraavasti:

``` java
   playerStream.map(p->p.getGoals())
```

eli uusi streami muodostetaan siten, että jokaiselle alkuperäisen streamin alkiolle lambda-lauseke <code>p->p.getGoals()</code>.

Sama käyttäen metodireferenssejä olisi:

``` java
   playerStream.map(Player::getGoals)
```

Metodin <code>map</code> avulla muunnettu oliovirta voidaan tarvittaessa "kerätä" listaksi:

``` java
    List<Integer> maalit = playerStream.map(Player::getGoals).collect(Collectors.toList() );
```

Streamille voidaan myös suorittaa metodi <code>reduce</code>, joka "laskee" streamin alkioiden perusteella määritellyn arvon. Määritellään seuraavassa maalien summan laskeva operaatio:

``` java
    int s = playerStream.map(Player::getGoals).reduce((item1,item2)->item1+item2).get();
``` 

Metodi <code>reduce</code> saa parametrikseen lambda-lausekkeen joka saa ensimmäisellä kutsukerralla parametrikseen streamin 2 ensimmäistä alkiota. Nämä lasketaan yhteen ja toisella kutsukerralla lambda-lauseke saa parametrikseen streamin kolmannen alkion _ja_ edellisen reducen laskeman summan. Näin reduce "kuljettaa" mukanaan streamin alkioiden summaa ja kasvataa sitä aina seuraavalla vastaantulevalla alkiolla. Kun koko stream on käyty läpi, palauttaa reduce kaikkien alkioiden summan joka muutetaan kokonaislukutyyppiseksi metodin <code>get</code> avulla.



