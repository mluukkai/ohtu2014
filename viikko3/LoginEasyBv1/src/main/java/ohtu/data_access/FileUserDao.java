/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author henrikorpela
 */
public class FileUserDao extends InMemoryUserDao implements UserDao{
    private File file;
    
    public FileUserDao(String file) throws FileNotFoundException
    {
        this.file = new File(file);
        this.loadUsers();
    }
    
    @Override
    public List<User> listAll() {
        return super.listAll();
    }

    @Override
    public User findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public void add(User user) {
        super.add(user);
        try {
            this.writeUser(user);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadUsers() throws FileNotFoundException {
        Scanner reader = new Scanner(this.file);
        while(reader.hasNextLine())
        {
            String params[] = reader.nextLine().split(":");
            User user = new User(params[0],params[1]);
            super.add(user);
        }
    }
    
    private void writeUser(User user) throws FileNotFoundException, IOException
    {
        FileWriter writer = new FileWriter(this.file);
        writer.append(user.getUsername() + ":" + user.getPassword());
        writer.close();
    }
}
