/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.data_access;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author maef
 */

@Component
public class FileUserDAO implements UserDao{

    private String tiedosto;
    private FileWriter tallentaja;
    private Scanner lukija;
    
    @Autowired
    public FileUserDAO(@Value("src/users.txt") String tiedosto) throws IOException {
        this.tiedosto = tiedosto;
        
        tallentaja = new FileWriter(tiedosto);
        
    }

    @Override
    public List<User> listAll() {
        try {
            lukija = new Scanner(new File(tiedosto));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<User> users = new ArrayList();
        while(lukija.hasNextLine()) {
            String nimi = lukija.nextLine();
            String salasana = lukija.nextLine();
            
            users.add(new User(nimi, salasana));
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        try {
            lukija = new Scanner(new File(tiedosto));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(lukija.hasNextLine()) {
            if (lukija.nextLine().equals(name)) {
                User user = new User(name, lukija.nextLine());
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            tallentaja.append(user.getUsername()+ "\n");
            tallentaja.append(user.getPassword()+"\n");
            tallentaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedostoa ei ole.");
        }
    }
}
