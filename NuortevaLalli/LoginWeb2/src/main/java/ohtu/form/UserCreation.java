
package ohtu.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCreation {

    @Size(min = 5, max=10, message = "length 5-10")
    private String username;
    @Size(min = 8,  message = "length greater or equal to 8")
    @Pattern(regexp="(.*\\d.*)|(.*\\W.*)",message = "must contain one character that is not a letter")
    private String password;
    private String passwordConfirmation;
    
    public UserCreation() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + " "+password+ " "+passwordConfirmation;
    }

    
    
}
