
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author Santeri
 */

@Component
public class FileUserDAO implements UserDao {
    
    private File file;
    private Scanner skanneri;
    List<User> lista;
    FileWriter kirjuri;
    String tiedosto;
    
    public FileUserDAO() throws FileNotFoundException {
        this.tiedosto = "tiedot.txt";
        this.lista = new ArrayList<User>();

        alustaLista(tiedosto);

    }

    @Override
    public List<User> listAll() {
        return lista;
    }

    @Override
    public User findByName(String name) {
        for (User user : lista) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        // avataan tiedosto filewriterin käyttöön
        try {
            this.kirjuri = new FileWriter(tiedosto);
        } catch (IOException ex) {
            System.out.println("Tiedoston avaaminen muokattavaksi epäonnistui.");
        }
        
        this.lista.add(user);
        
        // käydään lista läpi ja kirjoitetaan tiedostoon
        for (User user1 : lista) {
            try {
                kirjuri.write(user1.getUsername() + " " + user1.getPassword() + "\n");
            } catch (IOException ex) {
                System.out.println("Ei voitu kirjoittaa tiedostoon.");
            }
        }
        
        // suljetaan kirjoittaja
        try {
            kirjuri.close();
        } catch (IOException ex) {
            System.out.println("Ei voitu lopettaa kirjoitustapahtumaa.");
        }
        
        // alustetaan lista uudestaan
        try {
            alustaLista(this.tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Ei voitu alustaa listaa.");
        }
    }
    
    private void alustaLista(String tiedosto) throws FileNotFoundException {
        this.file = new File(tiedosto);
        this.skanneri = new Scanner(file);
        this.lista.clear();
        
        while (skanneri.hasNextLine()) {
            lista.add(new User(skanneri.next(), skanneri.next()));
        }
    }
    
    public String getTiedostonNimi() {
        return this.tiedosto;
    }
}
