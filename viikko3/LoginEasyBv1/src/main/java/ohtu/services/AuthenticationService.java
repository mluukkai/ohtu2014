package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

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
        String username_consistency = "((?=.*[a-z]).{3,40})";
        Pattern namePatrn = Pattern.compile(username_consistency);
        Matcher nameMach = namePatrn.matcher(username);
        
        String password_consistency = "((?=.*[a-z])(?=.*[@#€%&\\d]).{8,40})";
        Pattern passPatrn = Pattern.compile(password_consistency);
        Matcher passMach = passPatrn.matcher(password);
        
        if(username.length() < 3 || this.userDao.findByName(username) != null || !nameMach.matches()) {
            return true;
        }
        if(password.length() < 8 || !passMach.matches()) {
            return true;
        }
        return false;
    }
}
