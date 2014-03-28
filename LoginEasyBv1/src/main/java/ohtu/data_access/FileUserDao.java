package ohtu.data_access;

import java.io.BufferedWriter;
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

/**
 *
 * @author Joel
 */
public class FileUserDao implements UserDao {

    private File file;
    private List<User> users;

    public FileUserDao(String filepath) {
        file = new File(filepath);
        users = new ArrayList<User>();

        getUsersFromFile();
    }

    private void getUsersFromFile() {
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] split = str.split(" ");

            users.add(new User(split[0], split[1]));
        }
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (name.toLowerCase().equals(user.getUsername().toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        writeNewUserToFile();
    }

    private void writeNewUserToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (User user : users) {
                bw.write(user.getUsername() + " " + user.getPassword() + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
