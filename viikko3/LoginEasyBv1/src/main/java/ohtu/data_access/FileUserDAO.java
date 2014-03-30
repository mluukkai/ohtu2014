/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author Jussi
 */
public class FileUserDAO implements UserDao {

    private String path;
    private List<User> users;

    public FileUserDAO(String path) {

        this.path = path;
        this.users = new ArrayList<User>();
    }

    @Override
    public List<User> listAll() {
        try {
            writeUsersToMemory();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.users;
    }

    @Override
    public User findByName(String name) {
        listAll();
        for (User u : this.users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        this.users.add(user);
        try {
            writeUsersToFile();
        } catch (IOException ex) {
            System.out.println("KTSING!" +ex);
        }
    }

    private void writeUsersToFile() throws IOException {
        FileWriter scribe = new FileWriter(new File(this.path));
        for (User u : this.users) {
            scribe.write(u.getUsername() + "," + u.getPassword() + "\n");
        }
        scribe.close();
    }

    private void writeUsersToMemory() throws IOException {
        Scanner scanner = new Scanner(new File(this.path));
        this.users.clear();
        while (scanner.hasNextLine()) {
            String user = scanner.nextLine();
            String[] userArray = user.split(",");
            this.users.add(new User(userArray[0], userArray[1]));
        }
        scanner.close();
    }
}