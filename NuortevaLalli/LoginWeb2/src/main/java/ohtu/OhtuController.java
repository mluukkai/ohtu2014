package ohtu;

import javax.validation.Valid;
import ohtu.authentication.AuthenticationService;
import ohtu.domain.User;
import ohtu.form.UserCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class OhtuController {

    @Autowired
    AuthenticationService auth;

    @RequestMapping({"/"})
    public String showFrontPage() {
        return "home";
    }

    @RequestMapping({"login"})
    public String login(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public String loginPost(Model model, @ModelAttribute("user") User user) {

        System.out.println( user);
        
        if (!auth.logIn(user.getUsername(), user.getPassword())) {
            model.addAttribute("message", "wrong username or password");

            return "login";
        }
        return "redirect:/ohtu";
    }

    @RequestMapping({"user"})
    public String userGet(Model model) {
        model.addAttribute("user", new UserCreation());

        return "user";
    }

    @RequestMapping(value = {"user"}, method = RequestMethod.POST)
    public String userPost(@Valid @ModelAttribute("user") UserCreation user, BindingResult result) {
        if (result.hasErrors()) {
            return "user";
        }

        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            result.addError(new FieldError("user", "password", "password and confirmaton were not the same"));
            return "user";
        }

        if ( !auth.createUser(user.getUsername(), user.getPassword()) ){
            result.addError(new FieldError("user", "username", "username or password invalid"));
            return "user";
        }

        return "redirect:/newUser";
    }

    @RequestMapping({"newUser"})
    public String newUser(Model model) {

        return "newUser";
    }

    @RequestMapping({"ohtu"})
    public String ohtuApp(Model model) {

        return "ohtuApp";
    }
}
