package ohtu.services;

import ohtu.domain.User;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;
    private Pattern letter = Pattern.compile("[a-zA-z]");
    private Pattern digit = Pattern.compile("[0-9]");
    private Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        if (username == null || password == null) {
            return true;
        } else if (username.length() < 3 || password.length() < 8) {
            return true;
        }

        if (!letter.matcher(username).find() && special.matcher(username).find()
                && digit.matcher(username).find()) {
            return false;
        }


        return !(letter.matcher(password).find() && (digit.matcher(password).find()
                || special.matcher(password).find()));

    }
}
